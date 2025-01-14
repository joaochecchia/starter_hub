package com.example.StarterHub.infra.Mapper;

import com.example.StarterHub.core.domain.UserProperties;
import com.example.StarterHub.core.domain.Users;
import com.example.StarterHub.core.validation.EditRequest;
import com.example.StarterHub.infra.DTO.UserPropertiesDTO;
import com.example.StarterHub.infra.DTO.UsersDTO;
import com.example.StarterHub.infra.persistence.entities.UserModel;
import com.example.StarterHub.infra.persistence.entities.UserPropertiesModel;
import org.apache.catalina.User;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.UUID;

@Component
public class UserPropertiesMapper {
    public UserProperties toDomain(UserPropertiesDTO dto, UUID userId){
        if(dto == null) return null;

        Users user = new Users(
                userId,
                dto.userModel().getUsername(),
                dto.userModel().getPassword(),
                dto.userModel().getEmail(),
                dto.userModel().getPhoneNumber(),
                null
        );

        return new UserProperties(
                dto.id(),
                dto.description(),
                dto.photo(),
                dto.company(),
                user,
                null,
                null
        );
    }

    public UserProperties toDomain(UserPropertiesModel model, UUID userId){
        if(model == null) return null;

        Users user = new Users(
                userId,
                model.getUserModel().getUsername(),
                model.getUserModel().getPassword(),
                model.getUserModel().getEmail(),
                model.getUserModel().getPhoneNumber(),
                null
        );

        return new UserProperties(
                model.getId(),
                model.getDescription(),
                model.getPhoto(),
                model.getCompany(),
                user,
                null,
                null
        );
    }

    public UserPropertiesModel toEntity(UserProperties domain){
        if(domain == null) return null;

        UserModel userModel = new UserModel();
        userModel.setId(domain.user().id());
        userModel.setEmail(domain.user().email());
        userModel.setPassword(domain.user().password());
        userModel.setUsername(domain.user().username());
        userModel.setPhoneNumber(domain.user().phone());
        userModel.setUserPropertiesModel(null);


        UserPropertiesModel model = new UserPropertiesModel();
        model.setId(domain.id());
        model.setDescription(domain.description());
        model.setPhoto(domain.photo());
        model.setUserModel(userModel);
        model.setRepositoryModel(null);
        model.setLinkModel(null);

        return model;
    }

    public UserPropertiesDTO toDTO(UserProperties domain){
        if(domain == null) return null;

        UserModel usersDTO = new UserModel(
                domain.user().id(),
                domain.user().username(),
                domain.user().password(),
                domain.user().email(),
                domain.user().phone(),
                null
        );

        return new UserPropertiesDTO(
                domain.id(),
                domain.description(),
                domain.photo(),
                domain.company(),
                usersDTO,
                null,
                null
        );
    }

    public UserPropertiesModel toModel(EditRequest request){
        if(request == null) return null;

        byte[] photo = null;
        if(request.encodedPhoto() != null){
            photo = Base64.getDecoder().decode(request.encodedPhoto());
        }

        UserModel userModel = new UserModel();
        userModel.setId(request.user().id());
        userModel.setUsername(request.user().username());
        userModel.setPhoneNumber(request.user().phone());
        userModel.setEmail(request.user().email());
        userModel.setPassword(request.user().password());
        userModel.setUserPropertiesModel(null);

        UserPropertiesModel model = new UserPropertiesModel();
        model.setId(request.id());
        model.setDescription(request.description());
        model.setPhoto(photo);
        model.setUserModel(userModel);
        model.setRepositoryModel(null);
        model.setLinkModel(null);

        return model;
    }
}
