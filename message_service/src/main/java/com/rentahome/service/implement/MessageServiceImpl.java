package com.rentahome.service.implement;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentahome.dto.MessageDTO;
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

    @Override
    public MessageDTO findMessageById(int messageId) {
        return null;
    }

    private MessageDTO convertToDTO(Message message) {
        MessageDTO dto = new MessageDTO();
        dto.setMessageId(message.getMessageId());
        dto.setSenderId(message.getSenderId());
        dto.setReceiverId(message.getReceiverId());
        dto.setContent(message.getContent());
        dto.setTime(message.getTime());

        // Uncomment and implement this if needed for replies
        // List<ReplyDTO> replyDTOs = message.getReplies().stream()
        //         .map(reply -> {
        //             ReplyDTO replyDTO = new ReplyDTO();
        //             replyDTO.setReplyId(reply.getReplyId());
        //             replyDTO.setContent(reply.getContent());
        //             replyDTO.setTime(reply.getTime());
        //             return replyDTO;
        //         })
        //         .collect(Collectors.toList());
        // dto.setReplies(replyDTOs);

        return dto;
    }

    public List<Message> findMessagesWithPartner(String userId, String partnerId) {
        List<Message> sentMessages = messageRepository.findBySenderIdAndReceiverId(userId, partnerId);
        List<Message> receivedMessages = messageRepository.findByReceiverIdAndSenderId(userId, partnerId);

        List<Message> allMessages = new ArrayList<>();
        allMessages.addAll(sentMessages);
        allMessages.addAll(receivedMessages);

        allMessages.sort(Comparator.comparing(Message::getTime, Comparator.nullsLast(Comparator.naturalOrder())));

        return allMessages;
    }

    public List<Message> selectAll() {
        List<Message> sentMessages = messageRepository.findAll();
        List<Message> allMessages = new ArrayList<>();
        allMessages.addAll(sentMessages);

        allMessages.sort(Comparator.comparing(Message::getTime, Comparator.nullsLast(Comparator.naturalOrder())));

        return allMessages;
    }
}
