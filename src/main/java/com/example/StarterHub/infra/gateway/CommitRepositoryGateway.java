package com.example.StarterHub.infra.gateway;

import com.example.StarterHub.core.domain.Commit;
import com.example.StarterHub.core.gateway.CommitGateway;
import com.example.StarterHub.infra.Mapper.CommitMapper;
import com.example.StarterHub.infra.persistence.entities.CommitsModel;
import com.example.StarterHub.infra.persistence.repositories.CommitsRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class CommitRepositoryGateway implements CommitGateway {

    private final CommitsRepository commitsRepository;
    private final CommitMapper mapper;

    public CommitRepositoryGateway(CommitsRepository commitsRepository, CommitMapper mapper) {
        this.commitsRepository = commitsRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<Commit> postCommit(Commit folder) {
        CommitsModel newModel = commitsRepository.save(mapper.toEntity(folder));

        return Optional.of(mapper.toDomain(newModel));
    }

    @Override
    public Optional<Commit> searchCommit(UUID id) {
        Optional<CommitsModel> searchModel = commitsRepository.findById(id);

        return Optional.of(mapper.toDomain(searchModel.get()));
    }

    @Override
    public Optional<ArrayList<Commit>> findAllCommits(UUID id) {
        Optional<ArrayList<CommitsModel>> findAllCommits = commitsRepository.findAllByRepositoryModelId(id);

        return Optional.of(findAllCommits.get().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toCollection(ArrayList::new))
        );
    }

    @Override
    public Optional<Commit> editCommit(UUID id, Commit folder) {
        Optional<CommitsModel> find = commitsRepository.findById(id);

        if(find.isPresent()){
            CommitsModel commitsModel = mapper.toEntity(folder);
            commitsModel.setHash(commitsModel.getHash());

            CommitsModel editedCommit = commitsRepository.save(commitsModel);
            return Optional.of(mapper.toDomain(editedCommit));
        }

        return Optional.empty();
    }

    @Override
    public String deleteCommit(UUID id) {
        Optional<CommitsModel> find = commitsRepository.findById(id);

        if(find.isPresent()){
            commitsRepository.deleteById(id);

            return "Commit successfully reverted!";
        }

        return "Commit doesn't exist on database!";
    }
}
