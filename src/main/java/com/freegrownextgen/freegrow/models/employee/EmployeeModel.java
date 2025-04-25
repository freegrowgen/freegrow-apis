package com.freegrownextgen.freegrow.models.employee;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "employees")
/**
 * EmployeeUserModel is a model class that represents an employee user in the system.
 * It contains the email ID of the employee user.
 */
public class EmployeeModel {
    private String emailId;

}
