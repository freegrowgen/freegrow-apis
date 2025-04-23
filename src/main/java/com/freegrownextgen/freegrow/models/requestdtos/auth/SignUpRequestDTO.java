package com.freegrownextgen.freegrow.models.requestdtos.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestDTO {
    private String firstName;
    private String lastName;    
    private String emailId;
    private String password;
    private String mobileNumber;
    private Integer otp;
    private boolean googleSignUp;
    private String profileUrl;
}
