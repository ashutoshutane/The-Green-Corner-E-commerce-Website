package com.qsp.green_corner.service;

import org.springframework.stereotype.Service;

@Service
public class UpiRequestService {
	
	public String generateUpiLink(String upiId, String name, double amount) {
		return "upi://pay?pa="+upiId+"&pn="+name.replace(" ","%20")+"&am="+amount+"&cu=INR";
	}
	
}
