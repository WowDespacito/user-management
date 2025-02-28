package com.wowdespacito.user_management.controller;

import org.springframework.web.bind.annotation.RestController;

import com.wowdespacito.user_management.pojo.Response;
import com.wowdespacito.user_management.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public Response<String> login(@RequestParam(required = true) String username, @RequestParam(required = true) String password) {
        return Response.success(userService.login(username, password));
    }
    
}
