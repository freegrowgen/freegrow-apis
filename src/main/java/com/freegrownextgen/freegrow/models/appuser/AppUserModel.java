package com.freegrownextgen.freegrow.models.appuser;

import org.springframework.data.mongodb.core.mapping.Document;

import com.freegrownextgen.freegrow.enums.AccountStatusEnum;
import com.freegrownextgen.freegrow.enums.RoleEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "app_users")
public class AppUserModel {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String mobileNumber;
    private RoleEnum role;
    private AccountStatusEnum accountStatus;
}
