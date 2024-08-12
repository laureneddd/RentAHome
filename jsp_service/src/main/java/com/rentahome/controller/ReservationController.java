package com.rentahome.controller;

import com.rentahome.dto.PropertyDTO;
import com.rentahome.dto.PropertyTypeDTO;
import com.rentahome.dto.ReservationDTO;
import com.rentahome.dto.UserDTO;
import com.rentahome.entity.User;
import com.rentahome.service.PropertyService;
import com.rentahome.service.ReservationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@RestController
//@RequestMapping("/reservation")
public class ReservationController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private PropertyService propertyService;

    @GetMapping("/booking/{PropertyId}")// "/" represents the very first page of your application
    public ModelAndView bookingPage(@PathVariable Integer PropertyId, HttpServletRequest request) {
        if(request.getSession().getAttribute("loggedInUser") == null){
            List<PropertyTypeDTO> propertyTypes = propertyService.getPropertyTypes();
            ModelAndView modelAndView = new ModelAndView("index");
            modelAndView.addObject("searchResult", false);
            List<PropertyDTO> propertyDTOS = propertyService.getAllProperty();
            modelAndView.addObject("propertyDTOS", propertyDTOS);
            modelAndView.addObject("propertyTypeDTOS", propertyTypes);
            return modelAndView;
        }
        PropertyDTO propertyDTO = propertyService.getPropertyById(PropertyId);
        ModelAndView modelAndView = new ModelAndView("booking");
        modelAndView.addObject("propertyDTO", propertyDTO);
        return modelAndView;
    }

    @PostMapping("/reservationConfirm")
    public ModelAndView confirmPage(@RequestParam String checkin, @RequestParam String checkout, HttpServletRequest request) {
        if(request.getSession().getAttribute("loggedInUser") == null){
            List<PropertyTypeDTO> propertyTypes = propertyService.getPropertyTypes();
            ModelAndView modelAndView = new ModelAndView("index");
            modelAndView.addObject("searchResult", false);
            List<PropertyDTO> propertyDTOS = propertyService.getAllProperty();
            modelAndView.addObject("propertyDTOS", propertyDTOS);
            modelAndView.addObject("propertyTypeDTOS", propertyTypes);
            return modelAndView;
        }
        LocalDate checkinDate = LocalDate.parse(checkin);
        LocalDate checkoutDate = LocalDate.parse(checkout);
        UserDTO userDTO = (UserDTO) request.getSession().getAttribute("loggedInUser");
        PropertyDTO propertyDTO = (PropertyDTO) request.getSession().getAttribute("propertyDTO");
        System.out.println(propertyDTO);
        reservationService.reserveProperty(userDTO.getUserId(), checkinDate, checkoutDate, propertyDTO);
        ModelAndView modelAndView = new ModelAndView("owner_confirmation");
        return modelAndView;
    }
    @GetMapping("/confirmReservation/{reservationId}")
    public ModelAndView confirmReservation(@PathVariable Integer reservationId, HttpServletRequest request) {
        if(request.getSession().getAttribute("loggedInUser") == null){
            List<PropertyTypeDTO> propertyTypes = propertyService.getPropertyTypes();
            ModelAndView modelAndView = new ModelAndView("index");
            modelAndView.addObject("searchResult", false);
            List<PropertyDTO> propertyDTOS = propertyService.getAllProperty();
            modelAndView.addObject("propertyDTOS", propertyDTOS);
            modelAndView.addObject("propertyTypeDTOS", propertyTypes);
            return modelAndView;
        }
        reservationService.confirmReservation(reservationId);
        ModelAndView modelAndView = new ModelAndView("owner_confirmation");
        return modelAndView;
    }

    @GetMapping("/reservation_dashboard")
    public ModelAndView reservationDashboard(HttpServletRequest request) {
        UserDTO userDTO = (UserDTO) request.getSession().getAttribute("loggedInUser");
        ModelAndView modelAndView = new ModelAndView("ReservationDashboard");
        if(userDTO == null) {
            List<PropertyTypeDTO> propertyTypes = propertyService.getPropertyTypes();
            modelAndView = new ModelAndView("index");
            modelAndView.addObject("searchResult", false);
            List<PropertyDTO> propertyDTOS = propertyService.getAllProperty();
            modelAndView.addObject("propertyDTOS", propertyDTOS);
            modelAndView.addObject("propertyTypeDTOS", propertyTypes);
            return modelAndView;
        }
        List<PropertyDTO> propertyDTOS = new ArrayList<>();
        List<ReservationDTO> reservationDTOS = null;
        if(userDTO.getRole().equals("Owner")) {
            propertyDTOS = propertyService.getOwnerProperty(userDTO.getUserId());
            reservationDTOS = reservationService.getOwnerReservation(userDTO.getUserId());
        }else{
            reservationDTOS = reservationService.getOtherReservation(userDTO.getUserId());
            for(ReservationDTO reservationDTO : reservationDTOS) {
                propertyDTOS.add(propertyService.getPropertyById(reservationDTO.getPropertyId()));
            }
        }
        modelAndView.addObject("reservationDTOS", reservationDTOS);
        modelAndView.addObject("propertyDTOS", propertyDTOS);
        return modelAndView;
    }
}
