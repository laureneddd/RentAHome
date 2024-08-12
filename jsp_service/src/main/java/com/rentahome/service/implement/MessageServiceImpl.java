package com.rentahome.service.implement;

import com.rentahome.dto.MessageDTO;
import com.rentahome.dto.PropertyDTO;
import com.rentahome.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    private RestTemplate restTemplate;

    private static final String MESSAGE_SERVICE = "http://localhost:8082/message";

    @Override
    public MessageDTO addMessage(MessageDTO message) {
        return restTemplate.postForObject(MESSAGE_SERVICE+"/addMessage", message, MessageDTO.class);
    }

    @Override
    public List<MessageDTO> findAllMessages() {
        ResponseEntity<List<MessageDTO>> rateResponse =
                restTemplate.exchange(MESSAGE_SERVICE+"/messages",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<MessageDTO>>() {
                        });
        List<MessageDTO> messageDTOS = rateResponse.getBody();
        if (messageDTOS == null) {
            messageDTOS= new ArrayList<>();
        }
        return messageDTOS;
    }

}