package com.qsp.green_corner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qsp.green_corner.entity.DeliveryEntity;
import com.qsp.green_corner.repository.DeliveryRepository;

@Service
public class DeliveryService {
	
	@Autowired
	private DeliveryRepository deliveryRepository;

	public DeliveryService(DeliveryRepository deliveryRepository) {
		super();
		this.deliveryRepository = deliveryRepository;
	}
	
	public DeliveryEntity saveDelivery(DeliveryEntity deliveryEntity) {
		DeliveryEntity save = deliveryRepository.save(deliveryEntity);
		return save;
	}
	
	
}
