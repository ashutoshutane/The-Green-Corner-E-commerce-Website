package com.qsp.green_corner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qsp.green_corner.entity.ContactUs;
import com.qsp.green_corner.repository.ContactusRepository;

@Service
public class ContactusService {
	
	@Autowired 	
	private ContactusRepository contactusRepository;

	public ContactusService(ContactusRepository contactusRepository) {
		super();
		this.contactusRepository = contactusRepository;
	}
	
	public ContactUs saveMessage(ContactUs contactUs) {
		ContactUs save = contactusRepository.save(contactUs);
		return save;
	}
}
