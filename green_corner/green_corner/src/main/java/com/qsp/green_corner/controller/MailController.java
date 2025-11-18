package com.qsp.green_corner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qsp.green_corner.entity.ContactUs;
import com.qsp.green_corner.service.ContactusService;
import com.qsp.green_corner.service.MailService;

@RestController
@RequestMapping("/SendMail")
public class MailController {
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private ContactusService contactusService;
	
	public MailController(MailService mailService, ContactusService contactusService) {
		super();
		this.mailService = mailService;
		this.contactusService = contactusService;
	}

	@PostMapping("/send")
	public ResponseEntity<String> contact(@RequestBody ContactUs contactUs){
		contactusService.saveMessage(contactUs);
		mailService.sendMail(contactUs.getEmail(),contactUs.getSubject(), contactUs.getMessage());
		return ResponseEntity.status(HttpStatus.OK).body("Message Sent Successfully");
	}
	

}
