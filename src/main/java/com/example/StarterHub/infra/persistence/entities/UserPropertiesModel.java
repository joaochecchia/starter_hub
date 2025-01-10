package com.example.StarterHub.infra.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.UUID;

@Entity
@Table(name = "user_properties")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private UserModel userModel;

    @OneToMany(mappedBy = "userPropertiesModel")
    private ArrayList<LinkModel> linkModel;

    @OneToOne(mappedBy = "userPropertiesModel")
    private AddressModel addressModel;

    @OneToMany(mappedBy = "userPropertiesModel")
    private ArrayList<RepositoryModel> repositoryModel;

    public UserPropertiesModel(UUID id, String description, byte[] photo, String company, UserModel userModel, ArrayList<LinkModel> linkModel, AddressModel addressModel, ArrayList<RepositoryModel> repositoryModel) {
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

    public ArrayList<RepositoryModel> getRepositoryModel() {
        return repositoryModel;
    }

    public void setRepositoryModel(ArrayList<RepositoryModel> repositoryModel) {
        this.repositoryModel = repositoryModel;
    }

    public AddressModel getAddressModel() {
        return addressModel;
    }

    public void setAddressModel(AddressModel addressModel) {
        this.addressModel = addressModel;
    }

    public ArrayList<LinkModel> getLinkModel() {
        return linkModel;
    }

    public void setLinkModel(ArrayList<LinkModel> linkModel) {
        this.linkModel = linkModel;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
