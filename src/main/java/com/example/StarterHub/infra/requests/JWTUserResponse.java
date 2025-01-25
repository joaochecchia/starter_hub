package com.example.StarterHub.infra.requests;

import lombok.Builder;

import java.util.UUID;

@Builder
public record JWTUserResponse(UUID id, String email){
}
