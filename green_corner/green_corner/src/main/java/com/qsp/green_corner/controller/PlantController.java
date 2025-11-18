package com.qsp.green_corner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qsp.green_corner.entity.Plant;
import com.qsp.green_corner.entity.Reviews;
import com.qsp.green_corner.service.PlantService;

@RestController
@RequestMapping("/plant")
public class PlantController {
	
	
	@Autowired
	private PlantService plantService;
	
	
	public PlantController(PlantService plantService) {
		super();
		this.plantService = plantService;
	}

	@PostMapping("/save")
	public ResponseEntity<Plant> savePlant(@RequestBody Plant plant){
		Plant savePlant = plantService.savePlant(plant);
		return ResponseEntity.status(HttpStatus.OK).body(plant);
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Plant>> findAll(@RequestBody Plant plant){
		List<Plant> allPlant = plantService.findAll();
		return ResponseEntity.status(HttpStatus.FOUND).body(allPlant);
	}
	
	@PutMapping("/{id}/reviews")
	public ResponseEntity<String> giveReveiwToPlant(@RequestBody Reviews reviews , @PathVariable long id){
		String giveReviewToPlant = plantService.giveReviewToPlant(reviews, id);
		return ResponseEntity.status(HttpStatus.OK).body(giveReviewToPlant);
	}
	
	@GetMapping("/ratings")
	public ResponseEntity<List<Plant>> findPlantByRating(@RequestParam double rating){
		List<Plant> plantByRating = plantService.findPlantByRating(rating);
		return ResponseEntity.status(HttpStatus.OK).body(plantByRating);
	}
	
	@GetMapping("/ratingBetween")
	public ResponseEntity<List<Plant>> findPlantByRatingBetween(@RequestParam double min){
		List<Plant> plantByRatingBetween = plantService.findPlantByRatingBetween(min);
		
		return ResponseEntity.status(HttpStatus.OK).body(plantByRatingBetween);
	}
	
	@GetMapping("/findByCategorie")
	public ResponseEntity<List<Plant>> findPlantByCategories(@RequestParam String categorie){
		List<Plant> plantByCategories = plantService.findPlantByCategories(categorie);
		return ResponseEntity.status(HttpStatus.OK).body(plantByCategories);
	}
	
	@GetMapping("/sortAccordingToPriceInAsc")
	public ResponseEntity<List<Plant>> sortPlantInAscAccToPrice(){
		List<Plant> sortPlantInAscAccToPrice = plantService.sortPlantInAscAccToPrice();
		return ResponseEntity.status(HttpStatus.OK).body(sortPlantInAscAccToPrice);
	}
	
	@GetMapping("/sortAccordingToP riceInDesc")
	public ResponseEntity<List<Plant>> sortPlantInDescAccToPrice(){
		List<Plant> sortPlantInDescAccToPrice = plantService.sortPlantInDescAccToPrice();
		return ResponseEntity.status(HttpStatus.OK).body(sortPlantInDescAccToPrice);
	}
	
	@GetMapping("/getPlants")
	public ResponseEntity<Page<Plant>> getPlants(@RequestParam int page, @RequestParam int size){
		Page<Plant> plants = plantService.getPlants(page, size);
		return ResponseEntity.status(HttpStatus.OK).body(plants);
	}

}
