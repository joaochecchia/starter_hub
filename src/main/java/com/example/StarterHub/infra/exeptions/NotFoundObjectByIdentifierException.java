package com.example.StarterHub.infra.exeptions;

public class NotFoundObjectByIdentifierException extends RuntimeException {
    public NotFoundObjectByIdentifierException(String message) {
        super(message);
    }
}
