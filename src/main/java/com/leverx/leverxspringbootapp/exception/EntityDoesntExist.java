package com.leverx.leverxspringbootapp.exception;

public class EntityDoesntExist extends RuntimeException {

    public EntityDoesntExist() {
        super();
    }

    public EntityDoesntExist(String message) {
        super(message);
    }

    public EntityDoesntExist(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityDoesntExist(Throwable cause) {
        super(cause);
    }

    protected EntityDoesntExist(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
