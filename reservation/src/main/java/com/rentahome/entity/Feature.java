package com.rentahome.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Feature {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String featureName;
    private String featureDescription;

    @ManyToMany(mappedBy = "features")
    private Set<Property> students = new HashSet<>();

}
