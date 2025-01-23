package com.example.StarterHub.core.useCases.User;

import com.example.StarterHub.core.domain.Users;
import com.example.StarterHub.core.gateway.UsersGateway;
import com.example.StarterHub.infra.exceptions.CredentialsAreadyExistsExceptions;

import java.util.Map;
import java.util.Optional;

public class RegisterUsersUseCaseImp implements RegisterUsersUseCase{

    private final UsersGateway usersGateway;

    public RegisterUsersUseCaseImp(UsersGateway usersGateway) {
        this.usersGateway = usersGateway;
    }

    @Override
    public Optional<Users> execute(Users users) {
        Map<String, Object> check = usersGateway.userExist(users.username(), users.email(), users.phone());
        if(!check.isEmpty()){
            StringBuilder stringBuilder = new StringBuilder();
            if(check.get("Username") != null) stringBuilder.append(check.get("Username")).append("\n");
            if(check.get("Email") != null) stringBuilder.append(check.get("Email")).append("\n");
            if (check.get("PhoneNumber") != null) stringBuilder.append(check.get("PhoneNumber")).append("\n");

            throw new CredentialsAreadyExistsExceptions(stringBuilder.toString());
        }

        return usersGateway.registerUsers(users);
    }
}