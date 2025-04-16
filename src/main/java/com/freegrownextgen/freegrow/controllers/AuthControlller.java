package com.freegrownextgen.freegrow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.freegrownextgen.freegrow.enums.AuthEnums;
import com.freegrownextgen.freegrow.models.requestmodels.auth.LoginRequestModel;
import com.freegrownextgen.freegrow.models.requestmodels.auth.SignUpRequestModel;
import com.freegrownextgen.freegrow.models.requestmodels.response.ResponseModel;
import com.freegrownextgen.freegrow.services.AuthServices;

@RestController
@RequestMapping("/auth")
public class AuthControlller {

    @Autowired
    AuthServices auth;

    @PostMapping("/signup")
    ResponseModel SignUp(@RequestBody SignUpRequestModel request) {

        ResponseModel response = new ResponseModel();
        if (request.getFirstName() == null || request.getEmailId() == null || request.getMobileNumber() == null
                || request.getMobileNumber() == null) {
            response.setData(AuthEnums.BAD_REQUEST);
            return response;
        }

        response.setData(auth.signUPImpl(request));
        return response;
    }

    @PostMapping("/login")
    ResponseModel Login(@RequestBody LoginRequestModel request) {
        ResponseModel response = new ResponseModel();
        if (request.getEmailId() == null || request.getPassword() == null) {
            response.setData(AuthEnums.BAD_REQUEST);
            return response;
        }
        response.setData(auth.loginImpl(request));
        return response;

    }

}
