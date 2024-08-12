package com.rentahome.service;
import com.rentahome.dto.ReservationDTO;
import com.rentahome.entity.Reservation;

import java.util.List;

public interface ReservationService {

    void confirm(Integer reservationId);
    void addReservation(Reservation reservation);
    List<ReservationDTO> getOwnerReservation(Integer ownerId);
    List<ReservationDTO> getOtherReservation(Integer ownerId);

    void reserveProperty(ReservationDTO reservationDTO);
}
