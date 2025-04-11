package com.freegrownextgen.freegrow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.freegrownextgen.freegrow.services.AuthServices;

@RestController
@RequestMapping("/auth")
public class AuthControlller {

    @Autowired
    AuthServices auth;

    @GetMapping("/aa")
    public void Test() {
        auth.signUP();
        System.out.println("test");

    }

}
