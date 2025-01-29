package com.example.StarterHub.infra.beans;

import com.example.StarterHub.core.gateway.UserPropertiesGateway;
import com.example.StarterHub.core.useCases.UserProperties.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserPropertiesBeanConfigurator {

    @Bean
    public PostUserPropertiesUseCase insertUserPropertiesProperties(UserPropertiesGateway userPropertiesGateway){
        return new PostUserPropertiesUseCaseImp(userPropertiesGateway);
    }

    @Bean
    public EditUserPropertiesUseCase editUserPropertiess(UserPropertiesGateway userPropertiesGateway){
        return new EditUserPropertiesUseCaseImp(userPropertiesGateway);
    }

    @Bean
    public SearchUserPropertiesUseCase searchUserPropertiess(UserPropertiesGateway userPropertiesGateway){
        return new SearchUserPropertiesUseCaseImp(userPropertiesGateway);
    }

    @Bean
    public SearchUserPropertiesByUserIdUseCase searchUserPropertiesByUserIdUseCase(UserPropertiesGateway userPropertiesGateway){
        return new SearchUserPropertiesByUserIdUseCaseImp(userPropertiesGateway);
    }

    @Bean
    public DeleteUserPropertiesUseCase deleteUserPropertiess(UserPropertiesGateway userPropertiesGateway){
        return new DeleteUserPropertiesUseCaseImp(userPropertiesGateway);
    }
}
