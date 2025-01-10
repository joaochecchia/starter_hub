package com.example.StarterHub.core.useCases.User;

import com.example.StarterHub.core.domain.Users;

import java.util.Optional;

public interface LoginUsersUserCase {
    Optional<String> execute(Users users);
}
