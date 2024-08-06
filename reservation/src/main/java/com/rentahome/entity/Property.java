package com.rentahome.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int propertyId;

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

    @Temporal(TemporalType.DATE)
    private Date availableStartDate;

    @Temporal(TemporalType.DATE)
    private Date availableEndDate;

    private double propertyRate;

}
