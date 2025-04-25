package com.freegrownextgen.freegrow.models.appuser;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * AppUserProfileModel is a model class that represents the profile of an application user.
 * It contains various fields related to the user's profile information.
 */
public class AppUserProfileModel {
    private String firstName;
    private String lastName;
    private String emailId;
    private String phoneNumber;
    private String profilePictureUrl;      
     @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;
}
