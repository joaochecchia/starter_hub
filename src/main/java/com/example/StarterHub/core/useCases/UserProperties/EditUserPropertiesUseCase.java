package com.example.StarterHub.core.useCases.UserProperties;

import com.example.StarterHub.core.domain.UserProperties;
import com.example.StarterHub.core.validation.EditRequest;

import java.util.Optional;
import java.util.UUID;

public interface EditUserPropertiesUseCase {
    Optional<UserProperties> execute(UUID id, EditRequest editRequest);
}
