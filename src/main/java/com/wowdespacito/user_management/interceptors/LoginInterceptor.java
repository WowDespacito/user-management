package com.wowdespacito.user_management.interceptors;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.wowdespacito.user_management.utils.JWTUtil;
import com.wowdespacito.user_management.utils.ThreadLocalUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {
        String token=request.getHeader("token");
        try {
            Map<String, Object> claims = JWTUtil.parseToken(token);
            String redisToken = stringRedisTemplate.opsForValue().get(claims.get("User_"+claims.get("id")).toString());
            if (redisToken == null || !redisToken.equals(token)) {
                throw new RuntimeException();
            }

            ThreadLocalUtil.set(claims);
            return true;
        }catch (Exception e){
            response.setStatus(401);
            return false;
        }
    }
    
}
