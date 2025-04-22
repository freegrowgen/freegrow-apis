package com.freegrownextgen.freegrow.implementations;

import com.freegrownextgen.freegrow.enums.AuthEnums;
import com.freegrownextgen.freegrow.models.requestmodels.auth.ForgotPasswordRequestModel;
import com.freegrownextgen.freegrow.models.requestmodels.auth.LoginRequestModel;
import com.freegrownextgen.freegrow.models.requestmodels.auth.ResetPasswordRequestModel;
import com.freegrownextgen.freegrow.models.requestmodels.auth.SignUpRequestModel;

public interface AuthImpl {

    AuthEnums signUPImpl(SignUpRequestModel request);

    AuthEnums loginImpl(LoginRequestModel request);

    AuthEnums forgotPasswordImpl(ForgotPasswordRequestModel request);

    AuthEnums resetPasswordImpl(ResetPasswordRequestModel request);

}
