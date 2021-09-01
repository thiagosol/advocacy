package com.advocacy.advocacysystem.infrastructure.exception;

public class ObjectNotExistException extends RuntimeException {

    private final static String messageOther = "%s, id %d, not found";
    private final static String message = "Id %d not found";


    public ObjectNotExistException(String className, Long id) {
        super(String.format(messageOther, className, id));
    }

    public ObjectNotExistException(Long id) {
        super(String.format(message, id));
    }

}
