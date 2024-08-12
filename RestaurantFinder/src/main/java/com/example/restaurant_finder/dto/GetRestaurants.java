package com.example.restaurant_finder.dto;
 

public class GetRestaurants {
	
	private int restaurantId;
	 
	private String restaurantName;
 
	private String restaurantLocation;
 
	private int openAt;
 
	private int closeAt;

	public GetRestaurants(int restaurantId, String restaurantName, String restaurantLocation, int openAt, int closeAt) {
		super();
		this.restaurantId = restaurantId;
		this.restaurantName = restaurantName;
		this.restaurantLocation = restaurantLocation;
		this.openAt = openAt;
		this.closeAt = closeAt;
	}

	public GetRestaurants() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getRestaurantId() {
		return restaurantId;
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

	public GetRestaurants(String restaurantName) {
		super();
		this.restaurantName = restaurantName;
	}
}
