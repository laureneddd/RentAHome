package com.rentahome.service;

import com.rentahome.dto.PropertyDTO;

import java.util.List;

public interface PropertyService {
    PropertyDTO getPropertyById(Integer propertyId);
    List<PropertyDTO> getOwnerProperty(Integer ownerId);
    List<PropertyDTO> searchWithAddress(String address);
    void updateProperty(PropertyDTO property);
    void deleteProperty(Integer propertyId);
    void addProperty(PropertyDTO property);
}
