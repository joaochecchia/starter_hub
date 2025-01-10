package com.example.StarterHub.infra.Mapper;

import com.example.StarterHub.core.domain.UserProperties;
import com.example.StarterHub.core.domain.Users;
import com.example.StarterHub.core.domain.Links;
import com.example.StarterHub.core.domain.Address;
import com.example.StarterHub.infra.DTO.UserPropertiesDTO;
import com.example.StarterHub.infra.persistence.entities.UserPropertiesModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class UserPropertiesMapper {

    public static UserPropertiesDTO toDTO(UserPropertiesModel model) {
        if (model == null) return null;

        return new UserPropertiesDTO(
                model.getId(),
                model.getDescription(),
                model.getPhoto(),
                model.getCompany(),
                null,
                null,
                null
        );
    }

    public static UserPropertiesModel toEntity(UserPropertiesDTO dto) {
        if (dto == null) return null;

        UserPropertiesModel model = new UserPropertiesModel();
        model.setId(dto.id());
        model.setDescription(dto.description());
        model.setPhoto(dto.photo());
        model.setCompany(dto.company());
        model.setUserModel(null);
        model.setLinkModel(new ArrayList<>());
        model.setAddressModel(null);
        model.setRepositoryModel(new ArrayList<>());

        return model;
    }

    public static UserProperties toDomain(UserPropertiesDTO dto) {
        if (dto == null) return null;

        return new UserProperties(
                dto.id(),
                dto.description(),
                dto.photo(),
                dto.company(),
                null,
                null,
                null
        );
    }

    public static UserPropertiesModel fromDomain(UserProperties domain) {
        if (domain == null) return null;

        UserPropertiesModel model = new UserPropertiesModel();
        model.setId(domain.id());
        model.setDescription(domain.description());
        model.setPhoto(domain.photo());
        model.setCompany(domain.company());
        model.setUserModel(null);
        model.setLinkModel(new ArrayList<>());
        model.setAddressModel(null);
        model.setRepositoryModel(new ArrayList<>());

        return model;
    }
}
