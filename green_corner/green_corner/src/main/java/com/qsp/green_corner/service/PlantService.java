package com.qsp.green_corner.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.qsp.green_corner.entity.Plant;
import com.qsp.green_corner.entity.Reviews;
import com.qsp.green_corner.repository.PlantRepository;

@Service
public class PlantService {
	
	@Autowired
	private PlantRepository plantRepository;

	public PlantService(PlantRepository plantRepository) {
		super();
		this.plantRepository = plantRepository;
	}
	
	public Plant savePlant(Plant plant) {
		Plant save = plantRepository.save(plant);
		return save;
	}
	
	public List<Plant> findAll(){
		return plantRepository.findAll();
	}
	
	public String giveReviewToPlant(Reviews reviews , long id) {
		Optional<Plant> plant = plantRepository.findById(id);
		if(plant.isPresent()) {
			Plant plant2 = plant.get();  //fetching Plant object
			List<Reviews> reviews2 = plant2.getReviews();
			reviews2.add(reviews); //add new Review using add method of list
			plantRepository.save(plant2); //Again Save the Plant2 object with new Review
			return "PlantReviews Set successfully";
		}
		return "PlantReviews Set Unsuccessfull";
	}
	
	public List<Plant> findPlantByRating(double rating) {
		List<Plant> plantByRating = plantRepository.findPlantByRating(rating);
		
		return plantByRating;
		
	}
	
	public List<Plant> findPlantByRatingBetween(double min){
		double max = 0;
		if(min>=1.0 && min<2.0) {
			max = 1.9;
		}
		else if(min>=2.0 && min<3.0){
			max = 2.9;
		}
		else if(min>=3.0 && min<4.0){
			max = 3.9;
		}
		else if(min>=4.0 && min<5.0) {
			max = 4.9;
		}
		else {
			max = 5.0;
		}
		
		return plantRepository.findPlantByRatingBetween(min,max);
	}
	
	public List<Plant> findPlantByCategories(String categorie) {
		List<Plant> allPlants = plantRepository.findAll();
		List<Plant> newPlant = new ArrayList<>();
		
		for(Plant plants : allPlants) {
			List<String> categories = plants.getCategories();
			if(categories.contains(categorie)) {
				newPlant.add(plants);
			}
		}
//		System.out.println(newPlant);
//		System.out.println(allPlants);
		return newPlant;
	}
	
	public List<Plant> sortPlantInAscAccToPrice(){
		List<Plant> allPalntByPrice = plantRepository.findAllPlantByPriceAsc();
		return allPalntByPrice;
	}
	
	public List<Plant> sortPlantInDescAccToPrice(){
		List<Plant> allPlantByPriceDesc = plantRepository.findAllPlantByPriceDesc();
		return allPlantByPriceDesc;
	}
	

	public Page <Plant> getPlants(int page , int size){
		Pageable pageable = PageRequest.of(page, size);
		return plantRepository.findAll(pageable);
	
	}
	}
