package com.qsp.green_corner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qsp.green_corner.service.UpiRequestService;


@RestController
@RequestMapping("/Upi")
public class UpiRequestController {
	
	@Autowired
	private UpiRequestService upiRequestService;

	public UpiRequestController(UpiRequestService upiRequestService) {
		super();
		this.upiRequestService = upiRequestService;
	}
	
	@GetMapping("/upilink/{UpiId}/{name}/{amount}")
	public ResponseEntity<String> generateUpiLink(@PathVariable String UpiId , @PathVariable String name, @PathVariable double amount){
		String upiLink = upiRequestService.generateUpiLink(UpiId, name, amount);
		return ResponseEntity.status(HttpStatus.OK).body(upiLink);
	}
}
