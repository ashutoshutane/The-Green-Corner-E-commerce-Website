package com.qsp.green_corner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qsp.green_corner.entity.Equipment;
import com.qsp.green_corner.entity.Reviews;
import com.qsp.green_corner.repository.EquipmentRepository;
import com.qsp.green_corner.service.EquipmentService;

@RestController
@RequestMapping("/equipment")
public class EquipmentController {
	
	@Autowired
	private EquipmentService equipmentService;

	public EquipmentController(EquipmentService equipmentService) {
		super();
		this.equipmentService = equipmentService;
	}
	
	@PostMapping("/save")
	public ResponseEntity<Equipment>  saveEquipment(@RequestBody Equipment equipment) {
		Equipment saveEquipment = equipmentService.saveEquipment(equipment);
//		System.out.println(saveEquipment);
		return ResponseEntity.status(HttpStatus.OK).body(saveEquipment);
	}

	@PutMapping("/{id}/reviews")
	public ResponseEntity<String> giveReveiwToEquipment(@RequestBody Reviews reviews , @PathVariable Long id){
		String giveReveiwToEquipment = equipmentService.giveReveiwToEquipment(reviews, id);
		return ResponseEntity.status(HttpStatus.OK).body(giveReveiwToEquipment);
	}
	

}
