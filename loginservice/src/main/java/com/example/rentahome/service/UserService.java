package com.example.rentahome.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rentahome.entity.User;
import com.example.rentahome.repository.UserRepository;

@Service
public class UserService {
    @Autowired
	UserRepository userRepository;

	public String addUser(User user) {
		
		userRepository.save(user);
		
		return "User Saved";
	}

	public String deleteUser(int userId) {
		User user = userRepository.findByUserId(userId);

		userRepository.delete(user);
		
		return "User Deleted";
	}

	public String updateUser(User user) {
		
		userRepository.saveAndFlush(user);
		
		return "User Updated";
	}

	
	public User login(String name, String password) {
		
		return userRepository.findByNameAndPassword(name, password);
	}

	public User findByUserId(int userId){
		User user = userRepository.findByUserId(userId);
		return user;
	}

}
