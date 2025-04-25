package com.freegrownextgen.freegrow.models.job;

import java.time.Instant;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import com.freegrownextgen.freegrow.enums.jobs.JobWorkModeEnums;
import com.freegrownextgen.freegrow.enums.jobs.PostedByEnums;
import com.freegrownextgen.freegrow.enums.ExperienceEnums;
import com.freegrownextgen.freegrow.enums.jobs.JobTypeEnums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collation = "posted_jobs")
public class JobModel {
    private JobTypeEnums jobType;
    private JobWorkModeEnums jobWorkMode;
    private PostedByEnums postedBy;
    private String postedByEmailId;
    private String title;
    private Object description;
    private String location;
    private boolean isStrictLocation;
    private String salary;
    private String currency;
    private boolean isCostPerHour;
    private Integer costPerHour;
    private List<String> langugaes;
    private List<String> skills;
    private ExperienceEnums experienceType;
    private Integer experience;
    private boolean isActive;
    private Integer saved;
    private Integer applied;
    private Integer interviewing;
    private boolean isVerified;
    private Integer noOfOpenings;
    @CreatedDate
    private Instant createdAt;
    @LastModifiedDate
    private Instant updatedAt;

}
