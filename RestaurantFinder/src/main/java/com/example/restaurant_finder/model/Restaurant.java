package com.example.restaurant_finder.model;

import java.util.Set;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
@Component
public class Restaurant {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int restaurantId;
	
	@Column(length = 100)
	private String restaurantName;
	
	@Column(length = 50)
	private String restaurantLocation;
	
	@Column
	private int openAt;
	
	@Column
	private int closeAt; 
	
	@OneToMany(mappedBy = "restaurant", fetch = FetchType.EAGER)
	@JsonManagedReference(value = "restaurantFood")
	private Set<FoodItem> foodItems;

	public int getRestaurantId() {
		return restaurantId;
	}

	public Set<FoodItem> getFoodItems() {
		return foodItems;
	}

	public void setFoodItems(Set<FoodItem> foodItems) {
		this.foodItems = foodItems;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getRestaurantLocation() {
		return restaurantLocation;
	}

	public void setRestaurantLocation(String restaurantLocation) {
		this.restaurantLocation = restaurantLocation;
	}

	public int getOpenAt() {
		return openAt;
	}

	public void setOpenAt(int openAt) {
		this.openAt = openAt;
	}

	public int getCloseAt() {
		return closeAt;
	}

	public void setCloseAt(int closeAt) {
		this.closeAt = closeAt;
	}
 

	public Restaurant(String restaurantName, String restaurantLocation, int openAt, int closeAt) {
		super();
		this.restaurantName = restaurantName;
		this.restaurantLocation = restaurantLocation;
		this.openAt = openAt;
		this.closeAt = closeAt; 
	}

	public Restaurant(int restaurantId, String restaurantName, String restaurantLocation, int openAt, int closeAt) {
		super();
		this.restaurantId = restaurantId;
		this.restaurantName = restaurantName;
		this.restaurantLocation = restaurantLocation;
		this.openAt = openAt;
		this.closeAt = closeAt; 
	}

	public Restaurant() {
		super();
		// TODO Auto-generated constructor stub
	}

}
