package com.example.StarterHub.infra.DTO;

import com.example.StarterHub.core.domain.Address;
import com.example.StarterHub.core.domain.Links;
import com.example.StarterHub.core.domain.Repository;
import com.example.StarterHub.infra.persistence.entities.AddressModel;
import com.example.StarterHub.infra.persistence.entities.LinkModel;
import com.example.StarterHub.infra.persistence.entities.RepositoryModel;
import com.example.StarterHub.infra.persistence.entities.UserModel;
import org.apache.catalina.User;

import java.util.ArrayList;
import java.util.UUID;

public record UserPropertiesDTO(
        UUID id,
        String description,
        byte[] photo,
        String company,
        UserModel userModel,
        ArrayList<LinkModel> linksModel,
        AddressModel addressModel,
        ArrayList<RepositoryModel> repositoryModels
) { }
