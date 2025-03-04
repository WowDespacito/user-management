package com.wowdespacito.user_management.service;

import com.wowdespacito.user_management.exception.MyException;

public interface UserService {
    String login(String username, String password) throws MyException;

    String register(String email, String password) throws MyException;

    boolean verifyToken(String token) throws MyException;

    String changeUsername(Integer id, String username) throws MyException;
}
