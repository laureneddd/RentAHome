package com.rentahome.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Feature {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer featureId;

    private String featureName;
    private String featureDescription;

    @ManyToMany(mappedBy = "features")
    private List<Property> properties = new ArrayList<>();

}
