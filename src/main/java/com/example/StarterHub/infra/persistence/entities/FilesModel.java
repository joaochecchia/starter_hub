package com.example.StarterHub.infra.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "files")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "file_id")
    private UUID id;

    private String path;

    @Lob
    private byte[] content;

    @ManyToOne
    @JoinColumn(name = "id_commits", referencedColumnName = "commits_id", nullable = false)
    private CommitsModel commitsModel;

    @ManyToOne
    @JoinColumn(name = "id_repository", referencedColumnName = "repository_id", nullable = false)
    private RepositoryModel repositoryModel;
}
