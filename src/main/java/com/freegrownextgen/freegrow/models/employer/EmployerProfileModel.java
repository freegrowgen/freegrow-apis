package com.freegrownextgen.freegrow.models.employer;

import org.springframework.data.mongodb.core.mapping.Document;

import com.freegrownextgen.freegrow.enums.employer.EmployerDesignationEnums;
import com.freegrownextgen.freegrow.models.client.ClientProfileModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Document(collection = "employer_profiles")
public class EmployerProfileModel extends ClientProfileModel {
    private String companyName;
    private String companyDescriiption;
    private EmployerDesignationEnums designation;
    private String companyUrl;

}
