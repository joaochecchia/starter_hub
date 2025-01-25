package com.example.StarterHub.core.useCases.User;

import com.example.StarterHub.core.domain.Users;
import com.example.StarterHub.core.validation.LoginRequest;
import com.example.StarterHub.core.validation.LoginResponse;

import java.util.Optional;

public interface LoginUsersUserCase {
    Optional<LoginResponse> execute(LoginRequest response);
}
