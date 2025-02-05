package com.example.StarterHub.infra.requests;

import com.example.StarterHub.infra.requests.create.CreateAddressRequest;
import com.example.StarterHub.infra.requests.create.CreateLinksRequest;
import com.example.StarterHub.infra.requests.create.CreateUserPropertiesRequest;
import com.example.StarterHub.infra.requests.create.CreateUserRequest;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;

public record RegisterUserRequest(@NotNull CreateUserRequest userRequest,
                                  CreateUserPropertiesRequest userPropertiesRequest,
                                  CreateAddressRequest createAddressRequest,
                                  ArrayList<String> createLinksRequest)
{ }
