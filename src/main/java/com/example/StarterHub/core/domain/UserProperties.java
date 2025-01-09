package com.example.StarterHub.core.domain;

import org.apache.catalina.User;

import java.util.ArrayList;
import java.util.UUID;

public record UserProperties(
        UUID id,
        String description,
        byte[] photo,
        String company,
        User user,
        ArrayList<Links> links,
        Address address
) { }
