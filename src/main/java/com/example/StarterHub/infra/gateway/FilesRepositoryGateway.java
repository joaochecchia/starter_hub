package com.example.StarterHub.infra.gateway;

import com.example.StarterHub.core.domain.Files;
import com.example.StarterHub.core.gateway.FilesGatweay;
import com.example.StarterHub.infra.Mapper.FilesMapper;
import com.example.StarterHub.infra.persistence.entities.FilesModel;
import com.example.StarterHub.infra.persistence.repositories.FilesRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class FilesRepositoryGateway implements FilesGatweay {

    private final FilesRepository filesRepository;
    private final FilesMapper filesMapper;

    public FilesRepositoryGateway(FilesRepository filesRepository, FilesMapper filesMapper) {
        this.filesRepository = filesRepository;
        this.filesMapper = filesMapper;
    }

    @Override
    public Optional<Files> postFile(Files files) {
        FilesModel newFile = filesRepository.save(filesMapper.toEntity(files));

        return Optional.of(filesMapper.toDomain(newFile));
    }

    @Override
    public Optional<Files> searchFile(UUID id) {
        Optional<FilesModel> searchFile = filesRepository.findById(id);

        return Optional.of(filesMapper.toDomain(searchFile.get()));
    }

    @Override
    public Optional<ArrayList<Files>> findAllFiles(UUID id) {
        Optional<ArrayList<FilesModel>> findAllFiles = filesRepository.findAllByCommitsModelHash(id);

        return Optional.of(findAllFiles.get().stream()
                .map(filesMapper::toDomain)
                .collect(Collectors.toCollection(ArrayList::new))
        );
    }

    @Override
    public Optional<Files> editFile(UUID id, Files files) {
        Optional<FilesModel> verify = filesRepository.findById(id);

        if(verify.isPresent()){
            FilesModel model = filesMapper.toEntity(files);
            model.setId(id);

            FilesModel editedFile = filesRepository.save(model);
            return Optional.of(filesMapper.toDomain(editedFile));
        }

        return Optional.empty();
    }

    @Override
    public String deleteFile(UUID id) {
        Optional<FilesModel> verify = filesRepository.findById(id);

        if(verify.isPresent()){
            filesRepository.deleteById(id);

            return "File successfully deleted!";
        }

        return "File doesn't on database!";
    }
}
