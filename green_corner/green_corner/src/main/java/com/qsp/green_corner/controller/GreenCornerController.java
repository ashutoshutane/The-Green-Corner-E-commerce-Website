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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qsp.green_corner.entity.Cart;
import com.qsp.green_corner.entity.ContactUs;
import com.qsp.green_corner.entity.DeliveryEntity;
import com.qsp.green_corner.entity.Equipment;
import com.qsp.green_corner.entity.Plant;
import com.qsp.green_corner.entity.Reviews;
import com.qsp.green_corner.entity.User;
import com.qsp.green_corner.entity.Wishlist;
import com.qsp.green_corner.exception.NoPlantFoundInCart;
import com.qsp.green_corner.service.CartService;
import com.qsp.green_corner.service.ContactusService;
import com.qsp.green_corner.service.DeliveryService;
import com.qsp.green_corner.service.EquipmentService;
import com.qsp.green_corner.service.MailService;
import com.qsp.green_corner.service.PlantService;
import com.qsp.green_corner.service.UpiRequestService;
import com.qsp.green_corner.service.UserService;
import com.qsp.green_corner.service.WishlistService;

@RestController
public class GreenCornerController {
	
//=======================================plant Controller===========================================================
	@Autowired
	private PlantService plantService;

	@PostMapping("/plant/save")
	public ResponseEntity<Plant> savePlant(@RequestBody Plant plant){
		Plant savePlant = plantService.savePlant(plant);
		return ResponseEntity.status(HttpStatus.OK).body(plant);
	}
	
	@GetMapping("/plant/findAll")
	public ResponseEntity<List<Plant>> findAll(@RequestBody Plant plant){
		List<Plant> allPlant = plantService.findAll();
		return ResponseEntity.status(HttpStatus.FOUND).body(allPlant);
	}
	
	@PutMapping("/plant/{id}/reviews")
	public ResponseEntity<String> giveReveiwToPlant(@RequestBody Reviews reviews , @PathVariable long id){
		String giveReviewToPlant = plantService.giveReviewToPlant(reviews, id);
		return ResponseEntity.status(HttpStatus.OK).body(giveReviewToPlant);
	}
	
	@GetMapping("/plant/ratings")
	public ResponseEntity<List<Plant>> findPlantByRating(@RequestParam double rating){
		List<Plant> plantByRating = plantService.findPlantByRating(rating);
		return ResponseEntity.status(HttpStatus.OK).body(plantByRating);
	}
	
	@GetMapping("/plant/ratingBetween")
	public ResponseEntity<List<Plant>> findPlantByRatingBetween(@RequestParam double min){
		List<Plant> plantByRatingBetween = plantService.findPlantByRatingBetween(min);
		
		return ResponseEntity.status(HttpStatus.OK).body(plantByRatingBetween);
	}
	
	@GetMapping("/plant/findByCategorie")
	public ResponseEntity<List<Plant>> findPlantByCategories(@RequestParam String categorie){
		List<Plant> plantByCategories = plantService.findPlantByCategories(categorie);
		return ResponseEntity.status(HttpStatus.OK).body(plantByCategories);
	}
	
	@GetMapping("/plant/sortAccordingToPriceInAsc")
	public ResponseEntity<List<Plant>> sortPlantInAscAccToPrice(){
		List<Plant> sortPlantInAscAccToPrice = plantService.sortPlantInAscAccToPrice();
		return ResponseEntity.status(HttpStatus.OK).body(sortPlantInAscAccToPrice);
	}
	
	@GetMapping("/plant/sortAccordingToPriceInDesc")
	public ResponseEntity<List<Plant>> sortPlantInDescAccToPrice(){
		List<Plant> sortPlantInDescAccToPrice = plantService.sortPlantInDescAccToPrice();
		return ResponseEntity.status(HttpStatus.OK).body(sortPlantInDescAccToPrice);
	}
	
	@GetMapping("/plant/getPlants")
	public ResponseEntity<Page<Plant>> getPlants(@RequestParam int page, @RequestParam int size){
		Page<Plant> plants = plantService.getPlants(page, size);
		return ResponseEntity.status(HttpStatus.OK).body(plants);
	}
	
	
//===========================================User Controller==========================================================	
	
	@Autowired
	private UserService userservice;

	
	@PostMapping("/user/signup")
	public ResponseEntity<User> saveUser(@RequestBody User user){
		userservice.signUp(user);
		
		return ResponseEntity.status(HttpStatus.FOUND).body(user);
	}
	
	@PostMapping("/user/signin/{email}/{password}")
	public ResponseEntity<String> signIn(@PathVariable String email,@PathVariable String password){
		userservice.signIn(email, password);
		
		return ResponseEntity.status(HttpStatus.OK).body("Login Succesfully!");
		
	}
	
//===========================================Cart Controller===============================================================	
	
	@Autowired
	private CartService cartService;

	
	@PostMapping("/cart/addPlantToCart/{pid}/{userid}")
	public ResponseEntity<Cart> addToCart(@PathVariable long pid , @PathVariable long userid){
		Cart cart = cartService.addToCart(pid, userid);
		
		return ResponseEntity.status(HttpStatus.OK).body(cart);
	}
	
