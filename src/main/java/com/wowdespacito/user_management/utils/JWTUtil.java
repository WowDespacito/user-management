package com.wowdespacito.user_management.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;

import java.util.Date;
import java.util.Map;

public class JWTUtil {
    private static final String KEY = System.getenv("JWT_KEY");

    //接收业务数据，生成token并返回
    public static String getToken(Map<String, Object> claims){
        return JWT.create()
                .withClaim("claims", claims)
                .withExpiresAt(new Date(System.currentTimeMillis()+1000*60*60))
                .sign(Algorithm.HMAC256(KEY));
    }

    //接收验证token，验证token，并返回业务数据
    public static Map<String, Object> parseToken(String token){
        return JWT.require(Algorithm.HMAC256(KEY))
                .build()
                .verify(token)
                .getClaim("claims")
                .asMap();
    }

    public static boolean isExpired (String token){
        try{
            Date expiresAt = JWT.require(Algorithm.HMAC256(KEY))
                                .build()
                                .verify(token)
                                .getExpiresAt(); 
            return expiresAt.before(new Date());
        }catch(JWTDecodeException e){
            return true;
        }
    }
}
