package com.freegrownextgen.freegrow.models.utils;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "temp_app_users")
public class TempAppUserModel {
    private String emailId;
    private Integer otp;

}
