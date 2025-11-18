package com.qsp.green_corner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;


import io.micrometer.observation.transport.SenderContext;

@Service
public class MailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public void sendMail(String toEmail , String subject , String body) {
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setTo(toEmail);
		
		message.setSubject(subject);
		
		message.setText(body);
		
		message.setFrom("ashutoshutane453@gmail.com");
		
		javaMailSender.send(message);
		
		System.out.println("Mail Sent Successfully");
		
		
		
		
	}

}
