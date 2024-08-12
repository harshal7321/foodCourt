package com.example.restaurant_finder.service;

import java.util.List;

import org.springframework.http.ResponseEntity; 

import com.example.restaurant_finder.model.Restaurant;
 
public interface RestaurantService {
	
	public ResponseEntity<List<Restaurant>> getRestaurants();
}
