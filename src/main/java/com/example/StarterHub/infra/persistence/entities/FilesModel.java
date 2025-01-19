package com.example.StarterHub.infra.persistence.entities;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.UUID;

@Entity
@Table(name = "files")
public class FilesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "file_id")
    private UUID id;

    @Lob
    private byte[] content;

    @ManyToOne
    @JoinColumn(name = "id_folder", referencedColumnName = "folder_id", nullable = false)
    private FolderModel folderModel;

    @ManyToOne
    @JoinColumn(name = "id_commits", referencedColumnName = "hash", nullable = false)
    private CommitsModel commitsModel;

    public FilesModel(UUID id, byte[] content, FolderModel folderModel, CommitsModel commitsModel) {
        this.id = id;
        this.content = content;
        this.folderModel = folderModel;
        this.commitsModel = commitsModel;
    }

    public FilesModel() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public FolderModel getFolderModel() {
        return folderModel;
    }

    public void setFolderModel(FolderModel folderModel) {
        this.folderModel = folderModel;
    }

    public CommitsModel getCommitsModel() {
        return commitsModel;
    }

    public void setCommitsModel(CommitsModel commitsModel) {
        this.commitsModel = commitsModel;
    }

    @Override
    public String toString() {
        return "FilesModel{" +
                "id=" + id +
                ", content=" + Arrays.toString(content) +
                ", folderModel=" + folderModel +
                ", commitsModel=" + commitsModel +
                '}';
    }
}
