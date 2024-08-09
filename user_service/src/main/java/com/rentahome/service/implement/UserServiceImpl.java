package com.rentahome.service.implement;

import java.util.Optional;

import com.rentahome.dto.UserDTO;
import com.rentahome.service.Converter;
import com.rentahome.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentahome.entity.User;
import com.rentahome.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
	UserRepository userRepository;

	@Autowired
	Converter converter;

	@Override
	public void addUser(UserDTO userDTO) {
		userRepository.save(converter.coverToEntity(userDTO));
	}
	@Override
	public void deleteUser(int userId) {
		User user = userRepository.findByUserId(userId);
		userRepository.delete(user);
	}

	@Override
	public void updateUser(int userId, UserDTO userDTO) {
	
		Optional<User> existingUserOpt = Optional.ofNullable(userRepository.findByUserId(userId));
    
    // If the user is not found, return a "User not found" message
    if (existingUserOpt.isPresent()) {

		User existingUser = existingUserOpt.get();
		existingUser.setName(userDTO.getName());
		existingUser.setPassword(userDTO.getPassword());
		existingUser.setEmail(userDTO.getEmail());
		existingUser.setRole(userDTO.getRole());

		userRepository.saveAndFlush(existingUser);
		//entityManager.clear(); // Clear the persistence context
	}
		
	}

	@Override
	public UserDTO login(String name, String password) {
		Optional<User> existingUserOpt = Optional.ofNullable(userRepository.findByNameAndPassword(name, password));
		if (!existingUserOpt.isPresent()) {
			return null;
		}
		User user = existingUserOpt.get();
		UserDTO dto = converter.coverToDTO(user);
		return dto;
	}

	@Override
	public UserDTO getUser(int userId){
		Optional<User> UserOpt = Optional.ofNullable(userRepository.findByUserId(userId));
		if (!UserOpt.isPresent()) {
			return null;
		}
		User user = UserOpt.get();
		return converter.coverToDTO(user);
	}

}
