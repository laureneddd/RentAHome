package com.rentahome.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyDTO {

    private Integer propertyId;

    private Integer ownerId;

    private List<ReservationDTO> reservations = new ArrayList<>();

    private List<FeatureDTO> features = new ArrayList<>();

    private String address;
    private String propertyType;

    private LocalDate availableStartDate;
    private LocalDate availableEndDate;

    private double propertyRate;

    private double price;

    private String pictureLocation;

}