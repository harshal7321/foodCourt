package com.example.restaurant_finder.service;

import java.util.List;

import org.springframework.http.ResponseEntity; 

import com.example.restaurant_finder.dto.GetRestaurants;
import com.example.restaurant_finder.model.FoodItem;
 
public interface FoodItemService {
	
	public ResponseEntity<List<FoodItem>> getItemsByRestaurantId(GetRestaurants restaurant);
	
	public ResponseEntity<FoodItem> getItemById(FoodItem foodItem);
}
