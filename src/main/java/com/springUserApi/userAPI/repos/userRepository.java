package com.springUserApi.userAPI.repos;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.springUserApi.userAPI.models.user;

@Repository
public interface userRepository extends MongoRepository<user, String> {
    

   public user findByEmailId(String emailId);
   
    
} 