package com.example.StarterHub.infra.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "address")
public class AddressModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "address_id")
    private UUID id;

    private String country;

    @Column(name = "postal_code")
    private String postalCode;

    private String location;

    @OneToOne
    @JoinColumn(name = "id_user_properties", referencedColumnName = "user_properties_id", nullable = false)
    private UserPropertiesModel userPropertiesModel;

    public AddressModel(UUID id, String country,  String postalCode, String location, UserPropertiesModel userPropertiesModel) {
        this.id = id;
        this.country = country;
        this.postalCode = postalCode;
        this.location = location;
        this.userPropertiesModel = userPropertiesModel;
    }

    public AddressModel() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public UserPropertiesModel getUserPropertiesModel() {
        return userPropertiesModel;
    }

    public void setUserPropertiesModel(UserPropertiesModel userPropertiesModel) {
        this.userPropertiesModel = userPropertiesModel;
    }

    @Override
    public String toString() {
        return "AddressModel{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", location='" + location + '\'' +
                ", userPropertiesModel=" + userPropertiesModel +
                '}';
    }
}
