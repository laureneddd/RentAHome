package com.rentahome.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rentahome.entity.Message;


@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

    public Message findByMessageId(int messageId);

}
