package com.example.StarterHub.core.useCases.User;

import com.example.StarterHub.core.gateway.UserPropertiesGateway;
import com.example.StarterHub.core.gateway.UsersGateway;

import java.util.UUID;

public class DeleteUsersUseCaseImp implements DeleteUsersUseCase{

    public final UsersGateway usersGateway;

    public DeleteUsersUseCaseImp(UsersGateway usersGateway){
        this.usersGateway = usersGateway;
    }

    @Override
    public String execute(UUID id) {
        return usersGateway.deleteUsers(id);
    }
}
