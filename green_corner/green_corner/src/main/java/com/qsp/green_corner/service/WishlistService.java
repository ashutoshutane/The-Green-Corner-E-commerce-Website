package com.qsp.green_corner.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import com.qsp.green_corner.entity.Cart;
import com.qsp.green_corner.entity.Equipment;
import com.qsp.green_corner.entity.Plant;
import com.qsp.green_corner.entity.User;
import com.qsp.green_corner.entity.Wishlist;
import com.qsp.green_corner.repository.EquipmentRepository;
import com.qsp.green_corner.repository.PlantRepository;
import com.qsp.green_corner.repository.UserRepository;
import com.qsp.green_corner.repository.WishlistRepository;

@Service
public class WishlistService {
	
	@Autowired
	private WishlistRepository wishlistRepository;
	
	@Autowired
	private PlantRepository plantRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EquipmentRepository equipmentRepository;
	
	public WishlistService(WishlistRepository wishlistRepository, PlantRepository plantRepository,
			UserRepository userRepository) {
		super();
		this.wishlistRepository = wishlistRepository;
		this.plantRepository = plantRepository;
		this.userRepository = userRepository;
	}

	public Wishlist addPlantToWishlist(long uid , long pid) {
		Optional<User> userid = userRepository.findById(uid);
		Optional<Plant> plantid = plantRepository.findById(pid);
		
		if(userid.isPresent() && plantid.isPresent()) {
			User user = userid.get();
			Wishlist wishlist = user.getWishlist();
			Plant plant = plantid.get();
			
			List<Plant> plant2 = wishlist.getPlant();
			plant2.add(plant);
			
			return wishlistRepository.save(wishlist);
		}
		return null;
	}
	
	public Wishlist addEquipmentToWishlist(long uid , long eid) {
		Optional<User> userid = userRepository.findById(uid);
		Optional<Equipment> equipid = equipmentRepository.findById(eid);
		
		if(userid.isPresent() && equipid.isPresent()) {
			User user = userid.get();
			Wishlist wishlist = user.getWishlist();
			Equipment equipment = equipid.get();
			
			List<Equipment> equipment2 = wishlist.getEquipment();
			equipment2.add(equipment);
			
			return wishlistRepository.save(wishlist);
		}
		return null;
	}
	
	public Wishlist RemovePlantfromWishlist(long userid , long plantid) {
		Optional<User> uid = userRepository.findById(userid);
		Optional<Plant> pid = plantRepository.findById(plantid);
		
		if(uid.isPresent() && pid.isPresent()) {
			User user = uid.get();
			Wishlist wishlist = user.getWishlist();
			List<Plant> plant = wishlist.getPlant();
			
			Plant plant2 = pid.get();
			
			if(plant.contains(plant2)) {
				plant.remove(plant2);
				return wishlistRepository.save(wishlist);
			}
		}
		return null;
	}
	
	public Wishlist RemoveEquipmentfromWishlist(long userid , long plantid) {
		Optional<User> uid = userRepository.findById(userid);
		Optional<Equipment> eid = equipmentRepository.findById(plantid);
		
		if(uid.isPresent() && eid.isPresent()) {
			User user = uid.get();
			Wishlist wishlist = user.getWishlist();
			List<Equipment> equipment = wishlist.getEquipment();
			
			Equipment equipment2 = eid.get();
			
			if(equipment.contains(equipment2)) {
				equipment.remove(equipment2);
				return wishlistRepository.save(wishlist);
			}
		}
		return null;
	}

}
