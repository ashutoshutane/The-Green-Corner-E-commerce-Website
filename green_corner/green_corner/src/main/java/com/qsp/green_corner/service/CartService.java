package com.qsp.green_corner.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.qsp.green_corner.entity.Cart;
import com.qsp.green_corner.entity.Equipment;
import com.qsp.green_corner.entity.Plant;
import com.qsp.green_corner.entity.User;
import com.qsp.green_corner.repository.CartRepository;
import com.qsp.green_corner.repository.EquipmentRepository;
import com.qsp.green_corner.repository.PlantRepository;
import com.qsp.green_corner.repository.UserRepository;

@Service
public class CartService {
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private PlantRepository plantRepository;
	
	@Autowired
	private EquipmentRepository equipmentRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public Cart addToCart(long plantid , long userid ) {
		Optional<User> uid = userRepository.findById(userid);
		Optional<Plant> pid = plantRepository.findById(plantid);
		if(uid.isPresent() && pid.isPresent()) {
			User user = uid.get();
			Cart cart = user.getCart();
			Plant plant = pid.get();
			
			if(cart==null) {
				cart = new Cart();
				user.setCart(cart);
				cart.setUser(user);
				
			}
			
			List<Plant> plant2 = cart.getPlant();
			plant2.add(plant);
			
			return cartRepository.save(cart);
		}
		return null;
}
	
	
		public Cart addEquipmentToCart(long equipid, long userid) {
			Optional<User> uid = userRepository.findById(userid);
			Optional<Equipment> eid = equipmentRepository.findById(equipid);
			
			if(uid.isPresent() && eid.isPresent()) {
				User user = uid.get();
				Cart cart = user.getCart();
				List<Equipment> equipments = cart.getEquipments();
				
				Equipment equipment = eid.get();
				equipments.add(equipment);
				
				return cartRepository.save(cart);
			}
			return null;
		}
		
		public Cart findAllCartItemsByUserId(long userid) {
			Optional<User> uid = userRepository.findById(userid);
			if(uid.isPresent()) {
				User user = uid.get();
				Cart cart = user.getCart();
				return cart;	
			}
			return null;
		}
		
		
		public Cart RemovePlantfromCart(long userid , long plantid) {
			Optional<User> uid = userRepository.findById(userid);
			Optional<Plant> pid = plantRepository.findById(plantid);
			
			if(uid.isPresent() && pid.isPresent()) {
				User user = uid.get();
				Cart cart = user.getCart();
				List<Plant> plant = cart.getPlant();
				
				Plant plant2 = pid.get();
				
				if(plant.contains(plant2)) {
					plant.remove(plant2);
					return cartRepository.save(cart);
				}
			}
			return null;
		}
		
		public Cart removeEquipmentFromCart(long uid , long eid) {
			Optional<User> userid = userRepository.findById(uid);
			Optional<Equipment> equipid = equipmentRepository.findById(eid);
			
			if(userid.isPresent() && equipid.isPresent()) {
				User user = userid.get();
				Cart cart = user.getCart();
				
				List<Equipment> equipments = cart.getEquipments();
				Equipment equipment = equipid.get();
				
				if(equipments.contains(equipment)) {
					equipments.remove(equipment);
					return cartRepository.save(cart);
				}
			}
			return null;
		}
		
}
	
	
		

