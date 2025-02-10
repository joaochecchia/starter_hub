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


    public FilesModel(UUID id, byte[] content, FolderModel folderModel) {
        this.id = id;
        this.content = content;
        this.folderModel = folderModel;
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

    @Override
    public String toString() {
        return "FilesModel{" +
                "id=" + id +
                ", content=" + Arrays.toString(content) +
                ", folderModel=" + folderModel + '}';
    }
}
