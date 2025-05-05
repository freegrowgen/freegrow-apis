package com.freegrownextgen.freegrow.models.employee;

import java.time.Instant;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import com.freegrownextgen.freegrow.models.utils.DesignationModel;
import com.freegrownextgen.freegrow.models.utils.EducationModel;
import com.freegrownextgen.freegrow.models.utils.Experience;
import com.freegrownextgen.freegrow.models.utils.SkillModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "employee_profiles")
public class EmployeeProfileModel {
    private String employeeId;
    private String department;
    private DesignationModel designation;
    private String joiningDate;
    private String employeeStatus;
    private String employeeType;
    private String reportingManager;
    private String employeeCode;
    private String employeeLocation;
    private String employeeShift;
    private String employeeSalary;
    private List<Experience> employeeExperience;
    private List<SkillModel> employeeSkills;
    private List<EducationModel> employeeEducation;
    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;
}
