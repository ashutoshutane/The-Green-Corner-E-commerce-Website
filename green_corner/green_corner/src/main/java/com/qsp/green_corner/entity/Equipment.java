package com.qsp.green_corner.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Equipment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;
	
	private String description;
	
	private double price ;
	
	private float rating;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Reviews> reviews;
	
	@ElementCollection
	private List<String> categories;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	private Seller seller;
	
	private String availability;
	
	private String quantityAvailable;
	
	@ElementCollection
	private List<String> tags;
	
	private String shippingPolicy;
	
	private String refundPolicy;
	
	private String primaryImage;
	
	@ElementCollection
	private List<String> secondaryImages;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Cart> cart = new ArrayList<Cart>();
} 
