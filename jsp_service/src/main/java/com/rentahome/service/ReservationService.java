package com.rentahome.service;

import com.rentahome.dto.ReservationDTO;

import java.util.List;

public interface ReservationService {
    List<ReservationDTO> getOwnerReservation(Integer ownerId);
}
