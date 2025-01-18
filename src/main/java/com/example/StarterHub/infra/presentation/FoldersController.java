package com.example.StarterHub.infra.presentation;

import com.example.StarterHub.core.domain.Folder;
import com.example.StarterHub.core.useCases.Folder.*;
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

    public FoldersController(PostFolderUseCase postFolderUseCase, SearchFolderUseCase searchFolderUseCase, FindAllFoldersUseCase findAllFoldersUseCase, EditFolderUseCase editFolderUseCase, DeleteFolderUseCase deleteFolderUseCase) {
        this.postFolderUseCase = postFolderUseCase;
        this.searchFolderUseCase = searchFolderUseCase;
        this.findAllFoldersUseCase = findAllFoldersUseCase;
        this.editFolderUseCase = editFolderUseCase;
        this.deleteFolderUseCase = deleteFolderUseCase;
    }

    @Transactional
    @PostMapping("/insert")
    public ResponseEntity<Folder> postFolder(@RequestBody Folder request){
        System.out.println("NO REQUEST: " + request.toString());
        Optional<Folder> newFolder = postFolderUseCase.execute(request);

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
