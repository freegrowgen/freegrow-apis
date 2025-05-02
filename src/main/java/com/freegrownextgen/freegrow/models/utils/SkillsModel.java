package com.freegrownextgen.freegrow.models.utils;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "skills_data")
public class SkillsModel {
    private long id;
    private String name;

}
