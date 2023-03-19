package com.kodilla.ecommercee.exception;

public class ProductNotFoundException extends Exception {

    public ProductNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
