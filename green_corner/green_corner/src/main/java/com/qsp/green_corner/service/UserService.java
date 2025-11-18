package com.qsp.green_corner.service;

import org.springframework.stereotype.Service;

import com.qsp.green_corner.entity.Cart;
import com.qsp.green_corner.entity.User;
import com.qsp.green_corner.entity.Wishlist;
import com.qsp.green_corner.repository.UserRepository;

@Service
public class UserService {
	
	private UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	
	public User signUp(User user) {
		Cart cart = new Cart();
		cart.setUser(user);
		user.setCart(cart);
		
		Wishlist wishlist = new Wishlist();
		wishlist.setUser(user);
		user.setWishlist(wishlist);
		
		User save = userRepository.save(user);
		return save;
	}
	
	
	
	public User signIn(String email,String password) {
		User user = userRepository.findByemail(email);
		
		if(user == null) {
			return null;
		}
		if(user.getPassword().equals(password)) {
			return user;
		}
		else {
			return null;
		}
	}

}
