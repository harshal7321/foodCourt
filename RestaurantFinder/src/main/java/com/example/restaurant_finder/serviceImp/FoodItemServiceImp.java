package com.example.restaurant_finder.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.restaurant_finder.dto.GetRestaurants;
import com.example.restaurant_finder.model.FoodItem;
import com.example.restaurant_finder.model.Restaurant;
import com.example.restaurant_finder.repository.FoodItemRepository;
import com.example.restaurant_finder.repository.RestaurantRepository;
import com.example.restaurant_finder.service.FoodItemService;

@Service
public class FoodItemServiceImp implements FoodItemService {
	
	@Autowired
	private FoodItemRepository foodItemRepository;
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	public ResponseEntity<List<FoodItem>> getItemsByRestaurantId(GetRestaurants restaurant) {
		
		Restaurant restaurant2 = restaurantRepository.getRestaurantByName(restaurant.getRestaurantName());
		List<FoodItem> result = foodItemRepository.getItemsByRestaurantId(restaurant2.getRestaurantId());
		return ResponseEntity.ok(result);
	}
	
	public ResponseEntity<FoodItem> getItemById(FoodItem foodItem) {
		
		FoodItem result = foodItemRepository.getItemById(foodItem.getItemId()); 
		return ResponseEntity.ok(result);
	}
	
}
