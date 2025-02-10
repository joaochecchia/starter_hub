package com.example.StarterHub.infra.requests.create;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public record CreateFileRequest(
        MultipartFile content,
        UUID folderId
) { }
