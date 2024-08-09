package com.rentahome.service.implement;


import com.rentahome.dto.PropertyDTO;
import com.rentahome.entity.Feature;
import com.rentahome.entity.Property;
import com.rentahome.entity.User;
import com.rentahome.repository.FeatureRepository;
import com.rentahome.repository.PropertyRepository;
import com.rentahome.service.Converter;
import com.rentahome.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class PropertyServiceImpl implements PropertyService {
    @Autowired
    private FeatureRepository featureRepository;
    @Autowired
    private Converter converter;

    @Autowired
    private PropertyRepository propertyRepository;

    @Override
    public PropertyDTO getProperty(Integer propertyId){
        return converter.convertToDto(propertyRepository.findById(propertyId).orElse(null));
    }

    @Override
    public List<PropertyDTO> getOwnerProperties(Integer ownerId){
        List<Property> properties = propertyRepository.findByUserId(ownerId);
        return properties.stream().map(converter::convertToDto).collect(Collectors.toList());
    }

    @Override
    public void deleteProperty(Integer propertyId){
        propertyRepository.deleteById(propertyId);
    }

    @Override
    public void updateProperty(PropertyDTO dto){
        User user = new User();
        user.setUserId(dto.getOwnerId());
        Property property = converter.convertToEntity(dto);
        property.setOwner(user);
        List<Feature> existingFeatures = new ArrayList<>();
        for (Feature feature : property.getFeatures()) {
            if (feature.getFeatureId() != null) {
                // Load the existing feature from the database
                Feature existingFeature = featureRepository.findById(feature.getFeatureId())
                        .orElseThrow(() -> new RuntimeException("Feature not found with id: " + feature.getFeatureId()));
                if(!existingFeatures.contains(existingFeature)){
                    existingFeatures.add(existingFeature);
                }
            } else {
                // Handle the case where the feature is new
                existingFeatures.add(feature);
            }
        }
        featureRepository.saveAll(existingFeatures);
        property.setFeatures(existingFeatures);
        propertyRepository.save(property);
    }
    @Override
    public void addProperty(PropertyDTO dto){
        User user = new User();
        user.setUserId(dto.getOwnerId());
        Property property = converter.convertToEntity(dto);
        property.setOwner(user);
        List<Feature> existingFeatures = new ArrayList<>();
        for (Feature feature : property.getFeatures()) {
            if (feature.getFeatureId() != null) {
                // Load the existing feature from the database
                Feature existingFeature = featureRepository.findById(feature.getFeatureId())
                        .orElseThrow(() -> new RuntimeException("Feature not found with id: " + feature.getFeatureId()));
                if(!existingFeatures.contains(existingFeature)){
                    existingFeatures.add(existingFeature);
                }
            } else {
                // Handle the case where the feature is new
                existingFeatures.add(feature);
            }
        }
        featureRepository.saveAll(existingFeatures);
        property.setFeatures(existingFeatures);
        propertyRepository.save(property);
    }
    @Override
    public List<Property> getAllProperties(){
        return propertyRepository.findAll();
    }

    @Override
    public List<PropertyDTO> searchWithAddress(String address){
        return propertyRepository.findByAddressContaining(address).stream().map(converter::convertToDto).collect(Collectors.toList());

    }
}
