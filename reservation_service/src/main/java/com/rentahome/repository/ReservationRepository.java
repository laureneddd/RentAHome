package com.rentahome.repository;

import com.rentahome.entity.Property;
import com.rentahome.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    @Query("SELECT r FROM Reservation r WHERE r.property.propertyId = :propertyId")
    List<Reservation> findByPropertyId(@Param("propertyId") Integer propertyId);
}
