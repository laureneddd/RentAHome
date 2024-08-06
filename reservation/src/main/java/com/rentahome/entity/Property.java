package com.rentahome.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer propertyId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User owner;

    @ManyToMany
    @JoinTable(
            name = "property_feature",
            joinColumns = @JoinColumn(name = "propertyId"),
            inverseJoinColumns = @JoinColumn(name = "featureId")
    )
    private Set<Feature> features = new HashSet<>();

    private String Address;
    private String propertyType;

    private LocalDate availableStartDate;
    private LocalDate availableEndDate;

    private double propertyRate;

    private double price;

    private String pictureLocation;

}
