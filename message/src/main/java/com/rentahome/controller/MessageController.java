package com.rentahome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("addmessage")
    public String showMessagePage() {
        return "message"; 
    }

    @PostMapping("addmessage")
    public ModelAndView addMessage(Message message) {
        messageService.addMessage(message);
        ModelAndView modelAndView = new ModelAndView("message");
        // modelAndView.addObject("messageSuccess", "Message added successfully!");
        return modelAndView;
    }

    @GetMapping("reservation")
    public ModelAndView showPropertyPage() {
        ModelAndView modelAndView = new ModelAndView("owner_reservation");
        // modelAndView.addObject("attributeName", attributeValue);
        return modelAndView;
    }

    @GetMapping("/confirmation")
    public ModelAndView showConfirmationPage() {
        ModelAndView modelAndView = new ModelAndView("owner_confirmation");
        // modelAndView.addObject("attributeName", attributeValue);
    }
    
    @GetMapping("/addmessage")
    public ModelAndView showAddMessagePage() {
        return new ModelAndView("message");
    }

    @PostMapping("/addmessage")
    public ModelAndView addMessage(Message message) {
        messageService.addMessage(message);
        ModelAndView modelAndView = new ModelAndView("message");
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
