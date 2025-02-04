package com.example.StarterHub.infra.requests.create;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateFileRequest(
        @NotEmpty(message = "File is mandatory") byte[] content,
        @NotNull(message = "folder id is mandatory") UUID folderId,
        @NotNull(message = "commit id is mandatory") UUID commitId
) { }
