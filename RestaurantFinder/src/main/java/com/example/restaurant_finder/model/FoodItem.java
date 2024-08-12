package com.example.restaurant_finder.model;

import java.util.Set;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
@Component
public class FoodItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int itemId;
	
	@Column(length = 100)
	private String itemrName;
	
	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	@Column
	private double itemPrice; 
	 
	@OneToMany(mappedBy = "foodItem", fetch = FetchType.EAGER)
	@JsonManagedReference(value = "cartItem")
	private Set<ItemCart> carts;
	
	@ManyToOne
	@JoinColumn(name = "restaurantId")
	@JsonBackReference(value = "restaurantFood")
	private Restaurant restaurant;

	public FoodItem() {
		super(); 
	}

	public FoodItem(int itemId, String itemrName, double itemPrice, Set<ItemCart> carts, Restaurant restaurant) {
		super();
		this.itemId = itemId;
		this.itemrName = itemrName;
		this.itemPrice = itemPrice;
		this.carts = carts;
		this.restaurant = restaurant;
	}

	public FoodItem(String itemrName, double itemPrice, Set<ItemCart> carts, Restaurant restaurant) {
		super();
		this.itemrName = itemrName;
		this.itemPrice = itemPrice;
		this.carts = carts;
		this.restaurant = restaurant;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getItemrName() {
		return itemrName;
	}

	public void setItemrName(String itemrName) {
		this.itemrName = itemrName;
	}

	public double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public Set<ItemCart> getCarts() {
		return carts;
	}

	public void setCarts(Set<ItemCart> carts) {
		this.carts = carts;
	}

	 
}
