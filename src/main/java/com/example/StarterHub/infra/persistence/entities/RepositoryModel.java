package com.example.StarterHub.infra.persistence.entities;

import com.example.StarterHub.core.Enums.Visibility;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "repository")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepositoryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "repository_id")
    private UUID id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private Visibility visibility;

    @CreationTimestamp
    @Column(name = "creation_time", updatable = false)
    private LocalDateTime creationTimeStamp;

    @UpdateTimestamp
    @Column(name = "last_update")
    private LocalDateTime updateTimeStamp;

    @ManyToOne
    @JoinColumn(name = "id_user_properties", referencedColumnName = "user_properties_id", nullable = false)
    private UserPropertiesModel userPropertiesModel;

    @OneToMany(mappedBy = "repositoryModel")
    private ArrayList<CommitsModel> commitsModel;

    @OneToMany(mappedBy = "repositoryModel")
    private ArrayList<FilesModel> model;
}
