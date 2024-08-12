package com.example.restaurant_finder.model;

import java.time.LocalDateTime;
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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity 
@Component
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerId;
	
	@Column(length = 100)
	private String customerName;
	
	@Column(length = 100)
	private String customerEmail;
	
	@Column(length = 10)
	private String customerContact;
	
	@Column(length = 50)
	private String customerLocation;
	
	@Column(length = 50)
	private String customerPassword;
	
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime registeredAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime updatedAt;
 	
	@OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
	@JsonManagedReference(value = "cartCustomer")
	private Set<ItemCart> carts; 
	
	@OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
	@JsonManagedReference(value = "customerOder")
	private Set<OrderDetail> orderDetail; 
	
	
	public Customer(String customerName, String customerEmail, String customerContact, String customerLocation,
			String customerPassword) {
		super();
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.customerContact = customerContact;
		this.customerLocation = customerLocation;
		this.customerPassword = customerPassword;
	}


	public Customer(int customerId, String customerName, String customerEmail, String customerContact,
			String customerLocation, String customerPassword) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.customerContact = customerContact;
		this.customerLocation = customerLocation;
		this.customerPassword = customerPassword;
	}


	 
	public Customer(int customerId, String customerName, String customerEmail, String customerContact,
			String customerLocation) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.customerContact = customerContact;
		this.customerLocation = customerLocation;
	}


	public Customer(String customerName, String customerEmail, String customerContact, String customerLocation,
			String customerPassword, LocalDateTime registeredAt) {
		super();
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.customerContact = customerContact;
		this.customerLocation = customerLocation;
		this.customerPassword = customerPassword;
		this.registeredAt = registeredAt; 
	}


	public int getCustomerId() {
		return customerId;
	}


	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}


	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public String getCustomerEmail() {
		return customerEmail;
	}


	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}


	public String getCustomerContact() {
		return customerContact;
	}


	public void setCustomerContact(String customerContact) {
		this.customerContact = customerContact;
	}


	public String getCustomerLocation() {
		return customerLocation;
	}


	public void setCustomerLocation(String customerLocation) {
		this.customerLocation = customerLocation;
	}


	public String getCustomerPassword() {
		return customerPassword;
	}


	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}


	public LocalDateTime getRegisteredAt() {
		return registeredAt;
	}


	public void setRegisteredAt(LocalDateTime registeredAt) {
		this.registeredAt = registeredAt;
	}


	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}


	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}


	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}


	public Set<ItemCart> getCarts() {
		return carts;
	}


	public void setCarts(Set<ItemCart> carts) {
		this.carts = carts;
	}


	public void remove() {
		this.customerId = 0;
		this.customerName = null;
		this.customerEmail = null;
		this.customerContact = null;
		this.customerLocation = null;
		this.customerPassword = null;
	}
}
