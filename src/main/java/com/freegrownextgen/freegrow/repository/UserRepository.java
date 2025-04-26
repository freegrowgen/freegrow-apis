package com.freegrownextgen.freegrow.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import com.freegrownextgen.freegrow.enums.RoleEnum;
import com.freegrownextgen.freegrow.models.appuser.AppUserModel;

public interface UserRepository extends MongoRepository<AppUserModel, String> {
    public AppUserModel findByEmailId(String emailId);

    @Query("{ 'userName' : ?0 }")
    AppUserModel findByUserName(String userName);

    @Query("{ 'emailId' : ?0 }")
    @Update("{ '$set' : { 'role' : ?1,'firstTimeLogin':?2 } }")
    int findAndUpdateByEmailId(String emailId,RoleEnum role,boolean firstTimeLogin);
    

}
