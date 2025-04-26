package com.freegrownextgen.freegrow.implementations;

import com.freegrownextgen.freegrow.enums.response.ResponseEnums;
import com.freegrownextgen.freegrow.models.requestdtos.auth.ForgotPasswordRequestDTO;
import com.freegrownextgen.freegrow.models.requestdtos.auth.LoginRequesDTO;
import com.freegrownextgen.freegrow.models.requestdtos.auth.ResetPasswordRequestDTO;
import com.freegrownextgen.freegrow.models.requestdtos.auth.SignUpRequestDTO;

public interface AuthImpl {

    ResponseEnums signUPImpl(SignUpRequestDTO request);

    ResponseEnums loginImpl(LoginRequesDTO request);

    ResponseEnums forgotPasswordImpl(ForgotPasswordRequestDTO request);

    ResponseEnums resetPasswordImpl(ResetPasswordRequestDTO request);

}
