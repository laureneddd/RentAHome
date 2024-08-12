package com.rentahome.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer propertyTypeId;
    private String propertyTypeName;
}
