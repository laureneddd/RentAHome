package com.rentahome.controller;

import com.rentahome.dto.*;
import com.rentahome.entity.User;
import com.rentahome.service.PropertyService;
import com.rentahome.service.ReservationService;
import jakarta.servlet.http.HttpServletRequest;
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


    @GetMapping("/owner_dashboard")
    public ModelAndView ownerDashboard(HttpServletRequest request) {
        UserDTO user = (UserDTO) request.getSession().getAttribute("loggedInUser");
        if(user == null) {
            List<PropertyTypeDTO> propertyTypes = propertyService.getPropertyTypes();
            ModelAndView modelAndView = new ModelAndView("index");
            modelAndView.addObject("searchResult", false);
            List<PropertyDTO> propertyDTOS = propertyService.getAllProperty();
            modelAndView.addObject("propertyDTOS", propertyDTOS);
            modelAndView.addObject("propertyTypeDTOS", propertyTypes);
            return modelAndView;
        }
        ModelAndView modelAndView = new ModelAndView("OwnerDashboard");

        List<PropertyDTO> propertyDTOS = propertyService.getOwnerProperty(user.getUserId());
        List<ReservationDTO> reservationDTOS = reservationService.getOwnerReservation(user.getUserId());
        modelAndView.addObject("propertyDTOS", propertyDTOS);
        modelAndView.addObject("reservationDTOS", reservationDTOS);
        return modelAndView;
    }

    @PostMapping("/search_property")
    public ModelAndView searchProperty(@RequestParam("address")String address,
                                       @RequestParam("checkin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkInDate,
                                       @RequestParam("checkout")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOutDate,
                                       @RequestParam("category")String category){


        List<PropertyDTO> propertyDTOS = propertyService.searchAvailableProperty(address, checkInDate, checkOutDate, category);
        List<PropertyTypeDTO> propertyTypeDTOS = propertyService.getPropertyTypes();
        ModelAndView modelAndView = new ModelAndView("index");
        System.out.println(propertyDTOS);
        modelAndView.addObject("propertyDTOS", propertyDTOS);
        modelAndView.addObject("searchResult", true);
        modelAndView.addObject("propertyTypeDTOS", propertyTypeDTOS);
        return modelAndView;
    }

    @RequestMapping("/getOwnerProperty/{ownerId}")
    public List<PropertyDTO> getOwnerProperty(@PathVariable Integer ownerId, HttpServletRequest request) {
        if(request.getSession().getAttribute("loggedInUser") == null) {
            List<PropertyTypeDTO> propertyTypes = propertyService.getPropertyTypes();
            ModelAndView modelAndView = new ModelAndView("index");
            modelAndView.addObject("searchResult", false);
            List<PropertyDTO> propertyDTOS = propertyService.getAllProperty();
            modelAndView.addObject("propertyDTOS", propertyDTOS);
            modelAndView.addObject("propertyTypeDTOS", propertyTypes);
            return new ArrayList<>();
        }
        return propertyService.getOwnerProperty(ownerId);
    }


    @GetMapping("/editeProperty/{propertyId}")
    public ModelAndView editProperty(@PathVariable Integer propertyId, HttpServletRequest request) {
        if (request.getSession().getAttribute("loggedInUser") == null) {
            List<PropertyTypeDTO> propertyTypes = propertyService.getPropertyTypes();
            ModelAndView modelAndView = new ModelAndView("index");
            modelAndView.addObject("searchResult", false);
            List<PropertyDTO> propertyDTOS = propertyService.getAllProperty();
            modelAndView.addObject("propertyDTOS", propertyDTOS);
            modelAndView.addObject("propertyTypeDTOS", propertyTypes);
            return modelAndView;
        }
        System.out.println("this is property edit");
        ModelAndView modelAndView = new ModelAndView("editProperty");
        PropertyDTO property = propertyService.getPropertyById(propertyId);
        List<PropertyTypeDTO> propertyTypes = propertyService.getPropertyTypes();
        modelAndView.addObject("property",property );
        modelAndView.addObject("propertyTypes",propertyTypes );
        return modelAndView;
    }

    @GetMapping("/deleteProperty")
    public ModelAndView deleteProperty(@RequestParam String propertyId,
                                       HttpServletRequest request) {

        propertyService.deleteProperty(Integer.parseInt(propertyId));
        ModelAndView modelAndView = new ModelAndView("OwnerDashboard");
        UserDTO loggedInUser = (UserDTO) request.getSession().getAttribute("loggedInUser");
        if(loggedInUser != null) {
            List<PropertyTypeDTO> propertyTypes = propertyService.getPropertyTypes();
            modelAndView = new ModelAndView("index");
            modelAndView.addObject("searchResult", false);
            List<PropertyDTO> propertyDTOS = propertyService.getAllProperty();
            modelAndView.addObject("propertyDTOS", propertyDTOS);
            modelAndView.addObject("propertyTypeDTOS", propertyTypes);
            return modelAndView;
        }
        Integer ownerId = loggedInUser.getUserId();
        List<PropertyDTO> propertyDTOS = propertyService.getOwnerProperty(ownerId);
        List<ReservationDTO> reservationDTOS = reservationService.getOwnerReservation(ownerId);
        modelAndView.addObject("propertyDTOS", propertyDTOS);
        modelAndView.addObject("reservationDTOS", reservationDTOS);
        return modelAndView;
    }
    @GetMapping("/addProperty/{ownerId}")
    public ModelAndView addProperty(@PathVariable Integer ownerId, HttpServletRequest request) {
        if (request.getSession().getAttribute("loggedInUser") == null) {
            List<PropertyTypeDTO> propertyTypes = propertyService.getPropertyTypes();
            ModelAndView modelAndView = new ModelAndView("index");
            modelAndView.addObject("searchResult", false);
            List<PropertyDTO> propertyDTOS = propertyService.getAllProperty();
            modelAndView.addObject("propertyDTOS", propertyDTOS);
            modelAndView.addObject("propertyTypeDTOS", propertyTypes);
            return modelAndView;
        }
        ModelAndView modelAndView = new ModelAndView("newProperty");
        List<PropertyTypeDTO> propertyTypes = propertyService.getPropertyTypes();
        modelAndView.addObject("propertyTypes", propertyTypes);
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
                                       @RequestParam(value="features", required = false) List<String> features,
                                    HttpServletRequest request) {
        if (request.getSession().getAttribute("loggedInUser") == null) {
            List<PropertyTypeDTO> propertyTypes = propertyService.getPropertyTypes();
            ModelAndView modelAndView = new ModelAndView("index");
            modelAndView.addObject("searchResult", false);
            List<PropertyDTO> propertyDTOS = propertyService.getAllProperty();
            modelAndView.addObject("propertyDTOS", propertyDTOS);
            modelAndView.addObject("propertyTypeDTOS", propertyTypes);
            return modelAndView;
        }
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
        if(features != null) {


            for (String feature : features) {
                FeatureDTO featureDTO = new FeatureDTO(null, feature, feature);
                featureDTOList.add(featureDTO);
            }
        }
        propertyDTO.setFeatures(featureDTOList);
        propertyService.addProperty(propertyDTO);
        ModelAndView modelAndView = new ModelAndView("OwnerDashboard");
        List<PropertyDTO> propertyDTOS = propertyService.getOwnerProperty(Integer.parseInt(ownerId));
        List<ReservationDTO> reservationDTOS = reservationService.getOwnerReservation(Integer.parseInt(ownerId));
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
                                       @RequestParam(value="features", required = false) List<String> features,
                                       HttpServletRequest request) {
        if (request.getSession().getAttribute("loggedInUser") == null) {
            List<PropertyTypeDTO> propertyTypes = propertyService.getPropertyTypes();
            ModelAndView modelAndView = new ModelAndView("index");
            modelAndView.addObject("searchResult", false);
            List<PropertyDTO> propertyDTOS = propertyService.getAllProperty();
            modelAndView.addObject("propertyDTOS", propertyDTOS);
            modelAndView.addObject("propertyTypeDTOS", propertyTypes);
            return modelAndView;
        }
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
        if(features != null) {
            for(String feature : features){
                FeatureDTO featureDTO = new FeatureDTO(null, feature, feature);
                featureDTOList.add(featureDTO);
            }
        }

        propertyDTO.setFeatures(featureDTOList);
        propertyService.updateProperty(propertyDTO);
        ModelAndView modelAndView = new ModelAndView("OwnerDashboard");
        List<PropertyDTO> propertyDTOS = propertyService.getOwnerProperty(Integer.parseInt(ownerId));
        List<ReservationDTO> reservationDTOS = reservationService.getOwnerReservation(Integer.parseInt(ownerId));
        modelAndView.addObject("propertyDTOS", propertyDTOS);
        modelAndView.addObject("reservationDTOS", reservationDTOS);
        return modelAndView;
    }

}
