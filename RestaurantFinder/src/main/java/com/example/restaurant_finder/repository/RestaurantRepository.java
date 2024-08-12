package com.example.restaurant_finder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository; 
import com.example.restaurant_finder.model.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
	 
	@Query("from Restaurant where restaurantName = :name")
	public Restaurant getRestaurantByName(@Param("name") String name);
}
