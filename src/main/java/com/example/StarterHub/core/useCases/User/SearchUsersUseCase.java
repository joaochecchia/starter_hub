package com.example.StarterHub.core.useCases.User;

import com.example.StarterHub.core.domain.Users;

import java.util.Optional;
import java.util.UUID;

public interface SearchUsersUseCase {
    Optional<Users> execute(UUID id);
}
