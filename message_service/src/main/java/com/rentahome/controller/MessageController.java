package com.rentahome.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.rentahome.dto.MessageDTO;
import com.rentahome.entity.Message;
import com.rentahome.service.implement.MessageServiceImpl;

import jakarta.servlet.http.HttpSession;



@Controller
@RequestMapping("/message/")
public class MessageController {

    @Autowired
    MessageServiceImpl messageServiceImpl;

    // @Autowired
    // ReplyServiceImpl replyServiceImpl;

    @GetMapping("addmessage")
    public String showMessagePage() {
        return "chatbox";
    }


    @PostMapping("send")
public String addMessage(@RequestParam("senderId") String senderId,
                         @RequestParam("receiverId") String receiverId,
                         @RequestParam("content") String content) {

    Message newMessage = new Message();
    newMessage.setSenderId(senderId);
    newMessage.setReceiverId(receiverId);
    newMessage.setContent(content.trim());
    newMessage.setTime(new Date());

    messageServiceImpl.addMessage(newMessage);

    return "chatbox";
}


    @PostMapping("/sendmessage")
    public ModelAndView addMessage(@ModelAttribute Message message) {
        message.setTime(new Date());

        messageServiceImpl.addMessage(message);

        List<Message> messages = messageServiceImpl.selectAll();
        System.out.println(messages);

        ModelAndView modelAndView = new ModelAndView("chatbox");
        modelAndView.addObject("messages", messages);
        return modelAndView;
    }


    @GetMapping("/messages")
    public ModelAndView showMessages(HttpSession session) {
        String userId = (String) session.getAttribute("userId");

        List<Message> messages = messageServiceImpl.selectAll();

        System.out.println(messages);
        ModelAndView modelAndView = new ModelAndView("messages");
        modelAndView.addObject("messages", messages);

        return modelAndView;
    }

    @GetMapping("/chat")
    public ModelAndView viewChat(@PathVariable("partnerId") String partnerId) {
    String userId = "UserId";
    List<Message> chatMessages = messageServiceImpl.findMessagesWithPartner(userId, partnerId);

    ModelAndView modelAndView = new ModelAndView("chatbox");
    modelAndView.addObject("messages", chatMessages);
    return modelAndView;
}

// @GetMapping("/chatbox")
// public String showChatbox(Model model) {

//     List<MessageDTO> messages = messageServiceImpl.findAllMessages();
//     ((Object) model).addAttribute("messages", messages);
//     return "chatbox";  
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
