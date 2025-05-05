package com.freegrownextgen.freegrow.models.employee;

import org.springframework.data.mongodb.core.mapping.Document;

import com.freegrownextgen.freegrow.models.appuser.AppUserModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "employees")
public class EmployeeModel extends AppUserModel {

}
