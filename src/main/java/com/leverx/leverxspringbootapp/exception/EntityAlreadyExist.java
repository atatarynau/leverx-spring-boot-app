package com.leverx.leverxspringbootapp.exception;

public class EntityAlreadyExist extends RuntimeException {

    public EntityAlreadyExist(String message) {
        super(message);
    }
}
