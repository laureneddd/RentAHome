package com.rentahome.service;

import com.rentahome.dto.UserDTO;
import org.springframework.stereotype.Service;


public interface UserService {
    void addUser(UserDTO userDTO);
    void updateUser(UserDTO userDTO);
    void deleteUser(Integer userId);
}
