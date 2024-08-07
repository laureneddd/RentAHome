package com.rentahome.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentahome.entity.Message;
import com.rentahome.repository.MessageRepository;

@Service
public class MessageService{

    @Autowired
    MessageRepository messageRepository;

    public Message addMessage(Message message) {

        return messageRepository.save(message);
        
    }

    public List<Message> findAllMessages() {

        return messageRepository.findAll();

    }

    public Message findMessageById(int messageId) {

        return messageRepository.findByMessageId(messageId);

    }

}
