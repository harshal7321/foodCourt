package com.example.restaurant_finder.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.restaurant_finder.dto.CartDto;
import com.example.restaurant_finder.dto.CustomerLogin;
import com.example.restaurant_finder.serviceImp.ItemCartServiceImp;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class ItemCartController {
	
	@Autowired
	private ItemCartServiceImp service;
	
	@PostMapping("/addToCart")
	public ResponseEntity<CartDto> addToCart(@RequestBody CartDto cart) {
		return service.addToCart(cart);
	}
	
	@PostMapping("carts")
	public ResponseEntity<List<CartDto>> getCarts(@RequestBody CustomerLogin customer) {
		return service.getCarts(customer);
	}
	
	@PostMapping("/deleteFromCart")
	public String deleteFromCart(@RequestBody CartDto cart) {
		return service.deleteFromCart(cart);
	}
	
	@PostMapping("/quantity")
	public ResponseEntity<String> updateQuantity(@RequestBody CartDto cart) { 
		return service.updateQuantity(cart);
	}
	
	@PostMapping("/updateOrderId")
	public String updateOrderId(@RequestBody CartDto cart) { 
		return service.updateOrderId(cart);
	}
	
}
