package com.leverx.leverxspringbootapp.exception;

public class EntityAlreadyExist extends RuntimeException {

    public EntityAlreadyExist() {
        super();
    }

    public EntityAlreadyExist(String message) {
        super(message);
    }

    public EntityAlreadyExist(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityAlreadyExist(Throwable cause) {
        super(cause);
    }

    protected EntityAlreadyExist(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
