package com.freegrownextgen.freegrow.models.requestdtos.user;

import com.freegrownextgen.freegrow.enums.RoleEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfigRoleRequestDto {
    private String userName;
    private RoleEnum role; 
}
