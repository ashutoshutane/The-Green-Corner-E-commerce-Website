package com.qsp.green_corner.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class DeliveryEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String country;
	
	private String firstname;
	
	private String lastname;
	
	private String address;
	
	private String state;
	
	private String city;
	
	private int pincode;
	
	private long phone;
	
	private double totalbill;
	
}
