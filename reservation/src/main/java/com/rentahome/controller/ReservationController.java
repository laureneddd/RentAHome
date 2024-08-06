package com.rentahome.controller;

import com.rentahome.entity.Property;
import com.rentahome.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @RequestMapping("/")
    public String home() {
        return "index";
    }

    @RequestMapping("/property")
    public ModelAndView property() {

        ModelAndView modelAndView = new ModelAndView("property");

        List<Property> propertyList = reservationService.getAllProperties();
        modelAndView.addObject("properties", propertyList);


        return modelAndView;
    }
}
