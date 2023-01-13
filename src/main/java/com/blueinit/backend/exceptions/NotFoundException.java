package com.blueinit.backend.exceptions;

public class NotFoundException extends RuntimeException {

    private String message;
    public NotFoundException(String message) {
        super(message);
        this.message = message;
    }
}


