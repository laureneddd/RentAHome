package com.rentahome.service.implement;

import com.rentahome.dto.ReplyDTO;
import com.rentahome.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService{

//    @Autowired
//    private RestTemplate restTemplate;
//    private static final String MESSAGE_SERVICE = "http://localhost:8082/message";
//    @Override
//    public ReplyDTO addReply(ReplyDTO reply) {
//
//        return replyRepository.save(reply);
//
//    }
//
//    @Override
//    public List<ReplyDTO> findAllReplies() {
//
//        return replyRepository.findAll();
//
//    }
//
//    @Override
//    public List<ReplyDTO> findByMessageId(int messageId) {
//
//        return replyRepository.findByMessageMessageId(messageId);
//
//    }
}