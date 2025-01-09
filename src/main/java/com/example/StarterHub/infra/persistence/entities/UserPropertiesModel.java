package com.example.StarterHub.infra.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.UUID;

@Entity
@Table(name = "user_properties")
@Data
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
}
