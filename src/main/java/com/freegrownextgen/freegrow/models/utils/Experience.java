package com.freegrownextgen.freegrow.models.utils;

import com.freegrownextgen.freegrow.enums.EmployeeExperienceStatusEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * Experience is a model class that represents the work experience of a user.
 * It contains various fields related to the user's work experience.
 */
public class Experience {
    private String companyName;
    private String designation;
    private String startDate;
    private String endDate;
    private String location;
    private String description;
    private String employmentType;
    private String industry;
    private String salary;
    private String reasonForLeaving;
    private String employeeId;
    private EmployeeExperienceStatusEnum status;
}