	@PostMapping("/cart/addEquipmentToCart/{eid}/{uid}")
	public ResponseEntity<Cart> addEquipmentToCart(@PathVariable long eid , @PathVariable long uid){
		Cart cartToEquipment = cartService.addEquipmentToCart(eid, uid);
		
		return ResponseEntity.status(HttpStatus.OK).body(cartToEquipment);
	}
	
	@GetMapping("/cart/allCartItems/{userid}")
	public ResponseEntity<Cart> findAllItemsInCarts(@PathVariable long userid){
		Cart allCartItemsByUserId = cartService.findAllCartItemsByUserId(userid);
		
		return ResponseEntity.status(HttpStatus.OK).body(allCartItemsByUserId);
	}
	
	@DeleteMapping("/cart/RemovePlantFromCart/{uid}/{pid}")
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
	
	@DeleteMapping("/cart/RemoveEquipmentFromCart/{uid}/{eid}")
	public ResponseEntity<?> removeEquipmentFromCart(@PathVariable long uid ,@PathVariable long eid){
		Cart removeEquipmentFromCart = cartService.removeEquipmentFromCart(uid, eid);
		
		if(removeEquipmentFromCart!=null) {
			return ResponseEntity.status(HttpStatus.OK).body(removeEquipmentFromCart);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RuntimeException());
	}
	
//=========================================ContactUs Controller=====================================================
	@Autowired
	private ContactusService contactusService;

	
	@PostMapping("/contact/send")
	public ResponseEntity<ContactUs> sendContact(@RequestBody ContactUs contactUs){
		ContactUs saveMessage = contactusService.saveMessage(contactUs);
		return ResponseEntity.status(HttpStatus.CREATED).body(saveMessage);
	}
	
//=========================================Delivery Controller==========================================================	
	@Autowired
	private DeliveryService deliveryService;
	
	@PostMapping("/delivery/save")
	public ResponseEntity<DeliveryEntity> save(@RequestBody DeliveryEntity deliveryEntity){
		DeliveryEntity saveDelivery = deliveryService.saveDelivery(deliveryEntity);
		return ResponseEntity.status(HttpStatus.OK).body(saveDelivery);
	}
	
//==========================================Equipment Controller============================================================
	@Autowired
	EquipmentService equipmentService;

	
	@PostMapping("/equipment/save")
	public ResponseEntity<Equipment>  saveEquipment(@RequestBody Equipment equipment) {
		Equipment saveEquipment = equipmentService.saveEquipment(equipment);
//		System.out.println(saveEquipment);
		return ResponseEntity.status(HttpStatus.OK).body(saveEquipment);
	}

	@PutMapping("/equipment/{id}/reviews")
	public ResponseEntity<String> giveReveiwToEquipment(@RequestBody Reviews reviews , @PathVariable Long id){
		String giveReveiwToEquipment = equipmentService.giveReveiwToEquipment(reviews, id);
		return ResponseEntity.status(HttpStatus.OK).body(giveReveiwToEquipment);
	}

//========================================Mail Controller=======================================================================	
	@Autowired
	private MailService mailService;
	
	@PostMapping("/SendMail/send")
	public ResponseEntity<String> contact(@RequestBody ContactUs contactUs){
		contactusService.saveMessage(contactUs);
		mailService.sendMail(contactUs.getEmail(),contactUs.getSubject(), contactUs.getMessage());
		return ResponseEntity.status(HttpStatus.OK).body("Message Sent Successfully");
	}
	
//========================================UpiRequest Controller==================================================================
	@Autowired
	private UpiRequestService upiRequestService;

	
	@GetMapping("/Upi/upilink/{UpiId}/{name}/{amount}")
	public ResponseEntity<String> generateUpiLink(@PathVariable String UpiId , @PathVariable String name, @PathVariable double amount){
		String upiLink = upiRequestService.generateUpiLink(UpiId, name, amount);
		return ResponseEntity.status(HttpStatus.OK).body(upiLink);
	}
//======================================Wishlist Controller=======================================================================

	@Autowired
	private WishlistService wishlistService;
	
	@PostMapping("/wishlist/addPlantToWishlist/{uid}/{pid}")
	public ResponseEntity<Wishlist> addPlantToWishlist(@PathVariable long uid , @PathVariable long pid){
		Wishlist toWishlist = wishlistService.addPlantToWishlist(uid, pid);		
		return ResponseEntity.status(HttpStatus.OK).body(toWishlist);
	}
	
	@PostMapping("/wishlist/addEquipmentToWishlist/{uid}/{eid}")
	public ResponseEntity<Wishlist> addEquipmentToWishlist(@PathVariable long uid , @PathVariable long eid ){
		Wishlist equipmentToWishlist = wishlistService.addEquipmentToWishlist(uid, eid);
		return ResponseEntity.status(HttpStatus.OK).body(equipmentToWishlist);
		}

}
