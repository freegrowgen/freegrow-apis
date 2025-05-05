package com.freegrownextgen.freegrow.models.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostalPostOfficeModel {
    private String name;
    private String country;
    private String state;
    private String taluk;
    private String District;
    private String Divison;
    private String Circle;
    private String branchType;
    private String deliveryStatus;
    private String Description;

}
