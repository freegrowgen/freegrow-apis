package com.freegrownextgen.freegrow.models.appuser;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import com.freegrownextgen.freegrow.enums.AccountStatusEnum;
import com.freegrownextgen.freegrow.enums.MembershipPlanEnums;
import com.freegrownextgen.freegrow.enums.RoleEnum;
import com.freegrownextgen.freegrow.models.utils.ResetPassword;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "app_users")
/**
 * AppUserModel is a model class that represents an application user.
 * It contains various fields related to the user's information.
 */
public class AppUserModel {
    private String firstName;
    private String lastName;
    private String emailId;
    private String password;
    private String mobileNumber;
    private RoleEnum role;
    private AccountStatusEnum accountStatus;
    private Integer otp;
    private boolean googleSignUp;
    private String profileUrl;
    private boolean firstTimeLogin = true;
    ResetPassword resetPassword;
    private String userName;
    private MembershipPlanEnums memeberShipPlan = MembershipPlanEnums.FREE;
    private long followers;
    private long likes;
    private String noOfjobs;
    private String noOfActiveJobs;
    private String noOfCompletedJobs;
    private String totalSpend;
    private float averageSpendPerHour;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;
}
