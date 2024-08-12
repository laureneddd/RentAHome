package com.rentahome.service.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentahome.dto.MessageDTO;
import com.rentahome.dto.ReplyDTO;
import com.rentahome.entity.Message;
import com.rentahome.repository.MessageRepository;
import com.rentahome.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    MessageRepository messageRepository;

    @Override
    public MessageDTO addMessage(Message message) {
        Message savedMessage = messageRepository.save(message);
        return convertToDTO(savedMessage);
    }

    @Override
    public List<MessageDTO> findAllMessages() {
        List<Message> messages = messageRepository.findAll();
        return messages.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private MessageDTO convertToDTO(Message message) {
        MessageDTO dto = new MessageDTO();
        dto.setMessageId(message.getMessageId());
        dto.setSenderId(message.getSenderId());
        dto.setReceiverId(message.getReceiverId());
        dto.setContent(message.getContent());
        dto.setTime(message.getTime());

        List<ReplyDTO> replyDTOs = message.getReplies().stream()
                .map(reply -> {
                    ReplyDTO replyDTO = new ReplyDTO();
                    replyDTO.setReplyId(reply.getReplyId());
                    replyDTO.setContent(reply.getContent());
                    replyDTO.setTime(reply.getTime());
                    return replyDTO;
                })
                .collect(Collectors.toList());
        dto.setReplies(replyDTOs);

        return dto;
    }
}