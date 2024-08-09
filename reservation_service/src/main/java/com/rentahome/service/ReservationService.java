package com.rentahome.service;
import com.rentahome.dto.ReservationDTO;
import com.rentahome.entity.OwnerMail;
import com.rentahome.entity.Property;
import com.rentahome.entity.Reservation;

import java.util.List;

public interface ReservationService {

    void confirm(Integer reservationId);
    void addReservation(Reservation reservation);
    void addOwnerMail(OwnerMail ownerMail);
    List<ReservationDTO> getOwnerReservation(Integer ownerId);
}
