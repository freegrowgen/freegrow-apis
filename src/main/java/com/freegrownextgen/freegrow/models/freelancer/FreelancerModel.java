package com.freegrownextgen.freegrow.models.freelancer;

import org.springframework.data.mongodb.core.mapping.Document;

import com.freegrownextgen.freegrow.models.appuser.AppUserModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "freelancers")

public class FreelancerModel extends AppUserModel {

}
