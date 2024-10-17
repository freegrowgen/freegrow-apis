package com.springUserApi.userAPI.handlers;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class userControllerHandlers {
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> badRequest() {
        return ResponseEntity.status(400).body("Bad Request");
    }   
}
