package com.example.StarterHub.infra.DTO;

import com.example.StarterHub.core.domain.Address;
import com.example.StarterHub.core.domain.Links;
import org.apache.catalina.User;

import java.util.ArrayList;
import java.util.UUID;

public record UserPropertiesDTO(
        UUID id,
        String description,
        byte[] photo,
        String company,
        UsersDTO userDTO,
        ArrayList<LinksDTO> linksDTO,
        AddressDTO addressDTO
) { }
