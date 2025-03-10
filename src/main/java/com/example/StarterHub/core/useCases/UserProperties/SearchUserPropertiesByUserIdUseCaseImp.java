package com.example.StarterHub.core.useCases.UserProperties;

import com.example.StarterHub.core.domain.UserProperties;
import com.example.StarterHub.core.gateway.UserPropertiesGateway;

import java.util.Optional;
import java.util.UUID;

public class SearchUserPropertiesByUserIdUseCaseImp implements SearchUserPropertiesByUserIdUseCase{

    private final UserPropertiesGateway userPropertiesGateway;

    public SearchUserPropertiesByUserIdUseCaseImp(UserPropertiesGateway userPropertiesGateway) {
        this.userPropertiesGateway = userPropertiesGateway;
    }

    @Override
    public Optional<UserProperties> execute(UUID id) {
        return userPropertiesGateway.searchUserPropertiesByUserId(id);
    }
}
