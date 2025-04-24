package com.freegrownextgen.freegrow.implementations;

import com.freegrownextgen.freegrow.enums.AuthEnums;
import com.freegrownextgen.freegrow.models.requestdtos.auth.ForgotPasswordRequestDTO;
import com.freegrownextgen.freegrow.models.requestdtos.auth.LoginRequesDTO;
import com.freegrownextgen.freegrow.models.requestdtos.auth.ResetPasswordRequestDTO;
import com.freegrownextgen.freegrow.models.requestdtos.auth.SignUpRequestDTO;

public interface AuthImpl {

    AuthEnums signUPImpl(SignUpRequestDTO request);

    AuthEnums loginImpl(LoginRequesDTO request);

    AuthEnums forgotPasswordImpl(ForgotPasswordRequestDTO request);

    AuthEnums resetPasswordImpl(ResetPasswordRequestDTO request);

}
