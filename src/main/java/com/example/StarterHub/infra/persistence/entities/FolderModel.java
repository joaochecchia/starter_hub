package com.example.StarterHub.infra.persistence.entities;

import com.example.StarterHub.core.domain.Files;
import com.example.StarterHub.core.domain.Folder;
import com.example.StarterHub.core.domain.Repository;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "folder")
public class FolderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "folder_id")
    private UUID id;

    private String name;

    @OneToOne
    @JoinColumn(name = "id_father", nullable = true)
    private FolderModel father;

    @OneToMany(mappedBy = "folderModel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FilesModel> files;

    @OneToMany(mappedBy = "father", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FolderModel> children;

    @OneToOne
    @JoinColumn(name = "repository_id")
    private RepositoryModel repository;

    public FolderModel(UUID id, String name, FolderModel father, List<FilesModel> files, List<FolderModel> children, RepositoryModel repository) {
        this.id = id;
        this.name = name;
        this.father = father;
        this.files = files;
        this.children = children;
        this.repository = repository;
    }

    public FolderModel(UUID id, String name, FolderModel father, RepositoryModel repository) {
        this.id = id;
        this.name = name;
        this.father = father;
        this.repository = repository;
    }

    public FolderModel(UUID id) {
        this.id = id;
    }

    public FolderModel() {
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

    public FolderModel getFather() {
        return father;
    }

    public void setFather(FolderModel father) {
        this.father = father;
    }

    public List<FilesModel> getFiles() {
        return files;
    }

    public void setFiles(List<FilesModel> files) {
        this.files = files;
    }

    public List<FolderModel> getChildren() {
        return children;
    }

    public void setChildren(List<FolderModel> children) {
        this.children = children;
    }

    public RepositoryModel getRepository() {
        return repository;
    }

    public void setRepository(RepositoryModel repository) {
        this.repository = repository;
    }

    @Override
    public String toString() {
        return "FolderModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", father=" + father +
                ", files=" + files +
                ", children=" + children +
                ", repository=" + repository +
                '}';
    }
}
