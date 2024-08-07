package com.example.rentahome.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rentahome.entity.User;



public interface  UserRepository  extends JpaRepository<User, Integer>{
    
    public User findByNameAndPassword(String name, String password);

    public User findByUserId(int userId);

}
