package com.advocacy.advocacysystem.infrastructure.exception;

public class ObjectNotExistException extends RuntimeException {

    private final static String message = "%s, id %d, not found";

    public ObjectNotExistException(String className, Long id) {
        super(String.format(message, className, id));
    }

}
