package com.example.restaurant_finder.dto;

import java.time.LocalDateTime;

public class OrderDto {
	
	
	private int orderId;
	private int customerId;
	private double orderAmount;
	private LocalDateTime orderDate;
	
	private int OrderNo;
	public int getOrderNo() {
		return OrderNo;
	}

	public void setOrderNo(int orderNo) {
		OrderNo = orderNo;
	}

	
	public OrderDto() {
		super(); 
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getcustomerId() {
		return customerId;
	}

	public void setcustomerId(int cartId) {
		this.customerId = cartId;
	}

	public double getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(double orderAmount) {
		this.orderAmount = orderAmount;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public OrderDto(int customerId, double orderAmount, LocalDateTime orderDate) {
		super();
		this.customerId = customerId;
		this.orderAmount = orderAmount;
		this.orderDate = orderDate;
	}

	public OrderDto(int orderId, int customerId, double orderAmount, LocalDateTime orderDate) {
		super();
		this.orderId = orderId;
		this.customerId = customerId;
		this.orderAmount = orderAmount;
		this.orderDate = orderDate;
	}
 
}
