package com.example.StarterHub.core.validation;

import java.util.ArrayList;
import java.util.UUID;

public record SearchFolderRequest (UUID id,
                                   UUID fatherID,
                                   UUID repositoryID,
                                   ArrayList<SearchFolderRequest> children,
                                   ArrayList<UUID> files)
{ }
