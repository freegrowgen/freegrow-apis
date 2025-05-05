package com.freegrownextgen.freegrow.models.utils;

import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResetPassword {
    private String resetPasswordToken;
    private String emailId;
    private LocalDateTime createdAt;

}
