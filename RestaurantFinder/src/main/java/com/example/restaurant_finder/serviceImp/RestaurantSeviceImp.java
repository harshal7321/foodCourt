package com.example.restaurant_finder.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.restaurant_finder.dto.GetRestaurants;
import com.example.restaurant_finder.model.Restaurant;
import com.example.restaurant_finder.repository.RestaurantRepository;
import com.example.restaurant_finder.service.RestaurantService;

@Service
public class RestaurantSeviceImp implements RestaurantService {
	
	@Autowired
	private RestaurantRepository repository;
	
	public ResponseEntity<List<Restaurant>> getRestaurants() {
		return ResponseEntity.ok(repository.findAll());
	}
	
	public ResponseEntity<GetRestaurants> getRestaurantByName(String name) {
		
		System.out.println("Restaurant Name-----------"+name);
		Restaurant restaurant = repository.getRestaurantByName(name);
		GetRestaurants result = new GetRestaurants(restaurant.getRestaurantId(), restaurant.getRestaurantName(), restaurant.getRestaurantLocation(), restaurant.getOpenAt(), restaurant.getCloseAt());
		return ResponseEntity.ok(result);
	}
}
