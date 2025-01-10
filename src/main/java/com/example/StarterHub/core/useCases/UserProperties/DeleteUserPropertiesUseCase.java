package com.example.StarterHub.core.useCases.UserProperties;

import com.example.StarterHub.core.domain.UserProperties;

import java.util.UUID;

public interface DeleteUserPropertiesUseCase {
    String execute(UUID id);
}
