package com.application.exceptions;

public class CustomerExistsException extends RuntimeException {
    public CustomerExistsException(String message) {
        super("Customer with id " + message + " already exists");
    }
}
