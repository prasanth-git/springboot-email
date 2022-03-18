package com.springboot.demo.email.service;

import java.io.File;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

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
		MimeMultipart multipart = new MimeMultipart();

		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.addCc(new InternetAddress(to));
		helper.setText(body);
		helper.setSubject(subject);

		MimeBodyPart msg = new MimeBodyPart();
		DataSource ds = new FileDataSource(fileToAttach);
		msg.setDataHandler(new DataHandler(ds));
		msg.setFileName(ds.getName());
		multipart.addBodyPart(msg);
		
		mimeMessage.setContent(multipart);
		//FileSystemResource file = new FileSystemResource(new File(fileToAttach));
		//helper.addAttachment(file.getFilename(), file);

		try {
			mailSender.send(mimeMessage);
		} catch (MailException ex) {
			System.err.println(ex.getMessage());
		}
	}
}
