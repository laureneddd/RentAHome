package com.rentahome.service.implement;

import com.rentahome.dto.ReservationDTO;
import com.rentahome.entity.Property;
import com.rentahome.entity.Reservation;
import com.rentahome.repository.PropertyRepository;
import com.rentahome.repository.ReservationRepository;
import com.rentahome.service.Converter;
import com.rentahome.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    Converter converter;

    @Override
    public void reserveProperty(ReservationDTO dto){
        Reservation reservation = converter.convertToEntity(dto);
        reservationRepository.save(reservation);
    }
   @Override
   public List<ReservationDTO> getOwnerReservation(Integer ownerId){
       List<Property> properties = propertyRepository.findByUserId(ownerId);

       List<Reservation> reservations = new ArrayList<>();
       for(Property p : properties){
           reservations.addAll(reservationRepository.findByPropertyId(p.getPropertyId()));
       }
       if(reservations.isEmpty()){
           return new ArrayList<>();
       }
       return reservations.stream().map(converter::convertToDto).collect(Collectors.toList());
   }

   @Override
   public List<ReservationDTO> getOtherReservation(Integer userId){

       List<Reservation> reservations  = reservationRepository.findByUser_UserId(userId);
       return reservations.stream().map(converter::convertToDto).collect(Collectors.toList());
   }

    @Override
    public void confirm(Integer reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
        if (reservation != null) {
            reservation.setStatus("Confirmed");
            reservationRepository.save(reservation);
        }
    }

    @Override
    public void addReservation(Reservation reservation){
        reservationRepository.save(reservation);
    }

}
