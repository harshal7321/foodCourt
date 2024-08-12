package com.example.restaurant_finder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.restaurant_finder.dto.GetRestaurants;
import com.example.restaurant_finder.model.FoodItem;
import com.example.restaurant_finder.serviceImp.FoodItemServiceImp;

@RestController
public class FoodItemController {
	
	@Autowired
	private FoodItemServiceImp service;
	
	@PostMapping("/foodItems")
	public ResponseEntity<List<FoodItem>> postMethodName(@RequestBody GetRestaurants restaurant) {
		return service.getItemsByRestaurantId(restaurant);
	}
	
	@PostMapping("/foodItem")
	public ResponseEntity<FoodItem> getItemById(@RequestBody FoodItem foodItem){ 
		return service.getItemById(foodItem);
	}
	
}
