package com.rentahome.service;

import com.rentahome.dto.PropertyDTO;
import com.rentahome.dto.ReservationDTO;

import java.time.LocalDate;
import java.util.List;

public interface ReservationService {
    List<ReservationDTO> getOwnerReservation(Integer ownerId);
    List<ReservationDTO> getOtherReservation(Integer userId);
    void reserveProperty(Integer userId, LocalDate checkin, LocalDate checkout, PropertyDTO property);
    void confirmReservation(Integer reservationId);
}
