package com.example.StarterHub.infra.presentation;

import com.example.StarterHub.core.domain.Folder;
import com.example.StarterHub.core.useCases.Folder.*;
import com.example.StarterHub.infra.Mapper.FolderMapper;
import com.example.StarterHub.infra.requests.create.CreateFolderRequest;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/starter-hub/users/repository/folder")
public class FoldersController {
    public final PostFolderUseCase postFolderUseCase;
    public final SearchFolderUseCase searchFolderUseCase;
    public final FindAllFoldersUseCase findAllFoldersUseCase;
    public final EditFolderUseCase editFolderUseCase;
    public final DeleteFolderUseCase deleteFolderUseCase;
    public final FolderMapper mapper;

    public FoldersController(PostFolderUseCase postFolderUseCase, SearchFolderUseCase searchFolderUseCase, FindAllFoldersUseCase findAllFoldersUseCase, EditFolderUseCase editFolderUseCase, DeleteFolderUseCase deleteFolderUseCase, FolderMapper mapper) {
        this.postFolderUseCase = postFolderUseCase;
        this.searchFolderUseCase = searchFolderUseCase;
        this.findAllFoldersUseCase = findAllFoldersUseCase;
        this.editFolderUseCase = editFolderUseCase;
        this.deleteFolderUseCase = deleteFolderUseCase;
        this.mapper = mapper;
    }

    @Transactional
    @PostMapping("/insert")
    public ResponseEntity<Folder> postFolder(@RequestBody CreateFolderRequest request){
        System.out.println("REQUEST" + mapper.toDomain(request).toString());
        System.out.println("DOMAIN: " + mapper.toDomain(request).toString());
        Optional<Folder> newFolder = postFolderUseCase.execute(mapper.toDomain(request));

        return ResponseEntity.ok(newFolder.get());
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<Folder> searchFolder(@PathVariable UUID id){
        Optional<Folder> findFolder = searchFolderUseCase.execute(id);

        return ResponseEntity.ok(findFolder.get());
    }

    @Transactional
    @GetMapping("/findAll/{id}")
    public ResponseEntity<ArrayList<Folder>> findAllFolders(@PathVariable UUID id){
        Optional<ArrayList<Folder>> searchAllFolders = findAllFoldersUseCase.execute(id);

        return ResponseEntity.ok(searchAllFolders.get());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Folder> editFolders(@PathVariable UUID id, @RequestBody Folder request){
        Optional<Folder> folder = editFolderUseCase.execute(id, request);

        return ResponseEntity.ok(folder.get());
    }
}
