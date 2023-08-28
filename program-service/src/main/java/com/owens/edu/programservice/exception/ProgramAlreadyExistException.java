package com.owens.edu.programservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ProgramAlreadyExistException extends RuntimeException{
    private final String message;

    public ProgramAlreadyExistException(String message) {
        super(message);
        this.message = message;
    }
}
