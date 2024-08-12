package com.example.restaurant_finder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.restaurant_finder.model.FoodItem; 

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem, Integer> {
	 
	@Query(value = "select * from food_item where restaurant_id = :id", nativeQuery = true)
	public List<FoodItem> getItemsByRestaurantId(@Param("id") int id);
	
	@Query("from FoodItem where itemId = :id")
	public FoodItem getItemById(@Param("id") int id);
}
