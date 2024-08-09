package com.rentahome.controller;

import com.rentahome.dto.PropertyDTO;
import com.rentahome.service.Converter;
import com.rentahome.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/property")
public class PropertyController {

    @Autowired
    PropertyService propertyService;

    @GetMapping( "/searchWithAddress/")
    public ResponseEntity<List<PropertyDTO>> searchWithAddress(@RequestParam String address) {
        List<PropertyDTO> propertyDTOS = propertyService.searchWithAddress(address);
        return ResponseEntity.ok(propertyDTOS);
    }
    @GetMapping("/getProperty/{propertyId}")
    public PropertyDTO getProperty(@PathVariable Integer propertyId) {
        System.out.println(propertyId);
        return propertyService.getProperty(propertyId);
    }

    @GetMapping("/getOwnerProperty/{ownerId}")
    public ResponseEntity<List<PropertyDTO>> getOwnerProperty(@PathVariable Integer ownerId) {
        List<PropertyDTO> propertyDTOS = propertyService.getOwnerProperties(ownerId);
        return ResponseEntity.ok(propertyDTOS);
    }

    @DeleteMapping("/{propertyId}")
    public void deleteProperty(@PathVariable Integer propertyId) {
        propertyService.deleteProperty(propertyId);
    }

//    @PostMapping("/updateProperty")
//    public void updateProperty(@RequestBody PropertyDTO propertyDTO) {
//        propertyService.updateProperty(propertyDTO);
//    }
    @PutMapping("/updateProperty")
    public void updateProperty(@RequestBody PropertyDTO propertyDTO) {
        propertyService.updateProperty(propertyDTO);
    }
    @PostMapping("/addProperty")
    public void addProperty(@RequestBody PropertyDTO propertyDTO) {
        propertyService.addProperty(propertyDTO);
    }
}
