package com.wowdespacito.user_management.controller;

import org.springframework.web.bind.annotation.RestController;

import com.wowdespacito.user_management.exception.MyException;
import com.wowdespacito.user_management.pojo.Response;
import com.wowdespacito.user_management.service.UserService;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public Response<String> login(@RequestParam @NotBlank String username, @RequestParam @NotBlank String password) throws MyException {
        return Response.success(userService.login(username, password));
    }
    
    @GetMapping("/register")
    public Response<String> register(@RequestParam @NotBlank @Email String email, @RequestParam @NotBlank String password) throws MyException {
        return Response.success(userService.register(email, password));
    }

    @PostMapping("/verifyToken")
    public Response<Boolean> verifyToken(@RequestParam @NotBlank String token) throws MyException {
        return Response.success(userService.verifyToken(token));
    }

    @PutMapping("/changeUsername")
    public Response<String> putMethodName(@RequestParam @NotBlank Integer id, @RequestParam @NotBlank String username) throws MyException {
        return Response.success(userService.changeUsername(id, username));
    }
}
