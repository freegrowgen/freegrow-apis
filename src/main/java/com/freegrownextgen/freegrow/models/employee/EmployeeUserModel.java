package com.freegrownextgen.freegrow.models.employee;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "employee_users")
public class EmployeeUserModel {
    private String emailId;

}
