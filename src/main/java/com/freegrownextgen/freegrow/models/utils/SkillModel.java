package com.freegrownextgen.freegrow.models.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * SkillModel is a model class that represents the skills of a user.
 * It contains various fields related to the user's skills and certifications.
 */
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
