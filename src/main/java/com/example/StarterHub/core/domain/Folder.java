package com.example.StarterHub.core.domain;

import java.util.ArrayList;
import java.util.UUID;

public record Folder(
        UUID id,
        String name,
        UUID fatherID,
        ArrayList<Files> files,
        ArrayList<Folder> children,
        UUID repositoryId
) { }
