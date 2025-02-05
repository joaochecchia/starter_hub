package com.example.StarterHub.core.useCases.User;

import com.example.StarterHub.core.gateway.UserPropertiesGateway;
import com.example.StarterHub.core.gateway.UsersGateway;

import java.util.Map;

public class UserExistsUseCaseImp implements UserExistsUseCase {

    private final UsersGateway usersGateway;

    public UserExistsUseCaseImp(UsersGateway usersGateway) {
        this.usersGateway = usersGateway;
    }

    @Override
    public Map<String, Object> execute(String username, String email, String poneNumber) {
        return usersGateway.userExist(username, email, poneNumber);
    }
}
