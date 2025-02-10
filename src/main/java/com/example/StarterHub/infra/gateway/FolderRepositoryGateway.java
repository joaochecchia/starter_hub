package com.example.StarterHub.infra.gateway;

import com.example.StarterHub.core.domain.Files;
import com.example.StarterHub.core.domain.Folder;
import com.example.StarterHub.core.gateway.FoldersGateway;
import com.example.StarterHub.infra.Mapper.FilesMapper;
import com.example.StarterHub.infra.Mapper.FolderMapper;
import com.example.StarterHub.infra.exceptions.NotFoundObjectByIdentifierException;
import com.example.StarterHub.infra.persistence.entities.FilesModel;
import com.example.StarterHub.infra.persistence.entities.FolderModel;
import com.example.StarterHub.infra.persistence.repositories.FilesRepository;
import com.example.StarterHub.infra.persistence.repositories.FolderRepository;
import com.example.StarterHub.infra.persistence.repositories.RepositoryRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class FolderRepositoryGateway implements FoldersGateway {

    private final FolderRepository folderRepository;
    private final FilesRepository filesRepository;
    private final RepositoryRepository repositoryRepository;
    private final FilesMapper filesMapper;
    private final FolderMapper mapper;

    public FolderRepositoryGateway(FolderRepository folderRepository, FilesRepository filesRepository, RepositoryRepository repositoryRepository, FilesMapper filesMapper, FolderMapper mapper) {
        this.folderRepository = folderRepository;
        this.filesRepository = filesRepository;
        this.repositoryRepository = repositoryRepository;
        this.filesMapper = filesMapper;
        this.mapper = mapper;
    }

    @Override
    public Optional<Folder> postFolders(Folder folder) {
        if(folder.repositoryId() != null && repositoryRepository.findById(folder.repositoryId()).isEmpty()){
            throw new NotFoundObjectByIdentifierException("Don't have any repository with this id " + folder.repositoryId() + ".");
        }
        if(folder.fatherID() != null && repositoryRepository.findById(folder.repositoryId()).isEmpty()){
            throw new NotFoundObjectByIdentifierException("Don't have any folder with this id " + folder.fatherID() + ".");
        }
        FolderModel newFolder = folderRepository.save(mapper.toEntity(folder));

        return Optional.of(mapper.toDomain(newFolder));
    }

    @Override
    public Optional<Folder> saveAllFolders(Folder folder) {
        FolderModel newFolder = folderRepository.save(mapper.toEntity(folder));
        for(Files i : folder.files()){
            FilesModel filesModel = filesRepository.save(filesMapper.toEntity(i, newFolder.getId()));

            if(newFolder.getFiles() == null){
                newFolder.setFiles(new ArrayList<>());
            }

            newFolder.getFiles().add(filesModel);
        }

        for(Folder i : folder.children()){
            Optional<Folder> folderModel = this.saveAllFolders(new Folder(
                    null,
                    i.name(),
                    newFolder.getId(),
                    i.files(),
                    i.children(),
                    null
            ));
        }

        return Optional.of(mapper.toDomainRecursive(newFolder));
    }

    @Override
    public Optional<Folder> searchFolders(UUID id) {
        Optional<FolderModel> folder = folderRepository.findById(id);
        if(folder.isEmpty()) throw new NotFoundObjectByIdentifierException("Don't have any folder with " + id + " ID.");

        return Optional.of(mapper.toDomain(folder.get()));
    }

    @Override
    public Optional<ArrayList<Folder>> findAllFolders(UUID id) {
        Optional<ArrayList<FolderModel>> allFolders = folderRepository.findAllByRepository_Id(id);
        if(allFolders.get().isEmpty()) throw new NotFoundObjectByIdentifierException("Don't have any folder with " + id + " ID.");

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

        throw new NotFoundObjectByIdentifierException("Don't have any folder with " + id + " ID.");
    }

    @Override
    public String deleteFolder(UUID id) {
        Optional<FolderModel> search = folderRepository.findById(id);

        if(search.isPresent()){
            folderRepository.deleteById(id);

            return "Folder successfully deleted!";
        }

        throw new NotFoundObjectByIdentifierException("Don't have any folder with " + id + " ID.");
    }
}
