package com.rentahome.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Integer userId;
    private String name;
    private String password;
    private String email;
    private String role;
    private List<PropertyDTO> properties = new ArrayList<>();
    private List<ReservationDTO> reservations = new ArrayList<>();
    private List<OwnerMailDTO> ownerMails = new ArrayList<>();

}