package com.kodilla.ecommercee.exception;

public class UserNotFoundException extends Exception {

    public UserNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
