package com.example.StarterHub.infra.gateway;

import com.example.StarterHub.core.domain.Repository;
import com.example.StarterHub.core.gateway.RepositoryGateway;
import com.example.StarterHub.infra.Mapper.RepositoryMapper;
import com.example.StarterHub.infra.persistence.entities.RepositoryModel;
import com.example.StarterHub.infra.persistence.repositories.RepositoryRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class RepositoryRepositoryGateway implements RepositoryGateway {

    private final RepositoryRepository repositoryRepository;
    private final RepositoryMapper mapper;

    public RepositoryRepositoryGateway(RepositoryRepository repositoryRepository, RepositoryMapper mapper) {
        this.repositoryRepository = repositoryRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<Repository> postRepository(Repository repository) {
        System.out.println("MODEL: " + mapper.toEntity(repository).toString());
        RepositoryModel newRepository = repositoryRepository.save(mapper.toEntity(repository));

        return Optional.of(mapper.toDomain(newRepository));
    }

    @Override
    public Optional<Repository> searchRepository(UUID id) {
        Optional<RepositoryModel> searchRepository = repositoryRepository.findById(id);

        return Optional.of(mapper.toDomain(searchRepository.get()));
    }

    @Override
    public Optional<ArrayList<Repository>> findAllRepositories(UUID id) {
        Optional<ArrayList<RepositoryModel>> findAllRepositories = repositoryRepository.findAllByUserPropertiesModelId(id);

        return Optional.of(findAllRepositories.get()
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toCollection(ArrayList::new))
        );
    }

    @Override
    public Optional<Repository> editRepository(UUID id, Repository repository) {
        Optional<RepositoryModel> repositoryModel = repositoryRepository.findById(id);

        if(repositoryModel.isPresent()){
            RepositoryModel model = mapper.toEntity(repository);
            model.setId(id);

            RepositoryModel editedRepository = repositoryRepository.save(model);
            return Optional.of(mapper.toDomain(editedRepository));
        }

        return Optional.empty();
    }

    @Override
    public String deleteRepository(UUID id) {
        Optional<RepositoryModel> repositoryModel = repositoryRepository.findById(id);

        if(repositoryModel.isPresent()){
            repositoryRepository.deleteById(id);
            return "Repositório deletado com sucesso!";
        }

        return "Repositório não encontrado";
    }
}
