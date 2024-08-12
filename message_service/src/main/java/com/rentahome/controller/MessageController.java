package com.rentahome.controller;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.rentahome.dto.ReplyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.rentahome.dto.MessageDTO;
import com.rentahome.entity.Message;
import com.rentahome.service.MessageService;
import com.rentahome.service.ReplyService;

import jakarta.servlet.http.HttpSession;



@Controller
@RequestMapping("/message")
public class MessageController {

    @Autowired
    MessageService messageService;

    @Autowired
    ReplyService replyService;

    @PostMapping("/addmessage")
    public void addMessage(@RequestBody MessageDTO messageDTO) {

        Message message = new Message();
        message.setContent(messageDTO.getContent());
        message.setSenderId(messageDTO.getSenderId());
        message.setReceiverId(messageDTO.getReceiverId());
        message.setTime(new Date());
        messageService.addMessage(message);
    }

    @GetMapping("/messages")
    public ResponseEntity<List<MessageDTO>> showMessages() {
        List<MessageDTO> messages = messageService.findAllMessages();
        return ResponseEntity.ok(messages);
    }

//    @GetMapping("/allReply")
//    public ResponseEntity<List<ReplyDTO>> showAllReply() {
////        List<ReplyDTO> replyDTOS = replyService.findAllReplies();
////        return ResponseEntity.ok(replyDTOS);
//    }

}