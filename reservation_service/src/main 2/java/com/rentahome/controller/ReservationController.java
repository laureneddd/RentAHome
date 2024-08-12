package com.rentahome.controller;

import com.rentahome.dto.ReservationDTO;
import com.rentahome.service.Converter;
import com.rentahome.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private Converter converter;

    @GetMapping("/")
    public String healthCheck() {
        return "Server is health";
    }

    @GetMapping("/confirmReservation/{reservationId}")
    public void confirmReservation(@PathVariable Integer reservationId) {
        reservationService.confirm(reservationId);
    }

    @PostMapping("/reserveProperty")
    public void addReservation(@RequestBody ReservationDTO dto) {
        reservationService.reserveProperty(dto);
    }


    @GetMapping("/getOwnerReservation/{ownerId}")
    public ResponseEntity<List<ReservationDTO>> getOwnerReservation(@PathVariable Integer ownerId) {
        List<ReservationDTO> reservationDTOS = reservationService.getOwnerReservation(ownerId);
        return ResponseEntity.ok(reservationDTOS);
    }
    @GetMapping("/getOtherReservation/{userId}")
    public ResponseEntity<List<ReservationDTO>> getOtherReservation(@PathVariable Integer userId) {
        List<ReservationDTO> reservationDTOS = reservationService.getOtherReservation(userId);
        return ResponseEntity.ok(reservationDTOS);
    }
}
