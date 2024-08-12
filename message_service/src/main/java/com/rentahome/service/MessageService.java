package com.rentahome.service;

import java.util.List;

import com.rentahome.dto.MessageDTO;
import com.rentahome.entity.Message;

public interface MessageService{


    public MessageDTO addMessage(Message message);

    public List<MessageDTO> findAllMessages();


}