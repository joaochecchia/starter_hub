package com.example.StarterHub.infra.requests.create;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateUserRequest(@NotEmpty(message = "Username is mandatory") String username,
                                @NotEmpty(message = "Password is mandatory") String password,
                                @NotEmpty(message = "Email is mandatory") String email,
                                @NotNull(message = "Phone number is mandatory") String phone
) { }
