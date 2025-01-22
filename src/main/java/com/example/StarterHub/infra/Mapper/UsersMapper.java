package com.example.StarterHub.infra.Mapper;

import com.example.StarterHub.core.domain.Users;
import com.example.StarterHub.infra.persistence.entities.UserModel;
import com.example.StarterHub.infra.requests.create.CreateUserRequest;
import org.springframework.stereotype.Component;

@Component
public class UsersMapper {

    public Users toDomain(CreateUserRequest request){
        return new Users(
                null,
                request.username(),
                request.email(),
                request.password(),
                request.phone()
        );
    }

    public Users toDomain(UserModel model){
        return new Users(
                model.getId(),
                model.getUsername(),
                model.getPassword(),
                model.getEmail(),
                model.getPhoneNumber()
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
}
