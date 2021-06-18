package com.leverx.leverxspringbootapp.exception;

public class EntityIsDead extends RuntimeException {

    public EntityIsDead(String message) {
        super(message);
    }
}
