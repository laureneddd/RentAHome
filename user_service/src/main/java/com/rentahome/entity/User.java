package com.rentahome.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name = "users")
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int userId;
	@Column(nullable = false, length = 30)
	private String name;
	@Column(nullable = false, length = 30)
	private String password;
	private String email;
    private String role;
    
}
