package com.rentahome;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rentahome.service.MessageService;

@SpringBootApplication
public class MessageApplication {

	@Autowired
	MessageService messageService;

	public static void main(String[] args) {
		SpringApplication.run(MessageApplication.class, args);
	}

}
