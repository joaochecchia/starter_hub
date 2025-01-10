package com.example.StarterHub.infra.Mapper;

import com.example.StarterHub.core.domain.Users;
import com.example.StarterHub.infra.DTO.UsersDTO;
import com.example.StarterHub.infra.persistence.entities.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UsersMapper {

    public static UsersDTO toDTO(UserModel userModel) {
        if (userModel == null) return null;

        return new UsersDTO(
                userModel.getId(),
                userModel.getUsername(),
                userModel.getPassword(),
                userModel.getEmail(),
                userModel.getPhoneNumber(),
                null
        );
    }

    public static UsersDTO toDTO(Users users) {
        if (users == null) return null;

        return new UsersDTO(
                users.id(),
                users.username(),
                users.password(),
                users.email(),
                users.phone(),
                null
        );
    }

    public static UserModel toEntity(UsersDTO usersDTO) {
        if (usersDTO == null) return null;

        UserModel userModel = new UserModel();
        userModel.setId(usersDTO.id());
        userModel.setUsername(usersDTO.username());
        userModel.setPassword(usersDTO.password());
        userModel.setEmail(usersDTO.email());
        userModel.setPhoneNumber(usersDTO.phone());

        return userModel;
    }

    public static Users toDomain(UsersDTO dto) {
        if (dto == null) return null;

        return new Users(
                dto.id(),
                dto.username(),
                dto.password(),
                dto.email(),
                dto.phone(),
                null
        );
    }

    public static UserModel fromDomain(Users users) {
        if (users == null) return null;

        UserModel userModel = new UserModel();
        userModel.setId(users.id());
        userModel.setUsername(users.username());
        userModel.setPassword(users.password());
        userModel.setEmail(users.email());
        userModel.setPhoneNumber(users.phone());

        return userModel;
    }
}
