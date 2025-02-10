package com.example.StarterHub.core.gateway;

import com.example.StarterHub.core.domain.Files;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public interface FilesGatweay {
    Optional<Files> postFile(Files files);
    Optional<Files> searchFile(UUID id);
    Optional<Files> editFile(UUID id, Files files);
    String deleteFile(UUID id);
}
