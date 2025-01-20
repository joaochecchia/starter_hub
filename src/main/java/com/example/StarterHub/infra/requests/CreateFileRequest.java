package com.example.StarterHub.infra.requests;

import jakarta.validation.constraints.NotEmpty;

import java.util.UUID;

public record CreateFileRequest(
        @NotEmpty(message = "File is mandatory") byte[] content,
        @NotEmpty(message = "folder id is mandatory") UUID folderId,
        @NotEmpty(message = "commit id is mandatory") UUID commitId
) { }
