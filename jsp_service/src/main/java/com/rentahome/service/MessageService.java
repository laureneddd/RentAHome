package com.rentahome.service;

import com.rentahome.dto.MessageDTO;

import java.util.List;

public interface MessageService{


    public MessageDTO addMessage(MessageDTO message);

    public List<MessageDTO> findAllMessages();


}