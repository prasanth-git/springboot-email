package com.springboot.demo.email.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.demo.email.model.Email;
import com.springboot.demo.email.service.EmailService;

@RestController
public class EmailController {
   
	@Autowired
    private EmailService emailService;
	
	@PostMapping("/sendMail")
	public void sendMail(@RequestBody Email email) {
		try {
			emailService.sendMailWithAttachment(email.getTo(), email.getSubject(), email.getBody(), email.getFileToAttach());
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		 
	}
}
