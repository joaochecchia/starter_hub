package com.example.StarterHub.core.gateway;

import com.example.StarterHub.core.domain.UserProperties;
import com.example.StarterHub.core.validation.EditRequest;

import java.util.Optional;
import java.util.UUID;

public interface UserPropertiesGateway {
    Optional<UserProperties> postUserProperties(UserProperties userProperties);
    Optional<UserProperties> searchUserProperties(UUID id);
    Optional<UserProperties> editUserProperties(UUID id, EditRequest editRequest);
    String deleteUserProperties(UUID id);
}
