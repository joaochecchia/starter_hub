package com.example.StarterHub.infra.exceptions;

public class BadCrededentialsExceptions extends RuntimeException{
    public BadCrededentialsExceptions(String message){
        super(message);
    }
}
