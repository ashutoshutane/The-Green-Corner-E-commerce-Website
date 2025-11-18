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
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "Plant")
public class Plant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String description;
	
	private double price;
	
	private double discountPrice; 
	
	private float rating;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Reviews> reviews;
	
	private int totalSalesLastMonth;
	
	private String sellerName;
	
	@OneToOne(cascade = CascadeType.ALL)
	private SellerAddress sellerAddress;
	
	private String availability;
	
	private String quantityAvailable;
	
	@ElementCollection
	private List<String> categories ;
	
	private String sunlightRequirement;
	
	private String moistureRequirement;
	
	private String soilType;
	
	private String season;
	
	private String growthRate;
	
	private String potSizeRequired;
	
	private String genus;
	
	private String localName;
	
	private String regionalName;
	
	private String biologicalName;
	
	private String botanicalName;
	
	@ElementCollection
	private List<String> Tags;
	
	@ElementCollection
	private List<String> shippingStates;
	
	private String primaryImage;
	
	@ElementCollection
	private List<String> secondaryImages;
	
	private String shoppingPolicy;
	
	private String refundPolicy;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Cart> cart = new ArrayList<Cart>();
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Wishlist> wishlist = new ArrayList<Wishlist>();
}
