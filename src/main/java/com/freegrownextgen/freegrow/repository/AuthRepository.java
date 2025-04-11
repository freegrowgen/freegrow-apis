package com.freegrownextgen.freegrow.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.freegrownextgen.freegrow.models.User;

@Repository 
public interface  AuthRepository  extends MongoRepository<User, String> {
    
}
