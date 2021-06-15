package com.leverx.leverxspringbootapp.exception;

public class EntityIsDead extends RuntimeException{
    public EntityIsDead() {
        super();
    }

    public EntityIsDead(String message) {
        super(message);
    }

    public EntityIsDead(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityIsDead(Throwable cause) {
        super(cause);
    }

    protected EntityIsDead(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
