package com.rentahome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.rentahome.entity.Message;
import com.rentahome.service.MessageService;
import com.rentahome.service.ReplyService;



@Controller
@RequestMapping("/message/")
public class MessageController {
    
    @Autowired
    MessageService messageService;

    @Autowired 
    ReplyService replyService;

    @GetMapping("/send")
    public ModelAndView showSendMessageForm() {
        return new ModelAndView("add_message");
    }

    @PostMapping("/addmessage")
    public ModelAndView addMessage(@ModelAttribute Message message) {
        messageService.addMessage(message);
        ModelAndView modelAndView = new ModelAndView("add_message");
        modelAndView.addObject("messageSuccess", "Message added successfully!");
        return modelAndView;
    }

    // @GetMapping("/user_messages")
    // public ModelAndView getUserMessages(@RequestParam String userId) {
    //     ModelAndView modelAndView = new ModelAndView("message_page");
    //     // modelAndView.addObject("messages", messageService.getMessagesByUserId(userId));
    //     return modelAndView;
    // }
    
}