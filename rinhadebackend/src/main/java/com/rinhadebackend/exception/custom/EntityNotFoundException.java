package com.rinhadebackend.exception.custom;

public class EntityNotFoundException extends RuntimeException {

    static final String DEFAULT_MESSAGE = "Entity Not Found";


    public EntityNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}