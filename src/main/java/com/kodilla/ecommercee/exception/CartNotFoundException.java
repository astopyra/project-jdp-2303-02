package com.kodilla.ecommercee.exception;

public class CartNotFoundException extends Exception {

    public CartNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
