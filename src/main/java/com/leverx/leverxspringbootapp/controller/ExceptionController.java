package com.leverx.leverxspringbootapp.controller;


import com.leverx.leverxspringbootapp.exception.EntityAlreadyExist;
import com.leverx.leverxspringbootapp.exception.EntityDoesntExist;
import com.leverx.leverxspringbootapp.exception.EntityIsDead;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {

        Map<String, String> errors = new HashMap<>();
        List<FieldError> fieldErrors = ex.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(EntityDoesntExist.class)
    public ResponseEntity<String> entityDoesNotExist(EntityDoesntExist ex) {

        String message = ex.getMessage();
        log.warn(message);
        return ResponseEntity.badRequest().body(message);
    }

    @ExceptionHandler(EntityAlreadyExist.class)
    public ResponseEntity<String> entityAlreadyExist(EntityAlreadyExist ex) {

        String message = ex.getMessage();
        log.warn(message);
        return ResponseEntity.badRequest().body(message);
    }

    @ExceptionHandler(EntityIsDead.class)
    public ResponseEntity<String> entityIsDead(EntityIsDead ex) {

        String message = ex.getMessage();
        log.warn(message);
        return ResponseEntity.badRequest().body(message);
    }
}
