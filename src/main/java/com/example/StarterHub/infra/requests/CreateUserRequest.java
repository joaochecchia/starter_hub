package com.example.StarterHub.infra.requests;

import com.example.StarterHub.infra.persistence.entities.UserPropertiesModel;
import jakarta.validation.constraints.NotEmpty;

public record CreateUserRequest(@NotEmpty(message = "Username is mandatory") String username,
                                @NotEmpty(message = "Password is mandatory") String password,
                                @NotEmpty(message = "Email is mandatory") String email,
                                @NotEmpty(message = "Phone number is mandatory") String phone
) { }
