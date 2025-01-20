package com.example.StarterHub.infra.presentation;

import com.example.StarterHub.core.domain.Repository;
import com.example.StarterHub.core.useCases.Links.*;
import com.example.StarterHub.core.useCases.Repository.*;
import com.example.StarterHub.infra.Mapper.RepositoryMapper;
import com.example.StarterHub.infra.requests.CreateRepositoryRequest;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

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

    @PostMapping("/create")
    public ResponseEntity<Repository> createRepository(@RequestBody CreateRepositoryRequest repository){
        Optional<Repository> newRepository = postRepositoryUseCase.execute(mapper.toDomain(repository));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(newRepository.get());
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<Repository> searchRepository(@PathVariable UUID id){
        Optional<Repository> repository = searchRepositoryUseCase.execute(id);

        return ResponseEntity.ok(repository.get());
    }

    @Transactional
    @GetMapping("/findAll/{id}")
    public ResponseEntity<ArrayList<Repository>> findAllRepositories(@PathVariable UUID id){
        Optional<ArrayList<Repository>> allRepositories = findAllRepositoriesUseCase.execute(id);

        return ResponseEntity.ok(allRepositories.get());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Repository> editRepository(@PathVariable UUID id, @RequestBody Repository repository){
        Optional<Repository> editedRepository = editRepositoryUseCase.execute(id, repository);

        return ResponseEntity.ok(editedRepository.get());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRepository(@PathVariable UUID id){
        String response = deleteRepositoryUseCase.execute(id);

        return ResponseEntity.ok(response);
    }
}
