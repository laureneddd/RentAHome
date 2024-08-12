package com.rentahome.service.implement;

import com.rentahome.dto.UserDTO;
import com.rentahome.entity.User;
import com.rentahome.repository.UserRepository;
import com.rentahome.service.Converter;
import com.rentahome.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    Converter converter;

    @Override
    public void addUser(UserDTO userDTO) {
        User user = converter.convertToEntity(userDTO);
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Integer userId){
        userRepository.deleteByUserId(userId);
    }

    @Override
    public void updateUser(UserDTO userDTO){
        User user = converter.convertToEntity(userDTO);
        userRepository.save(user);
    }
}
