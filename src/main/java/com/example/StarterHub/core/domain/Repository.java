package com.example.StarterHub.core.domain;

import com.example.StarterHub.core.Enums.Visibility;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public record Repository(
        UUID id,
        String name,
        String repositoryDescription,
        Visibility visibility,
        String localRepositoryPath,
        LocalDateTime creationTimeStamp,
        LocalDateTime updateTimeStamp,
        ArrayList<Commit> commits,
        Folder root,
        UUID userPropertiesID
) { }
