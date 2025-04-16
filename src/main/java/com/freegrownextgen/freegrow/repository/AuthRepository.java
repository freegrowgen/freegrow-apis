package com.freegrownextgen.freegrow.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

import com.freegrownextgen.freegrow.models.appuser.AppUserModel;


@Repository
public interface AuthRepository extends MongoRepository<AppUserModel, String> {
    
    public AppUserModel findByEmailId(String emailId);

    @Query("{ 'emailId' : ?0 }")
    @Update("{ '$set' : { 'otp' : ?1 } }")
    int findAndUpdateOtp(String emailId, int otp);

}
