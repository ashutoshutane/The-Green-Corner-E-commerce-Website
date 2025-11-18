package com.qsp.green_corner.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qsp.green_corner.entity.Equipment;
import com.qsp.green_corner.entity.Reviews;
import com.qsp.green_corner.repository.EquipmentRepository;

@Service
public class EquipmentService {
	
	@Autowired
	private EquipmentRepository equipmentRepository;

	public EquipmentService(EquipmentRepository equipmentRepository) {
		super();
		this.equipmentRepository = equipmentRepository;
	}
	
	public Equipment saveEquipment(Equipment equipment) {
		Equipment save = equipmentRepository.save(equipment);
		System.out.println(save);
		return save;
	}
	
	public String giveReveiwToEquipment(Reviews reviews , long id) {
		Optional<Equipment> equipment = equipmentRepository.findById(id);
		if(equipment.isPresent()) {
			Equipment equipment2 = equipment.get();
			List<Reviews> reviews2 = equipment2.getReviews();
			reviews2.add(reviews);
			equipmentRepository.save(equipment2);
			return "Reveiw Set Successfully";
		}
		return "Reveiw Set Unsuccessfull";
	}
	
	
	

}
