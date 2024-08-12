package com.example.restaurant_finder.dto;


public class CartDto {
	
	private int cartId;
	
	private int customerId;
	
	private int itemId;
	
	private int quantity;
	
	private int orderId;

	public int getOrderId() {
		return orderId;
	}



	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}



	public int getCartId() {
		return cartId;
	}
	
	

	public CartDto(int cartId, int customerId, int itemId, int quantity) {
		super();
		this.cartId = cartId;
		this.customerId = customerId;
		this.itemId = itemId;
		this.quantity = quantity;
	}



	public CartDto(int customerId, int itemId, int quantity) {
		super();
		this.customerId = customerId;
		this.itemId = itemId;
		this.quantity = quantity;
	}



	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public CartDto() {
		super();
		// TODO Auto-generated constructor stub
	}
 
	 
}
