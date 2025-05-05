package com.freegrownextgen.freegrow.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freegrownextgen.freegrow.enums.AccountStatusEnum;
import com.freegrownextgen.freegrow.enums.response.ResponseEnums;
import com.freegrownextgen.freegrow.implementations.AuthImpl;
import com.freegrownextgen.freegrow.models.appuser.AppUserModel;
import com.freegrownextgen.freegrow.models.requestdtos.auth.ForgotPasswordRequestDTO;
import com.freegrownextgen.freegrow.models.requestdtos.auth.LoginRequesDTO;
import com.freegrownextgen.freegrow.models.requestdtos.auth.ResetPasswordRequestDTO;
import com.freegrownextgen.freegrow.models.requestdtos.auth.SignUpRequestDTO;
import com.freegrownextgen.freegrow.models.utils.ResetPassword;
import com.freegrownextgen.freegrow.models.utils.TempAppUserModel;
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
    public ResponseEnums signUPImpl(SignUpRequestDTO request) {
        try {
            if (request.getFirstName().length() < 3) {
                return ResponseEnums.INVLAID_NAME;
            }
            if (!regexCheck.isValidEmail(request.getEmailId())) {
                return ResponseEnums.INVLAID_EMAIL_ID;
            }

            if (authRepo.findByEmailId(request.getEmailId()) != null) {
                return ResponseEnums.USER_EXISTS;
            }

            TempAppUserModel tempUser = tempAuthRepo.findByEmailId(request.getEmailId());

            if (request.getOtp() == null && !request.isGoogleSignUp()) {
                int signUpOtp = authUtils.generateOtp();

                TempAppUserModel tempUserData = new TempAppUserModel();

                tempUserData.setEmailId(request.getEmailId());
                tempUserData.setOtp(signUpOtp);

                if (tempUser != null) {
                    tempAuthRepo.findAndUpdateOtp(request.getEmailId(), signUpOtp);

                } else {
                    tempAuthRepo.save(tempUserData);

                }
                String signupBody = "Dear User,\n\n"
                        + "Your One-Time Password (OTP) for signing up for your FreeGrow account : " + signUpOtp
                        + "\n\n"
                        + "Please do not share this OTP with anyone. It is valid for a limited time only.\n\n"
                        + "If you did not initiate this request, please ignore this message.\n\n"
                        + "Regards,\n"
                        + "The FreeGrow Team";

                EmailServices signupEmailService = new EmailServices(
                        request.getEmailId(),
                        "Welcome to FreeGrow",
                        "Signup OTP Verification",
                        signupBody);

                signupEmailService.sendEmail();

                return ResponseEnums.OTP_SENT;

            } else {
                boolean isValid;

                if (request.isGoogleSignUp())
                    isValid = true;
                else if (request.getOtp().toString().equals(tempUser.getOtp().toString()))
                    isValid = true;

                else
                    isValid = false;

                if (isValid) {
                    AppUserModel userData = new AppUserModel();
                    userData.setFirstName(request.getFirstName());
                    userData.setLastName(request.getLastName());
                    userData.setEmailId(request.getEmailId());
                    userData.setMobileNumber(request.getMobileNumber());
                    userData.setRole(null);
                    userData.setAccountStatus(AccountStatusEnum.PENDING);
                    userData.setOtp(null);
                    userData.setUserName(authUtils.generateRandoUsername(request.getFirstName()));
                    userData.setFirstTimeLogin(true);
                    if (request.isGoogleSignUp()) {
                        userData.setGoogleSignUp(true);
                        userData.setPassword(null);
                        userData.setProfileUrl(request.getProfileUrl());
                    } else {
                        userData.setGoogleSignUp(false);
                        userData.setPassword(authUtils.hash(request.getPassword()));
                    }
                    AppUserModel user = authRepo.save(userData);
                    if (user.getEmailId() != null) {
                        tempAuthRepo.deleteByEmailId(request.getEmailId());
                        return ResponseEnums.SUCCESS;
                    } else {
                        return ResponseEnums.ERROR;
                    }
                } else {
                    return ResponseEnums.INVALID_OTP;

                }
            }

        } catch (Exception e) {
            System.out.println(e);
            return ResponseEnums.INTERNAL_SERVER_ERROR;
        }

    }

    @Override
    public ResponseEnums loginImpl(LoginRequesDTO request) {
        try {
            if (!regexCheck.isValidEmail(request.getEmailId())) {
                return ResponseEnums.INVLAID_EMAIL_ID;
            }

            AppUserModel loginUser = authRepo.findByEmailId(request.getEmailId());
            if (loginUser != null && loginUser.isGoogleSignUp()) {

                if (request.isGoogleLogin()) {
                    return ResponseEnums.SUCCESS;

                } else {
                    return ResponseEnums.INVALID_USER_LOGIN_TYPE;
                }

            } else {
                if (request.isGoogleLogin() && loginUser != null) {
                    return ResponseEnums.INVALID_USER_LOGIN_TYPE_GOOGLE;
                } else if (request.isGoogleLogin() && loginUser == null) {
                    SignUpRequestDTO signUpRequest = new SignUpRequestDTO();
                    signUpRequest.setEmailId(request.getEmailId());
                    signUpRequest.setGoogleSignUp(true);
                    signUpRequest.setProfileUrl(request.getProfileUrl());
                    signUpRequest.setFirstName(request.getFirstName());

                    return signUPImpl(signUpRequest);
                }
                if (request.getOtp() == null) {
                    int loginOtp = authUtils.generateOtp();
                    int user = authRepo.findAndUpdateOtp(request.getEmailId(), loginOtp);
                    String loginBody = "Dear User,\n\n"
                            + "Your One-Time Password (OTP) for logging into your FreeGrow account is: " + loginOtp
                            + "\n\n"
                            + "Please do not share this OTP with anyone. It is valid for a limited time only.\n\n"
                            + "If you did not initiate this request, please ignore this message.\n\n"
                            + "Regards,\n"
                            + "The FreeGrow Team";

                    EmailServices loginEmailService = new EmailServices(
                            request.getEmailId(),
                            "FreeGrow Login Verification",
                            "Login OTP Verification",
                            loginBody);
                    loginEmailService.sendEmail();

                    if (user == 1) {
                        return ResponseEnums.OTP_SENT;
                    } else {
                        return ResponseEnums.INTERNAL_SERVER_ERROR;
                    }

                } else {
                    AppUserModel userData = authRepo.findByEmailId(request.getEmailId());
                    System.err.println(userData.toString());
                    if (userData.getOtp().toString().equals(request.getOtp().toString())) {
                        return ResponseEnums.SUCCESS;
                    } else {
                        return ResponseEnums.INVALID_OTP;
                    }

                }

            }

        } catch (Exception e) {
            System.out.println(e);
            return ResponseEnums.INTERNAL_SERVER_ERROR;
        }
    }

    @Override
    public ResponseEnums forgotPasswordImpl(ForgotPasswordRequestDTO request) {
        try {
            if (!regexCheck.isValidEmail(request.getEmailId())) {
                return ResponseEnums.INVLAID_EMAIL_ID;
            }

            AppUserModel user = authRepo.findByEmailId(request.getEmailId());
            if (user == null)
                return ResponseEnums.USER_NOT_FOUND;
            else if (user.isGoogleSignUp()) {
                return ResponseEnums.INVALID_USER_LOGIN_TYPE;
            }

            else if (user != null) {
                String token = authUtils.generateRandomToken(20);
                ResetPassword resetPassword = new ResetPassword();
                resetPassword.setEmailId(request.getEmailId());
                resetPassword.setResetPasswordToken(token);
                resetPassword.setCreatedAt(LocalDateTime.now());
                authRepo.findAndUpdateresetPassword(request.getEmailId(), resetPassword);

                String resetLink = "https://freegrow.live/reset-password/" + token;

                String subject = "Reset your FreeGrow password";
                String body = "Hi " + user.getFirstName() + ",\n\n"
                        + "You recently requested to reset your password for your FreeGrow account.\n"
                        + "Click the link below to reset it (this link is valid for 10 minutes):\n\n"
                        + resetLink + "\n\n"
                        + "If you did not request a password reset, you can safely ignore this email.\n\n"
                        + "Thanks,\n"
                        + "The FreeGrow Team";

                EmailServices emailService = new EmailServices(
                        request.getEmailId(),
                        "Password Reset - FreeGrow",
                        subject,
                        body);
                emailService.sendEmail();
                return ResponseEnums.SUCCESS;

            }
            return ResponseEnums.ERROR;

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEnums.INTERNAL_SERVER_ERROR;
        }
    }

    @Override
    public ResponseEnums resetPasswordImpl(ResetPasswordRequestDTO request) {

        try {
            if (request.getPassword() == null) {
                return ResponseEnums.BAD_REQUEST;
            }

            AppUserModel user = authRepo.findByResetPasswordToken(request.getToken()).orElse(null);
            boolean expired = user.getResetPassword().getCreatedAt().plusMinutes(10).isBefore(LocalDateTime.now());
            if (user != null && !expired) {
                int updateCount = authRepo.findAndUpdatePassword(user.getEmailId(), request.getPassword());

                if (updateCount == 1) {
                    return ResponseEnums.SUCCESS;
                } else {
                    return ResponseEnums.ERROR;
                }

            } else {
                return ResponseEnums.LINK_EXPIRED;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEnums.INTERNAL_SERVER_ERROR;
        }

    }

}
