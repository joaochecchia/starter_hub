package com.example.StarterHub.core.useCases.UserProperties;

import com.example.StarterHub.core.domain.UserProperties;
import com.example.StarterHub.core.gateway.UserPropertiesGateway;
import com.example.StarterHub.core.validation.EditRequest;

import java.util.Optional;
import java.util.UUID;

public class EditUserPropertiesUseCaseImp implements EditUserPropertiesUseCase{

    private final UserPropertiesGateway userPropertiesGateway;

    public EditUserPropertiesUseCaseImp(UserPropertiesGateway userPropertiesGateway) {
        this.userPropertiesGateway = userPropertiesGateway;
    }

    @Override
    public Optional<UserProperties> execute(UUID id, EditRequest editRequest) {
        return userPropertiesGateway.editUserProperties(id ,editRequest);
    }
}
