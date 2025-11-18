package com.qsp.green_corner.controller;



import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qsp.green_corner.entity.Cart;
import com.qsp.green_corner.exception.NoPlantFoundInCart;
import com.qsp.green_corner.service.CartService;


@RestController
@RequestMapping("/cart")
public class CartController {
	
	private CartService cartService;

	public CartController(CartService cartService) {
		super();
		this.cartService = cartService;
	}
	
	@PostMapping("addPlantToCart/{pid}/{userid}")
	public ResponseEntity<Cart> addToCart(@PathVariable long pid , @PathVariable long userid){
		Cart cart = cartService.addToCart(pid, userid);
		
		return ResponseEntity.status(HttpStatus.OK).body(cart);
	}
	
	@PostMapping("addEquipmentToCart/{eid}/{uid}")
	public ResponseEntity<Cart> addEquipmentToCart(@PathVariable long eid , @PathVariable long uid){
		Cart cartToEquipment = cartService.addEquipmentToCart(eid, uid);
		
		return ResponseEntity.status(HttpStatus.OK).body(cartToEquipment);
	}
	
	@GetMapping("allCartItems/{userid}")
	public ResponseEntity<Cart> findAllItemsInCarts(@PathVariable long userid){
		Cart allCartItemsByUserId = cartService.findAllCartItemsByUserId(userid);
		
		return ResponseEntity.status(HttpStatus.OK).body(allCartItemsByUserId);
	}
	
	@DeleteMapping("RemovePlantFromCart/{uid}/{pid}")
	public ResponseEntity<?> removefromCart(@PathVariable long uid , @PathVariable long pid){
		Cart removePlantfromCart = cartService.RemovePlantfromCart(uid, pid);
		
		if(removePlantfromCart!=null) {
			return ResponseEntity.status(HttpStatus.OK).body(removePlantfromCart);	
		}
		String message = "No Plant found with id : "+pid;
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new NoPlantFoundInCart(message));
		
//		ResponseEntity<?> allows you to return either a Cart object or a String message.
//				If the service returns null, it responds with:
//				HTTP 404 Not Found
//				Message body: "Unable to remove plant. User or plant not found in the cart."
	}
	
	@DeleteMapping("RemoveEquipmentFromCart/{uid}/{eid}")
	public ResponseEntity<?> removeEquipmentFromCart(@PathVariable long uid ,@PathVariable long eid){
		Cart removeEquipmentFromCart = cartService.removeEquipmentFromCart(uid, eid);
		
		if(removeEquipmentFromCart!=null) {
			return ResponseEntity.status(HttpStatus.OK).body(removeEquipmentFromCart);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RuntimeException());
	}
	

}
