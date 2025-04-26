package com.freegrownextgen.freegrow.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freegrownextgen.freegrow.enums.response.ResponseEnums;
import com.freegrownextgen.freegrow.implementations.UserServicesImpl;
import com.freegrownextgen.freegrow.models.appuser.AppUserModel;
import com.freegrownextgen.freegrow.models.requestdtos.user.ConfigRoleRequestDto;
import com.freegrownextgen.freegrow.repository.UserRepository;

@Service
public class UserServices implements UserServicesImpl {

    @Autowired
    UserRepository userRepo;

    public ResponseEnums configRole(ConfigRoleRequestDto request) {

        try {
            if (request.getUserName() == null || request.getRole() == null) {
                return ResponseEnums.BAD_REQUEST;
            }

            AppUserModel userData = userRepo.findByUserName(request.getUserName());
            if (userData == null) {
                return ResponseEnums.USER_NOT_FOUND;
            }

            int updateCount = userRepo.findAndUpdateByEmailId(userData.getEmailId(), request.getRole(), false);

            if (updateCount == 1) {
                return ResponseEnums.SUCCESS;
            } else
                return ResponseEnums.ERROR;

        } catch (Exception e) {
            System.out.println(e);
            return ResponseEnums.INTERNAL_SERVER_ERROR;
        }

    }

}
