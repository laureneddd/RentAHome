package com.rentahome.service.implement;

import java.util.List;
import java.util.stream.Collectors;

import com.rentahome.dto.MessageDTO;
import com.rentahome.dto.ReplyDTO;
import com.rentahome.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentahome.entity.Reply;
import com.rentahome.repository.ReplyRepository;
import com.rentahome.service.ReplyService;

@Service
public class ReplyServiceImpl implements ReplyService{

//    @Autowired
//    ReplyRepository replyRepository;
//
//    @Override
//    public ReplyDTO addReply(ReplyDTO dto) {
//        Reply reply = new Reply();
//
//        reply.setReplyId(dto.getReplyId());
//        reply.setContent(dto.getContent());
//        reply.setMessage(dto.getMessage());
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
//
//    private ReplyDTO convertToReplyDTO(Reply reply) {
//        ReplyDTO replyDTO = new ReplyDTO();
//        replyDTO.setReplyId(reply.getReplyId());
//        replyDTO.setTime(reply.getTime());
//        replyDTO.setContent(reply.getContent());
//        replyDTO.setMessage(this.convertToDTO(reply.getMessage()));
//        return replyDTO;
//    }
//
//    private MessageDTO convertToDTO(Message message) {
//        MessageDTO dto = new MessageDTO();
//        dto.setMessageId(message.getMessageId());
//        dto.setSenderId(message.getSenderId());
//        dto.setReceiverId(message.getReceiverId());
//        dto.setContent(message.getContent());
//        dto.setTime(message.getTime());
//
//        List<ReplyDTO> replyDTOs = message.getReplies().stream()
//                .map(reply -> {
//                    ReplyDTO replyDTO = new ReplyDTO();
//                    replyDTO.setReplyId(reply.getReplyId());
//                    replyDTO.setContent(reply.getContent());
//                    replyDTO.setTime(reply.getTime());
//                    return replyDTO;
//                })
//                .collect(Collectors.toList());
//        dto.setReplies(replyDTOs);
//
//        return dto;
//    }
}