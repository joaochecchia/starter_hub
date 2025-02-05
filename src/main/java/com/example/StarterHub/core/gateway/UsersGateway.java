package com.example.StarterHub.core.gateway;

import com.example.StarterHub.core.domain.Users;
import com.example.StarterHub.core.validation.LoginRequest;
import com.example.StarterHub.core.validation.LoginResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface UsersGateway {
    Optional<LoginResponse> loginUsers(LoginRequest request);
    Optional<Users> registerUsers(Users users);
    Optional<Users> searchUsers(UUID id);
    Optional<Users> editUsers(UUID id, Users users);
    String deleteUsers(UUID id);
    Map<String, Object> userExist(String username, String email, String poneNumber);
}
