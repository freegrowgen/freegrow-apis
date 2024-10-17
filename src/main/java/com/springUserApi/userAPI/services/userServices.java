package com.springUserApi.userAPI.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springUserApi.userAPI.enums.userResponseEnums;
import com.springUserApi.userAPI.models.User;
import com.springUserApi.userAPI.repos.userRepository;

@Service
public class userServices {

    @Autowired
    userRepository userDB;

    public userResponseEnums saveUserData(User userData) {

        try {
            User user = userDB.save(userData);
            if (user.getEmailId() == userData.getEmailId()) {
                return userResponseEnums.SUCCESS;
            } else {
                return userResponseEnums.ERROR;
            }
        } catch (Exception e) {
            System.out.println("ERROR....... " + e);
            return userResponseEnums.API_ERROR;
        }

    }

}
