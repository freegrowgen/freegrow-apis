package com.freegrownextgen.freegrow.models.freelancer;

import org.springframework.data.mongodb.core.mapping.Document;

import com.freegrownextgen.freegrow.models.employee.EmployeeProfileModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "freelancer_profiles")
public class FreelancerProfileModel extends EmployeeProfileModel {

}
