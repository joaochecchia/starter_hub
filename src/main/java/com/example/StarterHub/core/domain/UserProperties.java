package com.example.StarterHub.core.domain;

import java.util.ArrayList;
import java.util.UUID;

public record UserProperties(
        UUID id,
        String description,
        byte[] photo,
        String company,
        Users user,
        ArrayList<Links> links,
        Address address
) { }
