package com.example.restaurant_finder.service;

import org.springframework.http.ResponseEntity;

import com.example.restaurant_finder.dto.CustomerLogin;

public interface CustomerService {
	
	public ResponseEntity<CustomerLogin> getCustomerByCusomerEmail(CustomerLogin login);
	
	public ResponseEntity<String> logoutCustomer(CustomerLogin login);
	
	public ResponseEntity<CustomerLogin> addCustomer(CustomerLogin register);
	
	public CustomerLogin updateProfile(CustomerLogin customer);
	
	public CustomerLogin getPassword(CustomerLogin customer);
	
	public ResponseEntity<String> updatePassword(CustomerLogin customer);
}
