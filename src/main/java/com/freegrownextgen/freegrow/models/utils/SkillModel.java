package com.freegrownextgen.freegrow.models.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkillModel {
    private String skillName;
    private String skillDescription;
    private Integer skillLevel;
    private String skillProficiency;
    private String skillExperience;
    private String skillCertification;
    private String skillCertificationUrl;
    private String skillCertificationDate;
    private String skillCertificationExpiryDate;
    private String skillCertificationStatus;
    private String skillCertificationAuthority;
}
