package com.rentahome.controller;

import com.rentahome.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class PropertyController {

    @Autowired
    PropertyService propertyService;

    @RequestMapping("/newProperty")
    public ModelAndView addProperty() {
        ModelAndView modelAndView = new ModelAndView("newProperty");
        return modelAndView;
    }

}
