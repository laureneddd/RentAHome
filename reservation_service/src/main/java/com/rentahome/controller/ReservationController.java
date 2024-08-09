package com.rentahome.controller;

import com.rentahome.dto.OwnerMailDTO;
import com.rentahome.dto.PropertyDTO;
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

    @PostMapping("/addReservation")
    public void addReservation(@RequestBody ReservationDTO dto) {
        reservationService.addReservation(converter.convertToEntity(dto));
    }

    @PostMapping("/addOwnerMail")
    public void addOwnerMail(@RequestBody OwnerMailDTO dto) {
        reservationService.addOwnerMail(converter.convertToEntity(dto));
    }

    @GetMapping("/getOwnerReservation/{ownerId}")
    public ResponseEntity<List<ReservationDTO>> getOwnerReservation(@PathVariable Integer ownerId) {
        List<ReservationDTO> reservationDTOS = reservationService.getOwnerReservation(ownerId);
        return ResponseEntity.ok(reservationDTOS);
    }
}
