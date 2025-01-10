package com.example.StarterHub.core.useCases.User;

import com.example.StarterHub.core.domain.Users;
import com.example.StarterHub.core.gateway.UsersGateway;

import java.util.Optional;
import java.util.UUID;

public class EditUsersUseCaseImp implements  EditUsersUseCase{

    private final UsersGateway usersGateway;

    public EditUsersUseCaseImp(UsersGateway usersGateway) {
        this.usersGateway = usersGateway;
    }

    @Override
    public Optional<Users> execute(UUID id, Users users) {
        return usersGateway.editUsers(id, users);
    }
}
