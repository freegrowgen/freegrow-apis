package com.springUserApi.userAPI.repos;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.springUserApi.userAPI.models.User;


@Repository
public interface userRepository extends MongoRepository<User,String> {
    
} 