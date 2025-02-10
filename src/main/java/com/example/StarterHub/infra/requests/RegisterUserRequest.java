package com.example.StarterHub.infra.requests;

import com.example.StarterHub.infra.requests.create.CreateUserRequest;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;

public record RegisterUserRequest(@NotNull CreateUserRequest userRequest,
                                  String description,
                                  String company,
                                  String country,
                                  String postalCode,
                                  String location,
                                  ArrayList<String> createLinksRequest)
{ }
