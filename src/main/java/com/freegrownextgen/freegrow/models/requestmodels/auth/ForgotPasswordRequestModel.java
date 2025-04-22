package com.freegrownextgen.freegrow.models.requestmodels.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForgotPasswordRequestModel{
    private String emailId;

}