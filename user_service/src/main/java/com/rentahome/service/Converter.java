package com.rentahome.service;

import com.rentahome.dto.UserDTO;
import com.rentahome.entity.User;
import org.springframework.stereotype.Component;

@Component
public class Converter {

    public User coverToEntity(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setRole(userDTO.getRole());
        user.setUserId(userDTO.getUserId());
        return user;
    }

    public UserDTO coverToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setPassword(user.getPassword());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole());
        userDTO.setUserId(user.getUserId());
        return userDTO;
    }
}
