
package com.wowdespacito.user_management.service.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.wowdespacito.user_management.exception.MyException;
import com.wowdespacito.user_management.exception.UserException;
import com.wowdespacito.user_management.mapper.UserMapper;
import com.wowdespacito.user_management.pojo.User;
import com.wowdespacito.user_management.service.UserService;
import com.wowdespacito.user_management.utils.JWTUtil;
import com.wowdespacito.user_management.utils.Md5Util;
import com.wowdespacito.user_management.utils.RandomStringGeneratorUtil;
import com.wowdespacito.user_management.utils.ThreadLocalUtil;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private User findUserByUsername(String username) {
        User user = new User();
        user.setUsername(username);
        return userMapper.findUser(user);
    }

    private User findUserByEmail(String email) {
        User user = new User();
        user.setEmail(email);
        return userMapper.findUser(user);
    }

    @Override
    public String login(String username, String password) throws MyException {
        User user = findUserByUsername(username);
        if (user == null || user.equals(null)) {
            user = findUserByEmail(username);
            if (user == null || user.equals(null)) {
                throw new UserException("用户不存在");
            }
        }
        if (Md5Util.getMd5String(password).equals(user.getPassword())) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", user.getId());
            claims.put("username", user.getUsername());
            String token = JWTUtil.getToken(claims);
            stringRedisTemplate.opsForValue().set("User_"+user.getId().toString(), token, 1, TimeUnit.HOURS);
            ThreadLocalUtil.set(claims);
            return token;
        }else {
            throw new UserException("密码错误");
        }
    }

    @Override
    public String register(String email, String password) throws MyException {
        User user = new User();
        user.setEmail(email);
        if (userMapper.findUser(user) == null) {
            user.setUsername(RandomStringGeneratorUtil.getRandomUsername(8));
            user.setPassword(Md5Util.getMd5String(password));
            user.setRole(0);
            user.setStatus(1);
            user.setCreateTime(LocalDateTime.now());
            user.setUpdateTime(LocalDateTime.now());
            List<String> usernames = userMapper.allUsers().stream().map(User::getUsername).collect(Collectors.toList());
            int step = 1;
            while (usernames.contains(user.getUsername())) {
                String newUsername = RandomStringGeneratorUtil.neighborString(user.getUsername(), step);
                if (!newUsername.equals(user.getUsername())) {
                    user.setUsername(newUsername);
                }else if (step != -1){
                    step = -1;
                }else{
                    UserException e = new UserException("长度为8的用户名已全部被占用！！");
                    throw e;
                }
            }
            userMapper.insertUser(user);
            return user.getUsername();
        }else {
            throw new UserException("邮箱已被注册");
        }
    }

    @Override
    public boolean verifyToken(String token) {
        try {
            Map<String, Object> claims = JWTUtil.parseToken(token);
            Integer id =  (Integer)claims.get("id");
            logger.debug("解析出用户id："+id.toString());
            String username = (String) claims.get("username");
            logger.debug("解析出用户名："+username);
            User tmpUser = new User();
            tmpUser.setId(id);tmpUser.setUsername(username);
            if (userMapper.findUser(tmpUser)!= null 
            && !userMapper.findUser(tmpUser).equals(null)
            ){
                logger.info("验证通过");
                return true;
            }else{
                logger.info("token错误");
                return false;
            }
        } catch (TokenExpiredException e){
            logger.info("token过期");
            return false;
        } catch (Exception e){
            logger.error(e.getMessage());
            return false;
        }


    }

    @Override
    public String changeUsername(Integer id, String username) throws MyException {
        User user = new User();
        user.setId(id);
        user = userMapper.findUser(user);
        if (user == null) {
            throw new UserException("用户不存在");
        }
        if (findUserByUsername(username) != null) {
            throw new UserException("用户名已存在");
        }
        user.setUsername(username);
        userMapper.updateUser(user);

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("username", user.getUsername());
        String token = JWTUtil.getToken(claims);
        stringRedisTemplate.opsForValue().set("User_"+user.getId().toString(), token, 1, TimeUnit.HOURS);
        ThreadLocalUtil.set(claims);
        return token;
    }
}
