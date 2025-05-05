package com.freegrownextgen.freegrow.models.utils;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "postal_data")
public class PostalDetailsModel {
    private String stateName;
    private String districtName;
    private Integer pincode;
    private List<PostalPostOfficeModel> postOffice;

}
