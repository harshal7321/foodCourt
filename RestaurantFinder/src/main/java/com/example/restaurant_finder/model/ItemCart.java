package com.example.restaurant_finder.model;
 
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference; 

import jakarta.persistence.Column;
import jakarta.persistence.Entity; 
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne; 

@Entity
@Component
public class ItemCart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cartId;
	
	@ManyToOne
	@JoinColumn(name = "customerId")
	@JsonBackReference("cartCustomer")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name = "itemId")
	@JsonBackReference("cartItem")
	private FoodItem foodItem;

	@ManyToOne
	@JoinColumn(name = "orderDetailId")
	@JsonBackReference("carts")
	private OrderDetail orderDetail;
	
	@Column
	private int itemQuantity;

	public ItemCart(Customer customer, FoodItem foodItem, int itemQuantity) {
		super();
		this.customer = customer;
		this.foodItem = foodItem;
		this.itemQuantity = itemQuantity;
	}

	public ItemCart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderDetail getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(OrderDetail orderDetail) {
		this.orderDetail = orderDetail;
	}

	public int getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public FoodItem getFoodItem() {
		return foodItem;
	}

	public void setFoodItem(FoodItem foodItem) {
		this.foodItem = foodItem;
	} 
}
