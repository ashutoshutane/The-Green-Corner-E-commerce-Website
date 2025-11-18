package com.qsp.green_corner.exception;

public class NoPlantFoundInCart extends RuntimeException{
	
	public NoPlantFoundInCart(String Message) {
		super(Message);
	}

}
