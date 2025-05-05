package com.freegrownextgen.freegrow.models.client;

import org.springframework.data.mongodb.core.mapping.Document;

import com.freegrownextgen.freegrow.models.appuser.AppUserModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Document(collation = "clients")
public class ClientModel extends AppUserModel {

}
