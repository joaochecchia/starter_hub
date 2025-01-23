package com.example.StarterHub.infra.exceptions;

public class NotFoundObjectByIdentifierException extends RuntimeException {
    public NotFoundObjectByIdentifierException(String message) {
        super(message);
    }
}
