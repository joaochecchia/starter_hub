package com.example.StarterHub.infra.presentation;

import com.example.StarterHub.core.domain.Repository;
import com.example.StarterHub.core.useCases.Repository.*;
import com.example.StarterHub.infra.Mapper.RepositoryMapper;
import com.example.StarterHub.infra.requests.create.CreateRepositoryRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/starter-hub/users/repository")
public class RepositoryController {

    private final PostRepositoryUseCase postRepositoryUseCase;
    private final FindAllRepositoriesUseCase findAllRepositoriesUseCase;
    private final SearchRepositoryUseCase searchRepositoryUseCase;
    private final EditRepositoryUseCase editRepositoryUseCase;
    private final DeleteRepositoryUseCase deleteRepositoryUseCase;
    private final RepositoryMapper mapper;

    public RepositoryController(PostRepositoryUseCase postRepositoryUseCase, FindAllRepositoriesUseCase findAllRepositoriesUseCase, SearchRepositoryUseCase searchRepositoryUseCase, EditRepositoryUseCase editRepositoryUseCase, DeleteRepositoryUseCase deleteRepositoryUseCase, RepositoryMapper mapper) {
        this.postRepositoryUseCase = postRepositoryUseCase;
        this.findAllRepositoriesUseCase = findAllRepositoriesUseCase;
        this.searchRepositoryUseCase = searchRepositoryUseCase;
        this.editRepositoryUseCase = editRepositoryUseCase;
        this.deleteRepositoryUseCase = deleteRepositoryUseCase;
        this.mapper = mapper;
    }

    @Transactional
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createRepository(@Valid @RequestBody CreateRepositoryRequest request){
        Optional<Repository> newRepository = postRepositoryUseCase.execute(mapper.toDomain(request));

        Map<String, Object> response = new HashMap<>();
        response.put("Message: ", "Repository successfully created");
        response.put("Body: ", newRepository.get());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<Map<String, Object>> searchRepository(@PathVariable UUID id){
        Optional<Repository> repository = searchRepositoryUseCase.execute(id);

        Map<String, Object> response = new HashMap<>();
        response.put("Message: ", "Repository successfully found.");
        response.put("Body: ", repository.get());

        return ResponseEntity.ok(response);
    }

    @Transactional
    @GetMapping("/findAll/{id}")
    public ResponseEntity<Map<String,Object>> findAllRepositories(@PathVariable UUID id){
        Optional<ArrayList<Repository>> allRepositories = findAllRepositoriesUseCase.execute(id);

        Map<String, Object> response = new HashMap<>();
        response.put("Message: ", "Repositories successfully found.");
        response.put("Body: ", allRepositories.get());

        return ResponseEntity.ok(response);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Map<String, Object>> editRepository(@PathVariable UUID id, @Valid @RequestBody CreateRepositoryRequest request){
        Optional<Repository> editedRepository = editRepositoryUseCase.execute(id, mapper.toDomain(request));

        Map<String, Object> response = new HashMap<>();
        response.put("Message: ", "Repositories successfully edited.");
        response.put("Body: ", editedRepository.get());

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteRepository(@PathVariable UUID id){
        System.out.println(id);
        String result = deleteRepositoryUseCase.execute(id);

        Map<String, Object> response = new HashMap<>();
        response.put("Message: ", "Repositories successfully deleted.");
        response.put("Body: ", result);

        return ResponseEntity.ok(response);
    }
}