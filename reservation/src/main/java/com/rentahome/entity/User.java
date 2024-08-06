package com.rentahome.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class User {
    @Id
    private int userId;
    private String username;
    private String password;
    private String email;
    private String role;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Property> properties;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Reservation> reservations;

}
