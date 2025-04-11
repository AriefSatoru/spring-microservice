package com.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.entity.UserInfo;
import com.backend.service.UserInfoService;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    
    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("/register")
    public String addUser(@RequestBody UserInfo userInfo) {
        userInfoService.addUser(userInfo);
        return "User added successfully";
    }
}
