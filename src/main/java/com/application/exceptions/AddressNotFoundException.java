package com.application.exceptions;

public class AddressNotFoundException extends RuntimeException{

    public AddressNotFoundException(String message) {
        super("Address with id " + message + " not found");
    }
}
