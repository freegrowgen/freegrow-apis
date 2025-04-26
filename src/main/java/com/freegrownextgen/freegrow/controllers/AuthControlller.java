package com.freegrownextgen.freegrow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.freegrownextgen.freegrow.enums.response.ResponseEnums;
import com.freegrownextgen.freegrow.models.appuser.AppUserModel;
import com.freegrownextgen.freegrow.models.requestdtos.auth.ForgotPasswordRequestDTO;
import com.freegrownextgen.freegrow.models.requestdtos.auth.LoginRequesDTO;
import com.freegrownextgen.freegrow.models.requestdtos.auth.ResetPasswordRequestDTO;
import com.freegrownextgen.freegrow.models.requestdtos.auth.SignUpRequestDTO;
import com.freegrownextgen.freegrow.models.responsedtos.auth.LoginResponseDTO;
import com.freegrownextgen.freegrow.models.responsedtos.auth.ResponseDTO;
import com.freegrownextgen.freegrow.repository.AuthRepository;
import com.freegrownextgen.freegrow.services.AuthServices;

@RestController
@RequestMapping("/auth")
public class AuthControlller {

    @Autowired
    AuthServices auth;

    @Autowired
    AuthRepository authRepo;

    @PostMapping("/signup")
    ResponseDTO SignUp(@RequestBody SignUpRequestDTO request) {

        ResponseDTO response = new ResponseDTO();
        if (request.getFirstName() == null || request.getEmailId() == null) {
            response.setData(ResponseEnums.BAD_REQUEST);
            return response;
        }

        response.setData(auth.signUPImpl(request));
        return response;
    }

    @PostMapping("/login")
    LoginResponseDTO Login(@RequestBody LoginRequesDTO request) {
        LoginResponseDTO response = new LoginResponseDTO();
        if (request.getEmailId() == null) {
            response.setData(ResponseEnums.BAD_REQUEST);
            return response;
        }

        ResponseEnums loginImplResposne = auth.loginImpl(request);

        response.setData(loginImplResposne);

        if (loginImplResposne == ResponseEnums.SUCCESS) {
            AppUserModel userData = authRepo.findByEmailId(request.getEmailId());
            if (userData != null) {
                response.setUserName(userData.getUserName());
                response.setFirstTimeLogin((userData.isFirstTimeLogin()));
            }
        }
        return response;
    }

    @PostMapping("/forgotpassword")
    ResponseDTO forgotPassword(@RequestBody ForgotPasswordRequestDTO request) {
        ResponseDTO response = new ResponseDTO();
        if (request.getEmailId() == null) {
            response.setData(ResponseEnums.BAD_REQUEST);
            return response;
        }
        ResponseEnums loginImplResposne = auth.forgotPasswordImpl(request);
        response.setData(loginImplResposne);

        return response;
    }

    @PostMapping("/resetpassword")
    ResponseDTO resetPassword(@RequestBody ResetPasswordRequestDTO request) {
        ResponseDTO response = new ResponseDTO();
        if (request.getPassword() == null) {
            response.setData(ResponseEnums.BAD_REQUEST);
            return response;
        }
        response.setData(auth.resetPasswordImpl(request));
        return response;
    }

}
