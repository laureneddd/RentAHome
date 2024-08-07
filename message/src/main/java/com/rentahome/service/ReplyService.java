package com.rentahome.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentahome.entity.Reply;
import com.rentahome.repository.ReplyRepository;

@Service
public class ReplyService {

    @Autowired
    ReplyRepository replyRepository;

    public Reply addReply(Reply reply) {

        return replyRepository.save(reply);

    }

    public List<Reply> findAllReplies() {

        return replyRepository.findAll();
        
    }

    public List<Reply> findByMessageId(int messageId) {

        return replyRepository.findByMessageMessageId(messageId);

    }
    
}
