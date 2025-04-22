package com.freegrownextgen.freegrow.models.requestmodels.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResetPasswordRequestModel {
    private String password;
    private String token;

}
