package com.example.StarterHub.infra.presentation;

import com.example.StarterHub.core.domain.Files;
import com.example.StarterHub.core.useCases.Files.*;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/starter-hub/users/repository/commits/files")
public class FilesController {

    private final PostFilesUseCase postFilesUseCase;
    private final SearchFilesUseCase searchFilesUseCase;
    private final EditFilesUseCase editFilesUseCase;
    private final DeleteFilesUseCase deleteFilesUseCase;

    public FilesController(PostFilesUseCase postFilesUseCase, SearchFilesUseCase searchFilesUseCase, EditFilesUseCase editFilesUseCase, DeleteFilesUseCase deleteFilesUseCase) {
        this.postFilesUseCase = postFilesUseCase;
        this.searchFilesUseCase = searchFilesUseCase;
        this.editFilesUseCase = editFilesUseCase;
        this.deleteFilesUseCase = deleteFilesUseCase;
    }

    @PostMapping("/post")
    public ResponseEntity<Files> postFile(@RequestBody Files request){
        Optional<Files> newFile = postFilesUseCase.execute(request);

        return ResponseEntity.ok(newFile.get());
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<Files> searchFile(@PathVariable UUID id){
        Optional<Files> searchFile = searchFilesUseCase.execute(id);

        return ResponseEntity.ok(searchFile.get());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Files> editFiles(@PathVariable UUID id, @RequestBody Files files){
        Optional<Files> editedFile = editFilesUseCase.execute(id, files);

        return ResponseEntity.ok(editedFile.get());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFile(@PathVariable UUID id){
        String deletionMessage = deleteFilesUseCase.execute(id);

        return ResponseEntity.ok(deletionMessage);
    }
}
