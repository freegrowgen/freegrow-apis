package com.springUserApi.userAPI.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springUserApi.userAPI.enums.userResponseEnums;
import com.springUserApi.userAPI.models.user;
import com.springUserApi.userAPI.repos.userRepository;

@Service
public class userServices {

    @Autowired
    userRepository userDB;

    public userResponseEnums saveUserData(user userData) {
        try {
            user findedUser = userDB.findByEmailId(userData.getEmailId());
            if (findedUser == null || !findedUser.getEmailId().equals(userData.getEmailId())) {
                userDB.save(userData);
                return userResponseEnums.SUCCESS;
            } else if (findedUser.getEmailId().equals(userData.getEmailId())) {
                return userResponseEnums.ALREADY_EXISTS;

            } else {
                return userResponseEnums.ERROR;
            }
        } catch (Exception e) {
            System.out.println("ERROR....... " + e);
            return userResponseEnums.API_ERROR;
        }

    }

}
