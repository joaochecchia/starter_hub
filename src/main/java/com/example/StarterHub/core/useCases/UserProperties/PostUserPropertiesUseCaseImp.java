package com.example.StarterHub.core.useCases.UserProperties;

import com.example.StarterHub.core.domain.UserProperties;
import com.example.StarterHub.core.gateway.UserPropertiesGateway;

import java.util.Optional;

public class PostUserPropertiesUseCaseImp implements PostUserPropertiesUseCase{

    private final UserPropertiesGateway userPropertiesGateway;

    public PostUserPropertiesUseCaseImp(UserPropertiesGateway userPropertiesGateway) {
        this.userPropertiesGateway = userPropertiesGateway;
    }

    @Override
    public Optional<UserProperties> execute(UserProperties userProperties) {
        return userPropertiesGateway.postUserProperties(userProperties);
    }
}
