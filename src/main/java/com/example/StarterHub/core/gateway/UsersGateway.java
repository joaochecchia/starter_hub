package com.example.StarterHub.core.gateway;

import com.example.StarterHub.core.domain.Users;

import java.util.Optional;
import java.util.UUID;

public interface UsersGateway {
    Optional<String> loginUsers(Users users);
    Optional<Users> registerUsers(Users users);
    Optional<Users> searchUsers(UUID id);
    Optional<Users> editUsers(UUID id, Users users);
    String deleteUsers(UUID id);
}
