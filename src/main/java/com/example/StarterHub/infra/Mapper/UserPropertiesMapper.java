package com.example.StarterHub.infra.Mapper;

import com.example.StarterHub.core.domain.UserProperties;
import com.example.StarterHub.core.domain.Users;
import com.example.StarterHub.infra.DTO.UserPropertiesDTO;
import com.example.StarterHub.infra.persistence.entities.UserModel;
import com.example.StarterHub.infra.persistence.entities.UserPropertiesModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class UserPropertiesMapper {

    public UserPropertiesDTO toDTO(UserPropertiesModel model) {
        if (model == null) return null;

        return new UserPropertiesDTO(
                model.getId(),
                model.getDescription(),
                model.getPhoto(),
                model.getCompany(),
                model.getUserModel(),
                null,
                null
        );
    }

    public UserPropertiesDTO toDTO(UserProperties domain){
        if (domain == null) return null;
        UserModel temp = new UserModel();
        temp.setId(domain.id());

        return new UserPropertiesDTO(
                domain.id(),
                domain.description(),
                domain.photo(),
                domain.company(),
                temp,
                null,
                null
        );
    }

    public UserPropertiesModel toEntity(UserPropertiesDTO dto) {
        if (dto == null) return null;

        UserPropertiesModel model = new UserPropertiesModel();
        model.setId(dto.id());
        model.setDescription(dto.description());
        model.setPhoto(dto.photo());
        model.setCompany(dto.company());
        model.setUserModel(dto.userModel());
        model.setLinkModel(new ArrayList<>());
        model.setAddressModel(null);
        model.setRepositoryModel(new ArrayList<>());

        return model;
    }

    public UserProperties toDomain(UserPropertiesDTO dto) {
        if (dto == null) return null;
        System.out.println("ESSE E O ID DO MAPPER: " + dto.userModel().getId());
        Users temp = new Users(
                dto.userModel().getId(),
                null,
                null,
                null,
                null,
                null
        );


        return new UserProperties(
                dto.id(),
                dto.description(),
                dto.photo(),
                dto.company(),
                temp,
                null,
                null
        );
    }

    public UserProperties toDomain(UserPropertiesModel model) {
        if (model == null) return null;
        Users temp = new Users(
                model.getUserModel().getId(),
                null,
                null,
                null,
                null,
                null
        );

        return new UserProperties(
                model.getId(),
                model.getDescription(),
                model.getPhoto(),
                model.getCompany(),
                temp,
                null,
                null
        );
    }

    public UserPropertiesModel fromDomain(UserProperties domain) {
        if (domain == null) return null;
        UserModel temp = new UserModel();
        temp.setId(domain.user().id());

        UserPropertiesModel model = new UserPropertiesModel();
        model.setId(domain.id());
        model.setDescription(domain.description());
        model.setPhoto(domain.photo());
        model.setCompany(domain.company());
        model.setUserModel(temp);
        model.setLinkModel(new ArrayList<>());
        model.setAddressModel(null);
        model.setRepositoryModel(new ArrayList<>());

        return model;
    }
}
