package com.freegrownextgen.freegrow.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freegrownextgen.freegrow.enums.AccountStatusEnum;
import com.freegrownextgen.freegrow.enums.AuthEnums;
import com.freegrownextgen.freegrow.implementations.AuthImpl;
import com.freegrownextgen.freegrow.models.appuser.AppUserModel;
import com.freegrownextgen.freegrow.models.requestmodels.auth.LoginRequestModel;
import com.freegrownextgen.freegrow.models.requestmodels.auth.SignUpRequestModel;
import com.freegrownextgen.freegrow.repository.AuthRepository;
import com.freegrownextgen.freegrow.utils.AuthUtils;
import com.freegrownextgen.freegrow.utils.appRegex;

@Service
public class AuthServices implements AuthImpl {

    @Autowired
    private AuthRepository authRepo;

    appRegex regexCheck = new appRegex();
    AuthUtils authUtils = new AuthUtils();

    @Override
    public AuthEnums signUPImpl(SignUpRequestModel request) {
        try {
            if (request.getFirstName().length() < 3 || !regexCheck.isValidEmail(request.getEmailId())
                    || !regexCheck.isValidMobileNumber(request.getMobileNumber())) {
                return AuthEnums.BAD_REQUEST;
            }

            if (authRepo.findByEmailId(request.getEmailId()) != null) {
                return AuthEnums.USER_EXISTS;
            }

            AppUserModel userData = new AppUserModel();
            userData.setFirstName(request.getFirstName());
            userData.setLastName(request.getLastName());
            userData.setEmailId(request.getEmailId());
            userData.setPassword(authUtils.hash(request.getPassword()));
            userData.setMobileNumber(request.getMobileNumber());
            userData.setRole(null);
            userData.setAccountStatus(AccountStatusEnum.PENDING);
            userData.setOtp(null);

            AppUserModel user = authRepo.save(userData);
            if (user.getEmailId() != null) {
                return AuthEnums.SUCCESS;
            } else {
                return AuthEnums.ERROR;
            }

        } catch (Exception e) {
            return AuthEnums.INTERNAL_SERVER_ERROR;
        }

    }

    @Override
    public AuthEnums loginImpl(LoginRequestModel request) {
        try {
            if (!regexCheck.isValidEmail(request.getEmailId())) {
                return AuthEnums.INVLAID_EMAIL_ID;
            }

            if (authRepo.findByEmailId(request.getEmailId()) == null) {
                return AuthEnums.USER_NOT_FOUND;
            }

            if (request.getOtp() == null) {
                int user = authRepo.findAndUpdateOtp(request.getEmailId(), authUtils.generateOtp());
                if (user == 1) {
                    return AuthEnums.OTP_SENT;
                } else {
                    return AuthEnums.INTERNAL_SERVER_ERROR;
                }

            } else {
                AppUserModel userData = authRepo.findByEmailId(request.getEmailId());
                System.err.println(userData.toString());
                if (userData.getOtp().toString().equals(request.getOtp().toString())) {
                    return AuthEnums.SUCCESS;
                } else {
                    return AuthEnums.INVALID_OTP;
                }

            }

        } catch (Exception e) {
            System.out.println(e);
            return AuthEnums.INTERNAL_SERVER_ERROR;
        }

    }

}
