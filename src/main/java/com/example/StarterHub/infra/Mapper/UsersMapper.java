package com.example.StarterHub.infra.Mapper;

import com.example.StarterHub.core.domain.Users;
import com.example.StarterHub.infra.DTO.UserPropertiesDTO;
import com.example.StarterHub.infra.DTO.UsersDTO;
import com.example.StarterHub.infra.persistence.entities.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UsersMapper {
    public Users toDomain(UsersDTO dto){
        return new Users(
                dto.id(),
                dto.username(),
                dto.password(),
                dto.email(),
                dto.phone(),
                null
        );
    }

    public Users toDomain(UserModel model){
        return new Users(
                model.getId(),
                model.getUsername(),
                model.getPassword(),
                model.getEmail(),
                model.getPhoneNumber(),
                null
        );
    }

    public UserModel toEntity(Users domain){
        return new UserModel(
                domain.id(),
                domain.username(),
                domain.password(),
                domain.email(),
                domain.email(),
                null
        );
    }

    public UsersDTO toDTO(Users domain){
        return new UsersDTO(
                domain.id(),
                domain.username(),
                domain.password(),
                domain.email(),
                domain.phone(),
                null
        );
    }
}
