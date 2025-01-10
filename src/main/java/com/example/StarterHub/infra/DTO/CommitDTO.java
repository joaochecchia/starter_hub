package com.example.StarterHub.infra.DTO;

import com.example.StarterHub.core.domain.Files;
import com.example.StarterHub.core.domain.Repository;

import java.util.ArrayList;
import java.util.UUID;

public record CommitDTO(
        UUID hash,
        String description,
        RepositoryDTO repositoryDTO,
        ArrayList<FilesDTO> filesDTO
) { }
