package com.example.StarterHub.infra.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "address")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
}
