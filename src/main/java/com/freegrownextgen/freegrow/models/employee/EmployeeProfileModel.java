package com.freegrownextgen.freegrow.models.employee;

import java.time.Instant;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.freegrownextgen.freegrow.models.appuser.AppUserProfileModel;
import com.freegrownextgen.freegrow.models.utils.EducationModel;
import com.freegrownextgen.freegrow.models.utils.Experience;
import com.freegrownextgen.freegrow.models.utils.SkillModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
/**
 * EmployeeProfileModel is a data model class that represents the profile of an employee.
 * It extends the AppUserProfileModel class and includes additional fields specific to employees.
 */
public class EmployeeProfileModel extends AppUserProfileModel {
    private String employeeId;
    private String department;
    private String designation;
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
