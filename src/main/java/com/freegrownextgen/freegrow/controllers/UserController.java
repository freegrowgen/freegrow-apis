package com.freegrownextgen.freegrow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.freegrownextgen.freegrow.models.requestdtos.user.ConfigRoleRequestDto;
import com.freegrownextgen.freegrow.models.responsedtos.user.ConfigRoleResponseDto;
import com.freegrownextgen.freegrow.services.UserServices;

@RestController
@RequestMapping("/user")
public class UserController{

    @Autowired 
    UserServices userServices;

    @PostMapping("/config/role")
    public ConfigRoleResponseDto configRole(@RequestBody ConfigRoleRequestDto request){
        ConfigRoleResponseDto response = new ConfigRoleResponseDto();
        response.setData(userServices.configRole(request));
        return response;
    } 

    

}

