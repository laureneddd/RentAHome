package com.rentahome.repository;

import com.rentahome.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Integer> {
    @Query("SELECT p FROM Property p WHERE p.owner.userId = :userId")
    List<Property> findByUserId(@Param("userId") Integer userId);

    List<Property> findByAddressContaining(String address);
}
