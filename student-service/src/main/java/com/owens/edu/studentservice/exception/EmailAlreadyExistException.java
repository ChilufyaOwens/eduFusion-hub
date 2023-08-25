package com.owens.edu.studentservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EmailAlreadyExistException extends RuntimeException{
    private String message;
    public EmailAlreadyExistException(String message){
        super(message);
        this.message = message;
    }
}
