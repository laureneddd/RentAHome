package com.rentahome.service.implement;

import com.rentahome.entity.User;
import com.rentahome.service.UserService;
import org.springframework.stereotype.Service;


import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;

import com.rentahome.dto.UserDTO;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    RestTemplate restTemplate;
    private static final String USER_SERVICE_URL = "http://localhost:8083/user";
    @Override
    public String addUser(UserDTO user) {
        restTemplate.postForObject(USER_SERVICE_URL + "/addUser", user, UserDTO.class);
        return "User Saved";
    }
    @Override
    public String addUser(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setPassword(user.getPassword());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole());
        userDTO.setUserId(user.getUserId());
        restTemplate.postForObject(USER_SERVICE_URL + "/addUser",userDTO, UserDTO.class);
        return "User Saved";
    }
    @Override
    public void deleteUser(int userId) {
        restTemplate.delete(USER_SERVICE_URL +"/deleteUser/"+ userId);
    }

    @Override
    public String updateUser(String name, String password, String email, String role) {
        URI uri = UriComponentsBuilder.fromHttpUrl(USER_SERVICE_URL+"/login")
                .queryParam("name", name)
                .queryParam("password", password)
                .build()
                .toUri();

        UserDTO existingUser = restTemplate.getForObject(uri, UserDTO.class);

        // If the user is not found, return a "User not found" message
        if (existingUser == null) {
            return "User not found";
        }

        existingUser.setName(name);
        existingUser.setPassword(password);
        existingUser.setEmail(email);
        existingUser.setRole(role);

        restTemplate.put(USER_SERVICE_URL + "/updateUser/" + existingUser.getUserId(), existingUser);;
        //entityManager.clear(); // Clear the persistence context
        return "User Updated";

    }

    @Override
    public UserDTO login(String name, String password) {
        URI uri = UriComponentsBuilder.fromHttpUrl(USER_SERVICE_URL+"/login")
                .queryParam("name", name)
                .queryParam("password", password)
                .build()
                .toUri();
        UserDTO user = restTemplate.getForObject(uri, UserDTO.class);
        return user;
    }

    @Override
    public UserDTO findByUserId(int userId){
        UserDTO user = restTemplate.getForObject(USER_SERVICE_URL + "/getUser/"+userId, UserDTO.class);
        return user;
    }
}
