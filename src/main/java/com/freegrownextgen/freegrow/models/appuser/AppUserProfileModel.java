package com.freegrownextgen.freegrow.models.appuser;

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
    private String address;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private String profilePictureUrl;      
}
