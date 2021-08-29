package com.advocacy.advocacysystem.infrastructure.exception;

public class CustomerNotExistException extends RuntimeException {

    private final static String message = "Customer, id %d, not found";

    public CustomerNotExistException(Long id) {
        super(String.format(message, id));
    }

}
