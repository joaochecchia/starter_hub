package com.example.StarterHub.core.useCases.User;

import com.example.StarterHub.core.domain.Users;
import com.example.StarterHub.core.gateway.UsersGateway;

import java.util.Optional;
import java.util.UUID;

public class SearchUsersUseCaseImp implements SearchUsersUseCase{

    private final UsersGateway usersGateway;

    public SearchUsersUseCaseImp(UsersGateway usersGateway) {
        this.usersGateway = usersGateway;
    }

    @Override
    public Optional<Users> execute(UUID id) {
        return usersGateway.searchUsers(id);
    }
}
