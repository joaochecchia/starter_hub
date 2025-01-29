package com.example.StarterHub.core.useCases.UserProperties;

import com.example.StarterHub.core.domain.UserProperties;

import java.util.Optional;
import java.util.UUID;

public interface SearchUserPropertiesByUserIdUseCase {
    Optional<UserProperties> execute(UUID id);
}
