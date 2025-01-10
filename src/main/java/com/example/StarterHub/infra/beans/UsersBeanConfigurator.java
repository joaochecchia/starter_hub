package com.example.StarterHub.infra.beans;

import com.example.StarterHub.core.gateway.UsersGateway;
import com.example.StarterHub.core.useCases.User.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsersBeanConfigurator {

    @Bean
    public RegisterUsersUseCase registerUsers(UsersGateway usersGateway){
        return new RegisterUsersUseCaseImp(usersGateway);
    }

    @Bean
    public EditUsersUseCase editUsers(UsersGateway usersGateway){
        return new EditUsersUseCaseImp(usersGateway);
    }

    @Bean
    public LoginUsersUserCase loginUsers(UsersGateway usersGateway){
        return new LoginUsersUserCaseImp(usersGateway);
    }

    @Bean
    public SearchUsersUseCase searchUsers(UsersGateway usersGateway){
        return new SearchUsersUseCaseImp(usersGateway);
    }

    @Bean
    public DeleteUsersUseCase deleteUsers(UsersGateway usersGateway){
        return new DeleteUsersUseCaseImp(usersGateway);
    }
}
