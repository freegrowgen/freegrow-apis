package com.freegrownextgen.freegrow.handlers;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class Responsehandlers {
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> badRequest() {
        return ResponseEntity.status(400).body("Bad Request");
    }
    
}