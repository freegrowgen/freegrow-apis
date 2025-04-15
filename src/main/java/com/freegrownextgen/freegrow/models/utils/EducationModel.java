package com.freegrownextgen.freegrow.models.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EducationModel {
    private String degree;
    private String institution;
    private String yearOfPassing;
    private String percentage;
    private String specialization;
    private String location;
    private String description;
    
}
