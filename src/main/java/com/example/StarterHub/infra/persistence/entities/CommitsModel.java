package com.example.StarterHub.infra.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

@Entity
@Table(name = "commits")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommitsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "commits_id")
    private UUID id;

    @Column(length = 50)
    private String message;

    @CreationTimestamp
    @Column(name = "creation_time")
    private LocalDate creationTimeStamp;

    @ManyToOne
    @JoinColumn(name = "id_repository", referencedColumnName = "repository_id", nullable = false)
    private RepositoryModel repositoryModel;

    @OneToMany(mappedBy = "commitsModel")
    private ArrayList<FilesModel> filesModel;
}