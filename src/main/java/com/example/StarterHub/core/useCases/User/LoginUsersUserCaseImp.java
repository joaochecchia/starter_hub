package com.example.StarterHub.core.useCases.User;

import com.example.StarterHub.core.domain.Users;
import com.example.StarterHub.core.gateway.UsersGateway;

import java.util.Optional;

public class LoginUsersUserCaseImp implements LoginUsersUserCase{

    private final UsersGateway usersGateway;

    public LoginUsersUserCaseImp(UsersGateway usersGateway) {
        this.usersGateway = usersGateway;
    }

    @Override
    public Optional<String> execute(Users users) {
        return usersGateway.loginUsers(users);
    }
}
