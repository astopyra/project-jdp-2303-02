package com.kodilla.ecommercee.exception;

public class OrderNotFoundException extends Exception{

    public OrderNotFoundException (String errorMessage) {
        super(errorMessage);
    }
}
