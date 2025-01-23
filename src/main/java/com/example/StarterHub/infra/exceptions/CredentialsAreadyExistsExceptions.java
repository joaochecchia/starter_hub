package com.example.StarterHub.infra.exceptions;

public class CredentialsAreadyExistsExceptions extends RuntimeException {
    public CredentialsAreadyExistsExceptions(String message) {
        super(message);
    }
}
