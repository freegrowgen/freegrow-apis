package com.freegrownextgen.freegrow.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import com.freegrownextgen.freegrow.models.utils.TempAppUserModel;

public interface TempAuthRepository extends MongoRepository<TempAppUserModel,String> {

    TempAppUserModel findByEmailId(String emailId);
    TempAppUserModel deleteByEmailId(String emailId);

    @Query("{ 'emailId' : ?0 }")
    @Update("{ '$set' : { 'otp' : ?1 } }")
    int findAndUpdateOtp(String emailId, int otp);



    
}
