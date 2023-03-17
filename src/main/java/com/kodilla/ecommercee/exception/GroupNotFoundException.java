package com.kodilla.ecommercee.exception;

public class GroupNotFoundException extends Exception {
    public GroupNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
