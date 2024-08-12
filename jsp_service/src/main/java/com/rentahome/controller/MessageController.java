package com.rentahome.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.rentahome.dto.MessageDTO;
import com.rentahome.service.MessageService;
import com.rentahome.service.implement.ReplyServiceImpl;

import jakarta.servlet.http.HttpSession;



@Controller
@RequestMapping("/message/")
public class MessageController {

    @Autowired
    MessageService messageService;

    @Autowired
    ReplyServiceImpl replyServiceImpl;

    @GetMapping("addmessage")
    public String showMessagePage() {
        return "chatbox";
    }

    @PostMapping("addmessage")
    public ModelAndView addMessage(@RequestBody String messageContent, String senderId, String receiverId) {
        System.out.println(messageContent);
        MessageDTO message = new MessageDTO();
        message.setContent(messageContent);
        message.setSenderId(senderId);
        message.setReceiverId(receiverId);
        message.setTime(new Date());

        messageService.addMessage(message);

        return new ModelAndView("chatbox");
    }

    @GetMapping("/messages")
    public ModelAndView showMessages(HttpSession session) {
        String userId = (String) session.getAttribute("userId");

        List<MessageDTO> messages = messageService.findAllMessages();

        ModelAndView modelAndView = new ModelAndView("message");
        modelAndView.addObject("messages", messages);

        return modelAndView;
    }

//     @GetMapping("/chat")
//     public ModelAndView viewChat(@RequestParam("partnerId") String partnerId) {
//     String userId = "UserId";
//     List<Message> chatMessages = messageServiceImpl.findMessagesWithPartner(userId, partnerId);

//     ModelAndView modelAndView = new ModelAndView("chatbox");
//     modelAndView.addObject("messages", chatMessages);
//     return modelAndView;
// }

    // @PostMapping("addmessage")
    // public ModelAndView addMessage(Message message) {
    //     messageServiceImpl.addMessage(message);
    //     ModelAndView modelAndView = new ModelAndView("chatbox");
    //     modelAndView.addObject("messageSuccess", "Message added successfully!");
    //     return modelAndView;
    // }

    // @GetMapping("reservation")
    // public ModelAndView showPropertyPage() {
    //     ModelAndView modelAndView = new ModelAndView("owner_reservation");
    //     // modelAndView.addObject("reserve your trip", "reserved successfully");
    //     return modelAndView;
    // }

    // @GetMapping("confirmation")
    // public ModelAndView showConfirmationPage() {
    //     ModelAndView modelAndView = new ModelAndView("owner_confirmation");
    //     return modelAndView;
    // }
}