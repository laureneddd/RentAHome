package com.rentahome.service;

import com.rentahome.dto.*;
import com.rentahome.entity.*;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class Converter {
    public Feature convertToEntity(FeatureDTO dto) {
        Feature feature = new Feature();
        feature.setFeatureDescription(dto.getFeatureDescription());
        feature.setFeatureName(dto.getFeatureName());
        feature.setFeatureId(dto.getFeatureId());
        return feature;
    }

    public FeatureDTO convertToDto(Feature feature) {
        FeatureDTO dto = new FeatureDTO();
        dto.setFeatureDescription(feature.getFeatureDescription());
        dto.setFeatureName(feature.getFeatureName());
        dto.setFeatureId(feature.getFeatureId());
        return dto;
    }

    public OwnerMail convertToEntity(OwnerMailDTO dto) {
        OwnerMail ownerMail = new OwnerMail();
        ownerMail.setOwnerMailId(dto.getOwnerMailId());
        return ownerMail;
    }

    public OwnerMailDTO convertToDto(OwnerMail ownerMail) {
        OwnerMailDTO dto = new OwnerMailDTO();
        dto.setOwnerMailId(ownerMail.getOwnerMailId());
        dto.setOwnerId(ownerMail.getOwner().getUserId());
        dto.setReservationId(ownerMail.getReservation().getReservationId());
        return dto;
    }

    public Property convertToEntity(PropertyDTO dto) {
        Property property = new Property();
        property.setPropertyId(dto.getPropertyId());
        property.setAddress(dto.getAddress());
        property.setPrice(dto.getPrice());
        property.setPropertyType(dto.getPropertyType());
        property.setFeatures(dto.getFeatures().stream()
                                                .map(this::convertToEntity)
                                                .collect(Collectors.toList()));
        property.setReservations(dto.getReservations().stream()
                                                        .map(this::convertToEntity)
                                                        .collect(Collectors.toList()));
        property.setAvailableStartDate(dto.getAvailableStartDate());
        property.setAvailableEndDate(dto.getAvailableEndDate());
        property.setPropertyRate(dto.getPropertyRate());
        property.setPictureLocation(dto.getPictureLocation());
        return property;
    }

    public PropertyDTO convertToDto(Property property) {
        PropertyDTO dto = new PropertyDTO();
        dto.setOwnerId(property.getOwner().getUserId());
        dto.setPropertyId(property.getPropertyId());
        dto.setAddress(property.getAddress());
        dto.setPrice(property.getPrice());
        dto.setPropertyType(property.getPropertyType());
        dto.setFeatures(property.getFeatures().stream()
                                                .map(this::convertToDto)
                                                    .collect(Collectors.toList()));
        dto.setReservations(property.getReservations().stream()
                                                        .map(this::convertToDto)
                                                            .collect(Collectors.toList()));
        dto.setAvailableStartDate(property.getAvailableStartDate());
        dto.setAvailableEndDate(property.getAvailableEndDate());
        dto.setPropertyRate(property.getPropertyRate());
        dto.setPictureLocation(property.getPictureLocation());
        return dto;
    }

    public Reservation convertToEntity(ReservationDTO dto) {
        Reservation reservation = new Reservation();
        reservation.setReservationId(dto.getReservationId());
        reservation.setEndDate(dto.getEndDate());
        reservation.setStartDate(dto.getStartDate());
        reservation.setStatus(dto.getStatus());
        reservation.setOwnerMail(convertToEntity(dto.getOwnerMailDTO()));
        return reservation;
    }

    public ReservationDTO convertToDto(Reservation reservation) {
        ReservationDTO dto = new ReservationDTO();
        dto.setReservationId(reservation.getReservationId());
        dto.setEndDate(reservation.getEndDate());
        dto.setStartDate(reservation.getStartDate());
        dto.setStatus(reservation.getStatus());
        dto.setUserId(reservation.getUser().getUserId());
        dto.setOwnerMailDTO(convertToDto(reservation.getOwnerMail()));
        dto.setPropertyId(reservation.getProperty().getPropertyId());
        return dto;
    }

    public User convertToEntity(UserDTO dto) {
        User user = new User();
        user.setUserId(dto.getUserId());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        user.setRole(dto.getRole());
        user.setName(dto.getName());
        user.setReservations(dto.getReservations().stream()
                                                    .map(this::convertToEntity)
                                                        .collect(Collectors.toList()));
        user.setProperties(dto.getProperties().stream()
                                                .map(this::convertToEntity)
                                                    .collect(Collectors.toList()));
        user.setOwnerMails(dto.getOwnerMails().stream()
                                                    .map(this::convertToEntity)
                                                        .collect(Collectors.toList()));
        return user;
    }

    public UserDTO convertToDto(User user) {
        UserDTO dto = new UserDTO();
        dto.setUserId(user.getUserId());
        dto.setPassword(user.getPassword());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        dto.setName(user.getName());
        dto.setProperties(user.getProperties().stream()
                                                .map(this::convertToDto)
                                                    .collect(Collectors.toList()));
        dto.setOwnerMails(user.getOwnerMails().stream()
                                                .map(this::convertToDto)
                                                    .collect(Collectors.toList()));
        dto.setReservations(user.getReservations().stream()
                                                    .map(this::convertToDto)
                                                        .collect(Collectors.toList()));
        return dto;
    }


}
