package com.freegrownextgen.freegrow.implementations;

import com.freegrownextgen.freegrow.enums.AuthEnums;
import com.freegrownextgen.freegrow.models.requestmodels.auth.LoginRequestModel;
import com.freegrownextgen.freegrow.models.requestmodels.auth.SignUpRequestModel;

public interface  AuthImpl {
    
    AuthEnums signUPImpl(SignUpRequestModel request);
    AuthEnums loginImpl(LoginRequestModel request);


}
