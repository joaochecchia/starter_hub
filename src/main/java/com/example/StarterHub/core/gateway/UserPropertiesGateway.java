package com.example.StarterHub.core.gateway;

import com.example.StarterHub.core.domain.UserProperties;

import java.util.Optional;
import java.util.UUID;

public interface UserPropertiesGateway {
    Optional<UserProperties> postUserProperties(UserProperties userProperties);
    Optional<UserProperties> searchUserProperties(UUID id);
    Optional<UserProperties> editUserProperties(UUID id, UserProperties userProperties);
    String deleteUserProperties(UUID id);
}
