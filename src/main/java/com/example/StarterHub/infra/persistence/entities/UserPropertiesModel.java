package com.example.StarterHub.infra.persistence.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "user_properties")
public class UserPropertiesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_properties_id", nullable = false, unique = true, updatable = false)
    private UUID id;

    private String description;

    @Lob
    private byte[] photo;

    private String company;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserModel userModel;

    @OneToMany(mappedBy = "userPropertiesModel")
    private List<LinkModel> linkModel;

    @OneToOne(mappedBy = "userPropertiesModel")
    private AddressModel addressModel;

    @OneToMany(mappedBy = "userPropertiesModel")
    private List<RepositoryModel> repositoryModel;

    public UserPropertiesModel(UUID id, String description, byte[] photo, String company, UserModel userModel, List<LinkModel> linkModel, AddressModel addressModel, List<RepositoryModel> repositoryModel) {
        this.id = id;
        this.description = description;
        this.photo = photo;
        this.company = company;
        this.userModel = userModel;
        this.linkModel = linkModel;
        this.addressModel = addressModel;
        this.repositoryModel = repositoryModel;
    }

    public UserPropertiesModel() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public List<LinkModel> getLinkModel() {
        return linkModel;
    }

    public void setLinkModel(List<LinkModel> linkModel) {
        this.linkModel = linkModel;
    }

    public AddressModel getAddressModel() {
        return addressModel;
    }

    public void setAddressModel(AddressModel addressModel) {
        this.addressModel = addressModel;
    }

    public List<RepositoryModel> getRepositoryModel() {
        return repositoryModel;
    }

    public void setRepositoryModel(List<RepositoryModel> repositoryModel) {
        this.repositoryModel = repositoryModel;
    }
}
