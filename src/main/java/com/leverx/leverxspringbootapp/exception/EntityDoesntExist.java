package com.leverx.leverxspringbootapp.exception;

public class EntityDoesntExist extends RuntimeException {

    public EntityDoesntExist(String message) {
        super(message);
    }
}
