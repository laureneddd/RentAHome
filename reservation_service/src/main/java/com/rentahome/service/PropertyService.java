package com.rentahome.service;

import com.rentahome.dto.PropertyDTO;
import com.rentahome.entity.Property;

import java.time.LocalDate;
import java.util.List;

public interface PropertyService {
    PropertyDTO getProperty(Integer propertyId);
    List<PropertyDTO> getOwnerProperties(Integer ownerId);
    void deleteProperty(Integer propertyId);
    void updateProperty(PropertyDTO property);
    void addProperty(PropertyDTO property);
    List<PropertyDTO> searchWithAddress(String address);
    List<PropertyDTO> getAllProperties();
    List<PropertyDTO> searchAvailableProperty(String address, LocalDate checkInDate, LocalDate checkOutDate);
}
