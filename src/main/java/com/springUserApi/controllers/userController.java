package com.springUserApi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springUserApi.models.user;
import com.springUserApi.repos.userRepository;

@RestController
@RequestMapping("/user")
public class userController {

    @Autowired
    userRepository userDb;

    @GetMapping("{ok}")
    public List<user> getAllUsers() {
        return userDb.findAll();

    }

}
