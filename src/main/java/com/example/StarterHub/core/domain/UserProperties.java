package com.example.StarterHub.core.domain;

import java.util.ArrayList;
import java.util.UUID;

public record UserProperties(
        UUID id,
        String description,
        byte[] photo,
        String company,
        ArrayList<Links> links,
        Address address,
        ArrayList<Repository> repositories,
        Users users
) { }