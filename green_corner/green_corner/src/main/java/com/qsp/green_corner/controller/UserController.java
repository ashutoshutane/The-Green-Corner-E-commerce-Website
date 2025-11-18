package com.qsp.green_corner.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qsp.green_corner.entity.User;
import com.qsp.green_corner.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private UserService userservice;

	public UserController(UserService userservice) {
		super();
		this.userservice = userservice;
	}
	
	@PostMapping("/signup")
	public ResponseEntity<User> saveUser(@RequestBody User user){
		userservice.signUp(user);
		
		return ResponseEntity.status(HttpStatus.FOUND).body(user);
	}
	
	@PostMapping("/signin/{email}/{password}")
	public ResponseEntity<String> signIn(@PathVariable String email,@PathVariable String password){
		userservice.signIn(email, password);
		
		return ResponseEntity.status(HttpStatus.OK).body("Login Succesfully!");
		
	}
	
	
}
