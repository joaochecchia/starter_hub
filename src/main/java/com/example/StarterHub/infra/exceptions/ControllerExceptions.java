package com.example.StarterHub.infra.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.StreamSupport;

@RestControllerAdvice
public class ControllerExceptions {
    @ExceptionHandler(BadCrededentialsExceptions.class)
    public ResponseEntity<Map<String, String>> handleBadCrededentialsExceptions(BadCrededentialsExceptions ex){
            Map<String, String> response = new HashMap<>();
            response.put("Error: ", ex.getMessage());

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CredentialsAreadyExistsExceptions.class)
    public ResponseEntity<Map<String, String>> handleCredentialsAlreadyExistsException(CredentialsAreadyExistsExceptions ex){
        Map<String, String> response = new HashMap<>();
        response.put("Error: ", ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundObjectByIdentifierException.class)
    public ResponseEntity<Map<String, String>> handleNotFoundObjectByIdentifierException(NotFoundObjectByIdentifierException ex){
        Map<String, String> response = new HashMap<>();
        response.put("Error: ", ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleArgumentNotValidException(MethodArgumentNotValidException e){
        Map<String, String> errors = new HashMap<>();

        e.getBindingResult().getAllErrors().forEach((error) -> {
            errors.put(((FieldError) error).getField(), error.getDefaultMessage());
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUsernameNotFoundException(UsernameNotFoundException ex){
        Map<String, String> errors = new HashMap<>();

        errors.put("Error: ", ex.getMessage());

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}