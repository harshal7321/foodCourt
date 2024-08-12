package com.example.restaurant_finder.model;

import java.time.LocalDateTime;
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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Component
public class OrderDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderDetailId;
	
	@OneToMany(mappedBy = "orderDetail", fetch = FetchType.EAGER)
	@JsonManagedReference(value = "carts")
	private Set<ItemCart> carts;
	
	@ManyToOne
	@JoinColumn(name = "customerId")
	@JsonBackReference("customerOder")
	private Customer customer;
	
	@Column
	private double totalAmount;
	
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime orderDetailDate;

	public OrderDetail() {
		super(); 
	}

	public OrderDetail(Customer customer, double totalAmount, LocalDateTime orderDetailDate) {
		super();
		this.customer = customer;
		this.totalAmount = totalAmount;
		this.orderDetailDate = orderDetailDate;
	}

	public OrderDetail(int orderDetailId, Set<ItemCart> carts, Customer customer, double totalAmount,
			LocalDateTime orderDetailDate) {
		super();
		this.orderDetailId = orderDetailId;
		this.carts = carts;
		this.customer = customer;
		this.totalAmount = totalAmount;
		this.orderDetailDate = orderDetailDate;
	}

	public int getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(int orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public Set<ItemCart> getCarts() {
		return carts;
	}

	public void setCarts(Set<ItemCart> carts) {
		this.carts = carts;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public LocalDateTime getOrderDetailDate() {
		return orderDetailDate;
	}

	public void setOrderDetailDate(LocalDateTime orderDetailDate) {
		this.orderDetailDate = orderDetailDate;
	}

	 
}
