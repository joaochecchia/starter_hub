package com.example.StarterHub.infra.gateway;

import com.example.StarterHub.core.domain.Folder;
import com.example.StarterHub.core.gateway.FoldersGateway;
import com.example.StarterHub.infra.Mapper.FolderMapper;
import com.example.StarterHub.infra.persistence.entities.FolderModel;
import com.example.StarterHub.infra.persistence.repositories.FolderRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class FolderRepositoryGateway implements FoldersGateway {

    private final FolderRepository folderRepository;
    private  final FolderMapper mapper;

    public FolderRepositoryGateway(FolderRepository folderRepository, FolderMapper mapper) {
        this.folderRepository = folderRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<Folder> postFolders(Folder folder) {
        System.out.println("DEPOIS DO MAPPER: " +  mapper.toEntity(folder).toString());
        FolderModel newFolder = folderRepository.save(mapper.toEntity(folder));

        return Optional.of(mapper.toDomain(newFolder));
    }

    @Override
    public Optional<Folder> searchFolders(UUID id) {
        Optional<FolderModel> folder = folderRepository.findById(id);

        return Optional.of(mapper.toDomain(folder.get()));
    }

    @Override
    public Optional<ArrayList<Folder>> findAllFolders(UUID id) {
        Optional<ArrayList<FolderModel>> allFolders = folderRepository.findAllByRepository_Id(id);

        return Optional.of(allFolders.get().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toCollection(ArrayList::new))
        );
    }

    @Override
    public Optional<Folder> editFolder(UUID id, Folder folder) {
        Optional<FolderModel> search = folderRepository.findById(id);

        if(search.isPresent()){
            FolderModel model = mapper.toEntity(folder);
            model.setId(id);

            FolderModel editedFolder = folderRepository.save(model);
            return Optional.of(mapper.toDomain(editedFolder));
        }

        return Optional.empty();
    }

    @Override
    public String deleteFolder(UUID id) {
        Optional<FolderModel> search = folderRepository.findById(id);

        if(search.isPresent()){
            folderRepository.deleteById(id);

            return "Folder successfully deleted!";
        }

        return "Folder id  don't exist on database! ";
    }
}
