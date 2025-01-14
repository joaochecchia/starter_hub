package com.example.StarterHub.core.validation;

import com.example.StarterHub.core.domain.Address;
import com.example.StarterHub.core.domain.Links;
import com.example.StarterHub.core.domain.Users;

import java.util.ArrayList;
import java.util.UUID;

public record EditRequest(
        UUID id,
        String description,
        String encodedPhoto,
        String company,
        Users user,
        ArrayList<Links> links,
        Address address
) { }
