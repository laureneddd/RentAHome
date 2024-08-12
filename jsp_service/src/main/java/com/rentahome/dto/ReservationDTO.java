package com.rentahome.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {

    private Integer reservationId;

    private Integer propertyId;

    private Integer userId;

    private LocalDate startDate;

    private LocalDate endDate;

    private String status;
}
