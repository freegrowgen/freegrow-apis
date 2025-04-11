package com.freegrownextgen.freegrow.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freegrownextgen.freegrow.enums.ResponseEnums;
import com.freegrownextgen.freegrow.implementations.AuthImpl;
import com.freegrownextgen.freegrow.repository.AuthRepository;


@Service
public class AuthServices implements AuthImpl {

    @Autowired
    private AuthRepository authRepo;
    
    @Override
    public ResponseEnums signUP() {
        
        System.out.println(authRepo.findAll());
        return ResponseEnums.SUCCESS;
    }

}
