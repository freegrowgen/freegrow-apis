package com.freegrownextgen.freegrow.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freegrownextgen.freegrow.enums.AccountStatusEnum;
import com.freegrownextgen.freegrow.enums.AuthEnums;
import com.freegrownextgen.freegrow.implementations.AuthImpl;
import com.freegrownextgen.freegrow.models.appuser.AppUserModel;
import com.freegrownextgen.freegrow.models.appuser.TempAppUserModel;
import com.freegrownextgen.freegrow.models.requestmodels.auth.LoginRequestModel;
import com.freegrownextgen.freegrow.models.requestmodels.auth.SignUpRequestModel;
import com.freegrownextgen.freegrow.repository.AuthRepository;
import com.freegrownextgen.freegrow.repository.TempAuthRepository;
import com.freegrownextgen.freegrow.utils.AuthUtils;
import com.freegrownextgen.freegrow.utils.appRegex;

@Service
public class AuthServices implements AuthImpl {

    @Autowired
    private AuthRepository authRepo;
    @Autowired
    private TempAuthRepository tempAuthRepo;

    appRegex regexCheck = new appRegex();
    AuthUtils authUtils = new AuthUtils();

    @Override
    public AuthEnums signUPImpl(SignUpRequestModel request) {
        try {
            if (request.getFirstName().length() < 3 || !regexCheck.isValidEmail(request.getEmailId())) {
                return AuthEnums.BAD_REQUEST;
            }

            if (authRepo.findByEmailId(request.getEmailId()) != null) {
                return AuthEnums.USER_EXISTS;
            }
            TempAppUserModel tempUser = tempAuthRepo.findByEmailId(request.getEmailId());

            if (request.getOtp() == null) {
                int signUpOtp = authUtils.generateOtp();

                TempAppUserModel tempUserData = new TempAppUserModel();

                tempUserData.setEmailId(request.getEmailId());
                tempUserData.setOtp(signUpOtp);

                if (tempUser != null) {
                    tempAuthRepo.findAndUpdateOtp(request.getEmailId(), signUpOtp);

                } else {
                    tempAuthRepo.save(tempUserData);

                }
                String signupBody = "Thank you for signing up on FreeGrow. Your OTP for email verification is -"
                        + signUpOtp + ". Let's grow together!";
                EmailServices signupEmailService = new EmailServices(
                        request.getEmailId(),
                        "Welcome to FreeGrow!",
                        "Signup OTP Verification",
                        signupBody);
                signupEmailService.sendEmail();

                return AuthEnums.OTP_SENT;

            } else {
                if (request.getOtp().toString().equals(tempUser.getOtp().toString())) {
                    AppUserModel userData = new AppUserModel();
                    userData.setFirstName(request.getFirstName());
                    userData.setLastName(request.getLastName());
                    userData.setEmailId(request.getEmailId());
                    userData.setMobileNumber(request.getMobileNumber());
                    userData.setRole(null);
                    userData.setAccountStatus(AccountStatusEnum.PENDING);
                    userData.setOtp(null);
                    if (request.isGoogleSignUp()) {
                        userData.setGoogleSignUp(true);
                        userData.setPassword(null);
                    } else {
                        userData.setGoogleSignUp(false);
                        userData.setPassword(authUtils.hash(request.getPassword()));
                    }

                    AppUserModel user = authRepo.save(userData);
                    if (user.getEmailId() != null) {
                        tempAuthRepo.deleteByEmailId(request.getEmailId());
                        return AuthEnums.SUCCESS;
                    } else {
                        return AuthEnums.ERROR;
                    }
                } else {
                    return AuthEnums.INVALID_OTP;

                }
            }

        } catch (Exception e) {
            System.out.println(e);
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
                int loginOtp = authUtils.generateOtp();
                int user = authRepo.findAndUpdateOtp(request.getEmailId(), loginOtp);
                String loginBody = "Your OTP to login into FreeGrow is - " + loginOtp
                        + ". Please do not share it with anyone.";

                EmailServices loginEmailService = new EmailServices(
                        request.getEmailId(),
                        "FreeGrow Login Verification",
                        "Login OTP Verification",
                        loginBody);
                loginEmailService.sendEmail();

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
