package com.rentahome.repository;

import com.rentahome.entity.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FeatureRepository extends JpaRepository<Feature, Integer> {
}
