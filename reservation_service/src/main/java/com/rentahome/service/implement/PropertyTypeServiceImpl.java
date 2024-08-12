package com.rentahome.service.implement;

import com.rentahome.dto.PropertyDTO;
import com.rentahome.dto.PropertyTypeDTO;
import com.rentahome.entity.Property;
import com.rentahome.entity.PropertyType;
import com.rentahome.repository.PropertyTypeRepository;
import com.rentahome.service.Converter;
import com.rentahome.service.PropertyTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropertyTypeServiceImpl implements PropertyTypeService {
    @Autowired
    PropertyTypeRepository propertyTypeRepository;

    @Autowired
    Converter converter;
    @Override
    public List<PropertyTypeDTO> getPropertyTypes(){
        List<PropertyType> propertyTypes = propertyTypeRepository.findAll();
        return propertyTypes.stream().map(converter::convertToDto).collect(Collectors.toList());
    }
}
