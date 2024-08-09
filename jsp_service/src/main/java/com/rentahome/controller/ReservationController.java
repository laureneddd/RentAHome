package com.rentahome.controller;

import com.rentahome.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("/reservation")
public class ReservationController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ReservationService reservationService;

    @RequestMapping("/health")
    public String healthCheck(){
        return "Server is health";
    }

    @RequestMapping("/property")
    public ModelAndView property() {

        ModelAndView modelAndView = new ModelAndView("property");

        return modelAndView;
    }
}
