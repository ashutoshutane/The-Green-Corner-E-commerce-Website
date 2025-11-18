package com.qsp.green_corner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qsp.green_corner.entity.DeliveryEntity;
import com.qsp.green_corner.service.DeliveryService;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {
	
	@Autowired
	private DeliveryService deliveryService;

	public DeliveryController(DeliveryService deliveryService) {
		super();
		this.deliveryService = deliveryService;
	}
	
	@PostMapping("/save")
	public ResponseEntity<DeliveryEntity> save(@RequestBody DeliveryEntity deliveryEntity){
		DeliveryEntity saveDelivery = deliveryService.saveDelivery(deliveryEntity);
		return ResponseEntity.status(HttpStatus.OK).body(saveDelivery);
	}
	
	
}
