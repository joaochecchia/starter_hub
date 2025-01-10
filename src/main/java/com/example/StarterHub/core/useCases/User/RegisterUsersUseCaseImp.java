package com.example.StarterHub.core.useCases.User;

import com.example.StarterHub.core.domain.Users;
import com.example.StarterHub.core.gateway.UsersGateway;
import org.springframework.stereotype.Service;

import java.util.Optional;


public class RegisterUsersUseCaseImp implements RegisterUsersUseCase{

    private final UsersGateway usersGateway;

    public RegisterUsersUseCaseImp(UsersGateway usersGateway) {
        this.usersGateway = usersGateway;
    }

    @Override
    public Optional<Users> execute(Users users) {
        return usersGateway.registerUsers(users);
    }
}
