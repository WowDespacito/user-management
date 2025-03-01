package com.wowdespacito.user_management.controller;

import org.springframework.web.bind.annotation.RestController;

import com.wowdespacito.user_management.pojo.Response;
import com.wowdespacito.user_management.service.UserService;

import jakarta.validation.constraints.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public Response<String> login(@RequestParam(required = true) String username, @RequestParam(required = true) String password) throws Exception {
        return Response.success(userService.login(username, password));
    }
    
    @GetMapping("/register")
    public Response<String> register(@RequestParam(required = true) @Email String email, @RequestParam(required = true) String password) throws Exception {
        return Response.success(userService.register(email, password));
    }

    @GetMapping("/virifyToken")
    public Response<Boolean> verifyToken(@RequestParam(required = true) String token) throws Exception {
        return Response.success(userService.verifyToken(token));
    }

    @PutMapping("/changeUsername")
    public Response<String> putMethodName(@RequestParam(required = true) Integer id, @RequestParam(required = true) String username) throws Exception {
        return Response.success(userService.changeUsername(id, username));
    }
}
