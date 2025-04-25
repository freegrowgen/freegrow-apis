package com.freegrownextgen.freegrow.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressModel {
    private String addressLine1;
    private String addressLine2; 
    private String city;
    private String district;
    private String stateOrProvince;
    private String postalCode;
    private String country;
    private String countryCode;

    private String timeZone;
}