package com.freegrownextgen.freegrow.models.client;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import com.freegrownextgen.freegrow.enums.MembershipPlanEnums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collation = "clients")
public class ClientModel {
    private String emailId;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private MembershipPlanEnums meberShipPlan;
    private String profileUrl;
    private Integer noOfJobs;
    private Integer noOfActiveJobs;
    private Integer noOfCompletedJobs;
    private Integer totalSpend;
    private float averageSpendPerHour;
    private long followers;
    private long likes;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

}
