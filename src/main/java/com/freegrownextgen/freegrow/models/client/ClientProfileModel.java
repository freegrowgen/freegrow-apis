package com.freegrownextgen.freegrow.models.client;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import com.freegrownextgen.freegrow.models.utils.AddressModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "client_profiles")
public class ClientProfileModel {
    private String emailId;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String currency;
    private boolean isVerified;
    private String profileUrl;
    private AddressModel addressDetails;
    private String title;
    private String description;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;
}
