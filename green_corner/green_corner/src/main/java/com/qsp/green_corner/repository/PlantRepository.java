package com.qsp.green_corner.repository;

import java.lang.annotation.Native;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qsp.green_corner.entity.Plant;


public interface PlantRepository extends JpaRepository<Plant,Long> {
	List<Plant> findPlantByRating(double rating);
	
	List<Plant> findPlantByRatingBetween(double min,double max);
	
	@Query("Select p from Plant p order by p.price ASC" )//JPQL
	List<Plant> findAllPlantByPriceAsc();
	
	@Query("Select p from Plant p order by p.price DESC")//JPQL
	List<Plant> findAllPlantByPriceDesc();
	
}
