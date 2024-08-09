package com.rentahome.service;

import com.rentahome.dto.UserDTO;
import com.rentahome.entity.User;

public interface UserService {
    String addUser(UserDTO user);
    String addUser(User user);
    void deleteUser(int userId);
    UserDTO login(String name, String password);
    UserDTO findByUserId(int userId);
    String updateUser(String name, String password, String email, String role);
}
