package com.springUserApi.userAPI.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springUserApi.userAPI.enums.userResponseEnums;
import com.springUserApi.userAPI.models.User;
import com.springUserApi.userAPI.repos.userRepository;
import com.springUserApi.userAPI.services.userServices;

@RestController
@RequestMapping("/usr")
public class userController {

    @Autowired
    userRepository userDb;

    @Autowired
    userServices userService;

    @GetMapping("/allUsers")
    public List<User> getAllUser() {
        return userDb.findAll();
    }

    @PostMapping("/addUser")
    public userResponseEnums addUser(@RequestBody User userData) {
        return userService.saveUserData(userData);
    }

}
