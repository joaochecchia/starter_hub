package com.example.StarterHub.core.useCases.UserProperties;

import com.example.StarterHub.core.gateway.UserPropertiesGateway;

import java.util.UUID;

public class DeleteUserPropertiesUseCaseImp implements DeleteUserPropertiesUseCase{

    private final UserPropertiesGateway userPropertiesGateway;

    public DeleteUserPropertiesUseCaseImp(UserPropertiesGateway userPropertiesGateway) {
        this.userPropertiesGateway = userPropertiesGateway;
    }

    @Override
    public String execute(UUID id) {
        return userPropertiesGateway.deleteUserProperties(id);
    }
}
