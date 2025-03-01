package com.wowdespacito.user_management.service;

public interface UserService {
    String login(String username, String password) throws Exception;

    String register(String email, String password) throws Exception;

    boolean verifyToken(String token) throws Exception;

    String changeUsername(Integer id, String username) throws Exception;
}
