package com.springboot.demo.email.service;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service("emailService")
public class EmailService {
	@Autowired
	private JavaMailSender mailSender;

	public void sendMailWithAttachment(String to, String subject, String body, String fileToAttach)
			throws MessagingException {

		MimeMessage mimeMessage = mailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.addCc(new InternetAddress(to));
		helper.setText(body);
		helper.setSubject(subject);

		FileSystemResource file = new FileSystemResource(new File(fileToAttach));
		helper.addAttachment(file.getFilename(), file);

		try {
			mailSender.send(mimeMessage);
		} catch (MailException ex) {
			System.err.println(ex.getMessage());
		}
	}
}
