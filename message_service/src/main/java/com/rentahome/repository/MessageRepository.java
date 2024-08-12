package com.rentahome.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rentahome.dto.MessageDTO;
import com.rentahome.entity.Message;


@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

    public MessageDTO findByMessageId(int messageId);

    public List<Message> findBySenderIdAndReceiverId(String senderId, String receiverId);

    public List<Message> findByReceiverIdAndSenderId(String receiverId, String senderId);

}
