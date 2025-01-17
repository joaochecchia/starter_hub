package com.example.StarterHub.infra.persistence.entities;

import com.example.StarterHub.core.Enums.Visibility;
import com.example.StarterHub.core.domain.Commit;
import com.example.StarterHub.core.domain.Folder;
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

    @OneToMany(mappedBy = "repositoryModel")
    private List<CommitsModel> commitsModel;

    @OneToOne(mappedBy = "repository")
    private FolderModel root;

    @ManyToOne
    @JoinColumn(name = "id_user_properties", referencedColumnName = "user_properties_id", nullable = false)
    private UserPropertiesModel userPropertiesModel;

    public RepositoryModel(UUID id, String name, String description, Visibility visibility, LocalDateTime creationTimeStamp, LocalDateTime updateTimeStamp, List<CommitsModel> commitsModel, FolderModel root, UserPropertiesModel userPropertiesModel) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.visibility = visibility;
        this.creationTimeStamp = creationTimeStamp;
        this.updateTimeStamp = updateTimeStamp;
        this.commitsModel = commitsModel;
        this.root = root;
        this.userPropertiesModel = userPropertiesModel;
    }

    public RepositoryModel() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    public LocalDateTime getCreationTimeStamp() {
        return creationTimeStamp;
    }

    public void setCreationTimeStamp(LocalDateTime creationTimeStamp) {
        this.creationTimeStamp = creationTimeStamp;
    }

    public LocalDateTime getUpdateTimeStamp() {
        return updateTimeStamp;
    }

    public void setUpdateTimeStamp(LocalDateTime updateTimeStamp) {
        this.updateTimeStamp = updateTimeStamp;
    }

    public List<CommitsModel> getCommitsModel() {
        return commitsModel;
    }

    public void setCommitsModel(ArrayList<CommitsModel> commitsModel) {
        this.commitsModel = commitsModel;
    }

    public FolderModel getRoot() {
        return root;
    }

    public void setRoot(FolderModel root) {
        this.root = root;
    }

    public UserPropertiesModel getUserPropertiesModel() {
        return userPropertiesModel;
    }

    public void setUserPropertiesModel(UserPropertiesModel userPropertiesModel) {
        this.userPropertiesModel = userPropertiesModel;
    }
}
