package com.rentahome.service;

import com.rentahome.dto.PropertyDTO;
import com.rentahome.dto.PropertyTypeDTO;

import java.time.LocalDate;
import java.util.List;

public interface PropertyService {
    PropertyDTO getPropertyById(Integer propertyId);
    List<PropertyDTO> getOwnerProperty(Integer ownerId);
    List<PropertyDTO> searchWithAddress(String address);
    void updateProperty(PropertyDTO property);
    void deleteProperty(Integer propertyId);
    void addProperty(PropertyDTO property);
    List<PropertyTypeDTO> getPropertyTypes();
    List<PropertyDTO> searchAvailableProperty(String address, LocalDate checkInDate,LocalDate checkOutDate, String category);
    List<PropertyDTO> getAllProperty();
}
