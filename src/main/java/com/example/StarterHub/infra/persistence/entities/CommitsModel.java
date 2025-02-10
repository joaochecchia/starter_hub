package com.example.StarterHub.infra.persistence.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
    private LocalDateTime creationTimeStamp;

    @ManyToOne
    @JoinColumn(name = "id_repository", referencedColumnName = "repository_id", nullable = false)
    private RepositoryModel repositoryModel;

    public CommitsModel(UUID hash, String description, LocalDateTime creationTimeStamp, RepositoryModel repositoryModel) {
        this.hash = hash;
        this.description = description;
        this.creationTimeStamp = creationTimeStamp;
        this.repositoryModel = repositoryModel;
    }

    public CommitsModel(UUID hash) {
        this.hash = hash;
    }

    public CommitsModel() {
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

    public LocalDateTime getCreationTimeStamp() {
        return creationTimeStamp;
    }

    public void setCreationTimeStamp(LocalDateTime creationTimeStamp) {
        this.creationTimeStamp = creationTimeStamp;
    }

    public RepositoryModel getRepositoryModel() {
        return repositoryModel;
    }

    public void setRepositoryModel(RepositoryModel repositoryModel) {
        this.repositoryModel = repositoryModel;
    }

    @Override
    public String toString() {
        return "CommitsModel{" +
                "hash=" + hash +
                ", description='" + description + '\'' +
                ", creationTimeStamp=" + creationTimeStamp +
                ", repositoryModel=" + repositoryModel +
                '}';
    }
}