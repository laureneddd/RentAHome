package com.rentahome.service.implement;

import com.rentahome.dto.PropertyDTO;
import com.rentahome.dto.PropertyTypeDTO;
import com.rentahome.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String PROPERTY_SERVICE_URL = "http://localhost:8081/property";
    @Override
    public List<PropertyDTO> getAllProperty(){
        ResponseEntity<List<PropertyDTO>> rateResponse =
                restTemplate.exchange(PROPERTY_SERVICE_URL+"/getAllProperty",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<PropertyDTO>>() {
                        });
        List<PropertyDTO> propertyDTOS = rateResponse.getBody();
        if (propertyDTOS == null) {
            propertyDTOS = new ArrayList<>();
        }
        return propertyDTOS;
    }
    @Override
    public void deleteProperty(Integer propertyId){
        restTemplate.delete(PROPERTY_SERVICE_URL+"/"+propertyId);
    }
    @Override
    public List<PropertyDTO> getOwnerProperty(Integer ownerId) {
         ResponseEntity<List<PropertyDTO>> rateResponse =
                restTemplate.exchange(PROPERTY_SERVICE_URL+"/getOwnerProperty/"+ownerId,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<PropertyDTO>>() {
                        });
        List<PropertyDTO> propertyDTOS = rateResponse.getBody();
        if (propertyDTOS == null) {
            propertyDTOS = new ArrayList<>();
        }
        return propertyDTOS;
    }

    @Override
    public PropertyDTO getPropertyById(Integer propertyId) {
        return restTemplate.getForObject(PROPERTY_SERVICE_URL+"/getProperty/"+propertyId, PropertyDTO.class);
    }

    @Override
    public List<PropertyDTO> searchWithAddress(String address){
        URI uri = UriComponentsBuilder.fromHttpUrl(PROPERTY_SERVICE_URL+"/searchWithAddress/")
                .queryParam("address", address)
                .build()
                .toUri();

        ResponseEntity<List<PropertyDTO>> rateResponse =
                restTemplate.exchange(uri,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<PropertyDTO>>() {
                        });
        List<PropertyDTO> propertyDTOS = rateResponse.getBody();
        return propertyDTOS;
    }
    @Override
    public List<PropertyDTO> searchAvailableProperty(String address, LocalDate checkInDate, LocalDate checkOutDate, String category){
        URI uri = UriComponentsBuilder.fromHttpUrl(PROPERTY_SERVICE_URL+"/searchAvailableProperty")
                .queryParam("address", address)
                .queryParam("checkInDate", checkInDate.toString())
                .queryParam("checkOutDate", checkOutDate.toString())
                .build()
                .toUri();

        ResponseEntity<List<PropertyDTO>> rateResponse =
                restTemplate.exchange(uri,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<PropertyDTO>>() {
                        });
        List<PropertyDTO> propertyDTOS = rateResponse.getBody();
        propertyDTOS = propertyDTOS.stream().filter(propertyDTO -> {return propertyDTO.getPropertyType().equals(category);}).collect(Collectors.toList());
        return propertyDTOS;
    }

    @Override
    public void updateProperty(PropertyDTO property){
        restTemplate.put(PROPERTY_SERVICE_URL+"/updateProperty", property);
    }
    @Override
    public void addProperty(PropertyDTO property){
        restTemplate.postForObject(PROPERTY_SERVICE_URL+"/addProperty", property,PropertyDTO.class);
    }

    @Override
    public List<PropertyTypeDTO> getPropertyTypes() {
        ResponseEntity<List<PropertyTypeDTO>> rateResponse =
                restTemplate.exchange(PROPERTY_SERVICE_URL+"/getPropertyType",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<PropertyTypeDTO>>() {
                        });
        List<PropertyTypeDTO> propertyTypeDTOS = rateResponse.getBody();
        return propertyTypeDTOS;
    }
}
