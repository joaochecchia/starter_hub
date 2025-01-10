package com.example.StarterHub.core.useCases.UserProperties;

import com.example.StarterHub.core.domain.UserProperties;

import java.util.Optional;

public interface PostUserPropertiesUseCase {
    Optional<UserProperties> execute(UserProperties userProperties);
}
