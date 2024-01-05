package com.application.exceptions;

public class CustomerNotFoundException extends RuntimeException{

    public CustomerNotFoundException(String customerId) {
        super("Customer with id " + customerId + " not found");
    }
}
