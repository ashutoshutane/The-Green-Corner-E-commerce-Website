package com.qsp.green_corner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qsp.green_corner.entity.Wishlist;
import com.qsp.green_corner.service.WishlistService;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {
	
	@Autowired
	private WishlistService wishlistService;

	public WishlistController(WishlistService wishlistService) {
		super();
		this.wishlistService = wishlistService;
	}
	
	@PostMapping("/addPlantToWishlist/{uid}/{pid}")
	public ResponseEntity<Wishlist> addPlantToWishlist(@PathVariable long uid , @PathVariable long pid){
		Wishlist toWishlist = wishlistService.addPlantToWishlist(uid, pid);		
		return ResponseEntity.status(HttpStatus.OK).body(toWishlist);
	}
	
	@PostMapping("/addEquipmentToWishlist/{uid}/{eid}")
	public ResponseEntity<Wishlist> addEquipmentToWishlist(@PathVariable long uid , @PathVariable long eid ){
		Wishlist equipmentToWishlist = wishlistService.addEquipmentToWishlist(uid, eid);
		return ResponseEntity.status(HttpStatus.OK).body(equipmentToWishlist);
		}

}
