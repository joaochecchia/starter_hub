package com.example.StarterHub.infra.presentation;

import com.example.StarterHub.core.domain.Folder;
import com.example.StarterHub.core.useCases.Folder.*;
import com.example.StarterHub.infra.Mapper.FolderMapper;
import com.example.StarterHub.infra.requests.SaveAllFoldersRequest;
import com.example.StarterHub.infra.requests.create.CreateFolderRequest;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/starter-hub/users/repository/folder")
public class FoldersController {
    private final PostFolderUseCase postFolderUseCase;
    private final SaveAllFoldersUseCase saveAllFoldersUseCase;
    private final SearchFolderUseCase searchFolderUseCase;
    private final FindAllFoldersUseCase findAllFoldersUseCase;
    private final EditFolderUseCase editFolderUseCase;
    private final DeleteFolderUseCase deleteFolderUseCase;
    private final FolderMapper mapper;

    public FoldersController(PostFolderUseCase postFolderUseCase, SaveAllFoldersUseCase saveAllFoldersUseCase, SearchFolderUseCase searchFolderUseCase, FindAllFoldersUseCase findAllFoldersUseCase, EditFolderUseCase editFolderUseCase, DeleteFolderUseCase deleteFolderUseCase, FolderMapper mapper) {
        this.postFolderUseCase = postFolderUseCase;
        this.saveAllFoldersUseCase = saveAllFoldersUseCase;
        this.searchFolderUseCase = searchFolderUseCase;
        this.findAllFoldersUseCase = findAllFoldersUseCase;
        this.editFolderUseCase = editFolderUseCase;
        this.deleteFolderUseCase = deleteFolderUseCase;
        this.mapper = mapper;
    }

    @Transactional
    @PostMapping("/insert")
    public ResponseEntity<Map<String, Object>> postFolder(@RequestBody CreateFolderRequest request){
        Optional<Folder> newFolder = postFolderUseCase.execute(mapper.toDomain(request));

        Map<String, Object> newUser = new HashMap<>();
        newUser.put("Message: ", "Folder successfully saved");
        newUser.put("Body: ", newFolder.get());

        return ResponseEntity.ok(newUser);
    }

    @Transactional
    @PostMapping("/saveAll")
    public ResponseEntity<Map<String, Object>> saveAll(@RequestBody SaveAllFoldersRequest request) throws IOException {
        Optional<Folder> newDirectory = saveAllFoldersUseCase.execute(mapper.toDomainRecursive(request));

        Map<String, Object> directory = new HashMap<>();
        directory.put("Message: ", "Folder successfully saved");
        directory.put("Body: ", newDirectory.get());

        return ResponseEntity.ok(directory);
    }


    @GetMapping("/search/{id}")
    public ResponseEntity<Map<String, Object>> searchFolder(@PathVariable UUID id){
        Optional<Folder> findFolder = searchFolderUseCase.execute(id);

        Map<String, Object> user = new HashMap<>();
        user.put("Message: ", "Folder successfully found.");
        user.put("Body: ", findFolder.get());

        return ResponseEntity.ok(user);
    }

    @Transactional
    @GetMapping("/findAll/{id}")
    public ResponseEntity<Map<String, Object>> findAllFolders(@PathVariable UUID id){
        Optional<ArrayList<Folder>> searchAllFolders = findAllFoldersUseCase.execute(id);

        Map<String, Object> users = new HashMap<>();
        users.put("Message: ", "Folder successfully found.");
        users.put("Body: ", searchAllFolders.get());

        return ResponseEntity.ok(users);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Map<String, Object>> editFolders(@PathVariable UUID id, @RequestBody Folder request){
        Optional<Folder> folder = editFolderUseCase.execute(id, request);

        Map<String, Object> users = new HashMap<>();
        users.put("Message: ", "Folder successfully found.");
        users.put("Body: ", folder.get());

        return ResponseEntity.ok(users);
    }
}
