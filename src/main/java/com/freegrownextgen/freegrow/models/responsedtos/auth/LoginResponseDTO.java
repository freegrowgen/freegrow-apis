package com.freegrownextgen.freegrow.models.responsedtos.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDTO {
    private Object data;
    private String userName;
    private boolean isFirstTimeLogin;

}
