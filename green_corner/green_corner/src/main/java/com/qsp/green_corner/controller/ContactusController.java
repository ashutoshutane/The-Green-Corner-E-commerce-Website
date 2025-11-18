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

@RestController
@RequestMapping("/contact")
public class ContactusController {
	
	@Autowired
	private ContactusService contactusService;

	public ContactusController(ContactusService contactusService) {
		super();
		this.contactusService = contactusService;
	}
	
	@PostMapping("/send")
	public ResponseEntity<ContactUs> sendContact(@RequestBody ContactUs contactUs){
		ContactUs saveMessage = contactusService.saveMessage(contactUs);
		return ResponseEntity.status(HttpStatus.CREATED).body(saveMessage);
	}
	

}
