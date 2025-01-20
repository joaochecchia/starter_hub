package com.example.StarterHub.infra.requests;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

public record InsertPhotoRequest(@NotEmpty(message = "Photo can't be null,") String encodedPhoto) {
}
