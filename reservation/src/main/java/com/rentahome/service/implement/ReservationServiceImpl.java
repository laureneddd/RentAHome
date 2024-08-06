package com.rentahome.service.implement;

import com.rentahome.entity.Property;
import com.rentahome.repository.PropertyRepository;
import com.rentahome.repository.ReservationRepository;
import com.rentahome.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Override
    public List<Property> getAllProperties(){
        return propertyRepository.findAll();
    }

}
