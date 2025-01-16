package com.example.StarterHub.infra.persistence.entities;

import com.example.StarterHub.core.domain.Files;
import com.example.StarterHub.core.domain.Folder;
import com.example.StarterHub.core.domain.Repository;
import jakarta.persistence.*;

import java.util.ArrayList;
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
    private ArrayList<FilesModel> files;

    @OneToMany(mappedBy = "father", cascade = CascadeType.ALL, orphanRemoval = true)
    private ArrayList<FolderModel> children;

    @OneToOne
    @JoinColumn(name = "repository_id")
    private RepositoryModel repository;

    public FolderModel(UUID id, String name, FolderModel father, ArrayList<FilesModel> files, ArrayList<FolderModel> children, RepositoryModel repository) {
        this.id = id;
        this.name = name;
        this.father = father;
        this.files = files;
        this.children = children;
        this.repository = repository;
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

    public ArrayList<FilesModel> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<FilesModel> files) {
        this.files = files;
    }

    public ArrayList<FolderModel> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<FolderModel> children) {
        this.children = children;
    }

    public RepositoryModel getRepository() {
        return repository;
    }

    public void setRepository(RepositoryModel repository) {
        this.repository = repository;
    }
}
