package com.springUserApi.repos;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mongodb.client.MongoDatabase;
import com.springUserApi.models.user;


@Repository
public interface userRepository extends MongoRepository<user,String> {
    
} 