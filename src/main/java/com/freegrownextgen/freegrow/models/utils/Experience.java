package com.freegrownextgen.freegrow.models.utils;

import com.freegrownextgen.freegrow.enums.EmployeeExperienceStatusEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
