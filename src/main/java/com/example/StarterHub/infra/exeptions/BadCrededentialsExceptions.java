package com.example.StarterHub.infra.exeptions;

public class BadCrededentialsExceptions extends RuntimeException{
    public BadCrededentialsExceptions(String message){
        super(message);
    }
}
