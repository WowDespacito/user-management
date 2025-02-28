package com.wowdespacito.user_management.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wowdespacito.user_management.exception.LoginException;
import com.wowdespacito.user_management.mapper.UserMapper;
import com.wowdespacito.user_management.service.UserService;
import com.wowdespacito.user_management.utils.JWTUtil;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    private Integer findUserByUsername(String username) {
        return userMapper.findUserByUsername(username);
    }

    private String findPasswordById(Integer id) {
        String password = userMapper.findPasswordById(id);
        return password;
    }

    @Override
    public String login(String username, String password){
        System.out.println(username);
        Integer id = userMapper.findUserByUsername(username);
        System.out.println(id);
        if (password.equals(findPasswordById(id))) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", id);
            claims.put("username", username);
            String token = JWTUtil.getToken(claims);

            return token;
        }else {
            return null;
        }
    }

    // @Override
    // public String login(String username, String password) throws Exception {
    //     Integer id = findUserByUsername(username);
    //     System.out.println(id);
    //     if (id == null) {
    //         throw new LoginException("用户不存在");
    //     }
    //     if (password.equals(findPasswordById(id))) {
    //         Map<String, Object> claims = new HashMap<>();
    //         claims.put("id", id);
    //         claims.put("username", username);
    //         String token = JWTUtil.getToken(claims);

    //         return token;
    //     }else {
    //         throw new LoginException("密码错误");
    //     }
    // }
}
