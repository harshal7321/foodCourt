package com.example.restaurant_finder.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.restaurant_finder.dto.GetRestaurants;
import com.example.restaurant_finder.model.Restaurant;
import com.example.restaurant_finder.serviceImp.RestaurantSeviceImp;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
 


@RestController
public class RestaurantController {
	
	@Autowired
	private RestaurantSeviceImp service;
	
	@GetMapping("/restaurants")
	public ResponseEntity<List<Restaurant>> getRestaurants() {
		return service.getRestaurants();
	}
	
	@PostMapping("/restaurant")
	public ResponseEntity<GetRestaurants> getRestaurantByName(@RequestBody GetRestaurants restaurant) {
		return service.getRestaurantByName(restaurant.getRestaurantName());
	}
	
}
