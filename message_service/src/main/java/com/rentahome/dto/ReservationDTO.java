package com.rentahome.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {

    private Integer reservationId;

    private Integer propertyId;

    private Integer userId;

    private Date startDate;

    private Date endDate;

    private String status;

    private OwnerMailDTO ownerMailDTO;
}