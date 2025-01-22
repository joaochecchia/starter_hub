package com.example.StarterHub.infra.exeptions;

public class CredentialsAreadyExistsExceptions extends RuntimeException {
    public CredentialsAreadyExistsExceptions(String message) {
        super(message);
    }
}
