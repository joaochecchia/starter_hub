package com.example.StarterHub.core.useCases.UserProperties;

import com.example.StarterHub.core.domain.UserProperties;

import java.util.Optional;

public interface SearchUserPropertiesUseCase {
    Optional<UserProperties>  execute(String name);
}
