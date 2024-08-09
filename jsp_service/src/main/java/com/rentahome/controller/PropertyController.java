package com.rentahome.controller;

import com.rentahome.dto.FeatureDTO;
import com.rentahome.dto.PropertyDTO;
import com.rentahome.dto.ReservationDTO;
import com.rentahome.service.PropertyService;
import com.rentahome.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
//@RequestMapping("/property")
public class PropertyController {
    @Autowired
    private PropertyService propertyService;
    @Autowired
    private ReservationService reservationService;


    @PostMapping("/search_property")
    public ModelAndView searchProperty(@RequestParam("address")String address,
                                       @RequestParam("checkin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkInDate,
                                       @RequestParam("checkout")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOutDate){

        List<PropertyDTO> propertyDTOS = propertyService.searchWithAddress(address);
        ModelAndView modelAndView = new ModelAndView("index");
        System.out.println(propertyDTOS);
        modelAndView.addObject("propertyDTOS", propertyDTOS);
        modelAndView.addObject("searchResult", true);
        return modelAndView;
    }

    @RequestMapping("/getOwnerProperty/{ownerId}")
    public List<PropertyDTO> getOwnerProperty(@PathVariable Integer ownerId){
        return propertyService.getOwnerProperty(ownerId);
    }


    @GetMapping("/editeProperty/{propertyId}")
    public ModelAndView editProperty(@PathVariable Integer propertyId) {
        System.out.println("this is property edit");
        ModelAndView modelAndView = new ModelAndView("editProperty");
        PropertyDTO property = propertyService.getPropertyById(propertyId);
        modelAndView.addObject("property",property );
        return modelAndView;
    }

    @GetMapping("/deleteProperty")
    public ModelAndView deleteProperty(@RequestParam String propertyId,
                                       @RequestParam String ownerId) {

        propertyService.deleteProperty(Integer.parseInt(propertyId));
        ModelAndView modelAndView = new ModelAndView("OwnerDashboard");
        List<PropertyDTO> propertyDTOS = propertyService.getOwnerProperty(Integer.parseInt(ownerId));
        List<ReservationDTO> reservationDTOS = reservationService.getOwnerReservation(Integer.parseInt(ownerId));
        modelAndView.addObject("ownerId", Integer.parseInt(ownerId));
        modelAndView.addObject("propertyDTOS", propertyDTOS);
        modelAndView.addObject("reservationDTOS", reservationDTOS);
        return modelAndView;
    }
    @GetMapping("/addProperty/{ownerId}")
    public ModelAndView addProperty(@PathVariable Integer ownerId) {
        ModelAndView modelAndView = new ModelAndView("newProperty");
        modelAndView.addObject("ownerId", ownerId);
        return modelAndView;
    }
    @PostMapping("/addProperty")
    public ModelAndView addProperty(@RequestParam String propertyId,
                                       @RequestParam String ownerId,
                                       @RequestParam String address,
                                       @RequestParam String price,
                                       @RequestParam String picture,
                                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate availableStartDate,
                                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate availableEndDate,
                                       @RequestParam String propertyType,
                                       @RequestParam(value="features", required = false) List<String> features){
        PropertyDTO propertyDTO = new PropertyDTO();
        propertyDTO.setPropertyId(null);
        propertyDTO.setOwnerId(Integer.parseInt(ownerId));
        propertyDTO.setAddress(address);
        propertyDTO.setPrice(Double.parseDouble(price));
        propertyDTO.setAvailableStartDate(availableStartDate);
        propertyDTO.setAvailableEndDate(availableEndDate);
        propertyDTO.setPropertyType(propertyType);
        propertyDTO.setPictureLocation(picture);
        List<FeatureDTO> featureDTOList = new ArrayList<>();
        for(String feature : features){
            FeatureDTO featureDTO = new FeatureDTO(null, feature, feature);
            featureDTOList.add(featureDTO);
        }
        propertyDTO.setFeatures(featureDTOList);
        propertyService.addProperty(propertyDTO);
        ModelAndView modelAndView = new ModelAndView("OwnerDashboard");
        List<PropertyDTO> propertyDTOS = propertyService.getOwnerProperty(Integer.parseInt(ownerId));
        List<ReservationDTO> reservationDTOS = reservationService.getOwnerReservation(Integer.parseInt(ownerId));
        modelAndView.addObject("ownerId", Integer.parseInt(ownerId));
        modelAndView.addObject("propertyDTOS", propertyDTOS);
        modelAndView.addObject("reservationDTOS", reservationDTOS);
        return modelAndView;
    }

    @PostMapping("/updateProperty")
    public ModelAndView updateProperty(@RequestParam String propertyId,
                                       @RequestParam String ownerId,
                                       @RequestParam String address,
                                       @RequestParam String price,
                                       @RequestParam String picture,
                                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate availableStartDate,
                                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate availableEndDate,
                                       @RequestParam String propertyType,
                                       @RequestParam(value="features", required = false) List<String> features){
        PropertyDTO propertyDTO = new PropertyDTO();
        propertyDTO.setPropertyId(Integer.parseInt(propertyId));
        propertyDTO.setOwnerId(Integer.parseInt(ownerId));
        propertyDTO.setAddress(address);
        propertyDTO.setPrice(Double.parseDouble(price));
        propertyDTO.setAvailableStartDate(availableStartDate);
        propertyDTO.setAvailableEndDate(availableEndDate);
        propertyDTO.setPropertyType(propertyType);
        propertyDTO.setPictureLocation(picture);
        List<FeatureDTO> featureDTOList = new ArrayList<>();
        for(String feature : features){
            FeatureDTO featureDTO = new FeatureDTO(null, feature, feature);
            featureDTOList.add(featureDTO);
        }
        propertyDTO.setFeatures(featureDTOList);
        propertyService.updateProperty(propertyDTO);
        ModelAndView modelAndView = new ModelAndView("OwnerDashboard");
        List<PropertyDTO> propertyDTOS = propertyService.getOwnerProperty(Integer.parseInt(ownerId));
        List<ReservationDTO> reservationDTOS = reservationService.getOwnerReservation(Integer.parseInt(ownerId));
        modelAndView.addObject("ownerId", Integer.parseInt(ownerId));
        modelAndView.addObject("propertyDTOS", propertyDTOS);
        modelAndView.addObject("reservationDTOS", reservationDTOS);
        return modelAndView;
    }

}
