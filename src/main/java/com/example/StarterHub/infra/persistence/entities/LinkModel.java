package com.example.StarterHub.infra.persistence.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "Links")
public class LinkModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "link_id")
    private UUID id;

    private String link;

    @ManyToOne
    @JoinColumn(name = "id_user_properties", referencedColumnName = "user_properties_id", nullable = false)
    private UserPropertiesModel userPropertiesModel;

    public LinkModel(UUID id, String link, UserPropertiesModel userPropertiesModel) {
        this.id = id;
        this.link = link;
        this.userPropertiesModel = userPropertiesModel;
    }

    public LinkModel() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public UserPropertiesModel getUserPropertiesModel() {
        return userPropertiesModel;
    }

    public void setUserPropertiesModel(UserPropertiesModel userPropertiesModel) {
        this.userPropertiesModel = userPropertiesModel;
    }

    @Override
    public String toString() {
        return "LinkModel{" +
                "id=" + id +
                ", link='" + link + '\'' +
                ", userPropertiesModel=" + userPropertiesModel +
                '}';
    }
}
