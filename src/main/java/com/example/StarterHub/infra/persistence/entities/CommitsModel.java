package com.example.StarterHub.infra.persistence.entities;

import com.example.StarterHub.core.domain.Files;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

@Entity
@Table(name = "commits")
public class CommitsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "hash")
    private UUID hash;

    @Column(length = 50, nullable = false)
    private String description;

    @CreationTimestamp
    @Column(name = "creation_time")
    private LocalDate creationTimeStamp;

    @OneToMany(mappedBy = "commitsModel")
    ArrayList<FilesModel> filesModel;

    @ManyToOne
    @JoinColumn(name = "id_repository", referencedColumnName = "repository_id", nullable = false)
    private RepositoryModel repositoryModel;

    public CommitsModel(UUID hash, String description, LocalDate creationTimeStamp, ArrayList<FilesModel> filesModel, RepositoryModel repositoryModel) {
        this.hash = hash;
        this.description = description;
        this.creationTimeStamp = creationTimeStamp;
        this.filesModel = filesModel;
        this.repositoryModel = repositoryModel;
    }

    public UUID getHash() {
        return hash;
    }

    public void setHash(UUID hash) {
        this.hash = hash;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreationTimeStamp() {
        return creationTimeStamp;
    }

    public void setCreationTimeStamp(LocalDate creationTimeStamp) {
        this.creationTimeStamp = creationTimeStamp;
    }

    public ArrayList<FilesModel> getFilesModel() {
        return filesModel;
    }

    public void setFilesModel(ArrayList<FilesModel> filesModel) {
        this.filesModel = filesModel;
    }

    public RepositoryModel getRepositoryModel() {
        return repositoryModel;
    }

    public void setRepositoryModel(RepositoryModel repositoryModel) {
        this.repositoryModel = repositoryModel;
    }
}