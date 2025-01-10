package com.example.StarterHub.infra.DTO;

import com.example.StarterHub.core.domain.Commit;

import java.util.UUID;

public record FilesDTO(
        UUID id,
        byte[] content,
        CommitDTO commitDTO
) { }
