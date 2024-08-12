package com.rentahome.controller;

import com.rentahome.dto.PropertyDTO;
import com.rentahome.dto.PropertyTypeDTO;
import com.rentahome.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class HomeController {
    @Autowired
    PropertyService propertyService;

    @GetMapping("/")// "/" represents the very first page of your application
    public ModelAndView homePage() {
        List<PropertyTypeDTO> propertyTypes = propertyService.getPropertyTypes();
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("searchResult", false);
        List<PropertyDTO> propertyDTOS = propertyService.getAllProperty();
        modelAndView.addObject("propertyDTOS", propertyDTOS);
        modelAndView.addObject("propertyTypeDTOS", propertyTypes);
        return modelAndView;
    }
}
