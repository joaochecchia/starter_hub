package com.example.StarterHub.infra.presentation;

import com.example.StarterHub.core.domain.Repository;
import com.example.StarterHub.core.useCases.Links.*;
import com.example.StarterHub.core.useCases.Repository.*;
import com.example.StarterHub.infra.Mapper.RepositoryMapper;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.*;

import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/starter-hub/user/repository")
public class RepositoryController {

    public final PostRepositoryUseCase postRepositoryUseCase;
    public final FindAllRepositoriesUseCase findAllRepositoriesUseCase;
    public final SearchRepositoryUseCase searchRepositoryUseCase;
    public final EditRepositoryUseCase editRepositoryUseCase;
    public final DeleteRepositoryUseCase deleteRepositoryUseCase;
    public final RepositoryMapper mapper;

    public RepositoryController(PostRepositoryUseCase postRepositoryUseCase, FindAllRepositoriesUseCase findAllRepositoriesUseCase, SearchRepositoryUseCase searchRepositoryUseCase, EditRepositoryUseCase editRepositoryUseCase, DeleteRepositoryUseCase deleteRepositoryUseCase, RepositoryMapper mapper) {
        this.postRepositoryUseCase = postRepositoryUseCase;
        this.findAllRepositoriesUseCase = findAllRepositoriesUseCase;
        this.searchRepositoryUseCase = searchRepositoryUseCase;
        this.editRepositoryUseCase = editRepositoryUseCase;
        this.deleteRepositoryUseCase = deleteRepositoryUseCase;
        this.mapper = mapper;
    }

    @PostMapping("/create")
    public Optional<Repository> createRepository(@RequestBody Repository repository){
        Optional<Repository> newRepository = postRepositoryUseCase.execute(repository);

        return Optional.of(newRepository.get());
    }

    @GetMapping("/search/{id}")
    public Optional<Repository> searchRepository(@PathVariable UUID id){

        return Optional.empty();
    }

    @Transactional
    @GetMapping("/findAll/{id}")
    public Optional<ArrayList<Repository>> findAllRepositories(@PathVariable UUID id){

        return Optional.empty();
    }

    @PutMapping("/edit/{id}")
    public Optional<Repository> editRepository(@PathVariable UUID id, @RequestBody Repository repository){

        return Optional.empty();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteRepository(@PathVariable UUID id){

        return "çalsdkaçsd";
    }
}
