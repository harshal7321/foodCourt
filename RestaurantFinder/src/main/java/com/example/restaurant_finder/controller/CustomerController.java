package com.example.restaurant_finder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.restaurant_finder.dto.CustomerLogin;
import com.example.restaurant_finder.serviceImp.CustomerServiceImp;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class CustomerController {
	
	@Autowired
	private CustomerServiceImp service;
	
	@PostMapping("/customerRegistration")
	public ResponseEntity<CustomerLogin> addCustomer(@RequestBody CustomerLogin register) {
		 return service.addCustomer(register);
	}
	
	@PostMapping("/customerLogin")
	public ResponseEntity<CustomerLogin> customerLogin(@RequestBody CustomerLogin login) {
		return service.getCustomerByCusomerEmail(login);
	}
	
	@PostMapping("/customerLogout")
	public ResponseEntity<String> logoutCustomer(@RequestBody CustomerLogin login) {
		return service.logoutCustomer(login);
	}
	
	@PostMapping("/updateProfile")
	public CustomerLogin updateProfile(@RequestBody CustomerLogin login) { 
		return service.updateProfile(login);
	}
	
	@PostMapping("/getPassword")
	public CustomerLogin getPassword(@RequestBody CustomerLogin login) { 
		return service.getPassword(login);
	}
	
	@PostMapping("/updatePassword")
	public ResponseEntity<String> updatePassword(@RequestBody CustomerLogin login) { 
		return service.updatePassword(login);
	}
}
