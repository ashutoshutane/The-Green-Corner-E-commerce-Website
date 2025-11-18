package com.qsp.green_corner.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Plant> plant = new ArrayList<Plant>();
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Equipment> equipments = new ArrayList<Equipment>();
	
	
	@OneToOne(cascade = CascadeType.PERSIST)
	private User user;
}
