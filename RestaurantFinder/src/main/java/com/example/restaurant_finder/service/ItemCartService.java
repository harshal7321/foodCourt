package com.example.restaurant_finder.service;

import java.util.List;

import org.springframework.http.ResponseEntity; 

import com.example.restaurant_finder.dto.CartDto;
import com.example.restaurant_finder.dto.CustomerLogin;
import com.example.restaurant_finder.model.ItemCart;
 
public interface ItemCartService {
	
	public ItemCart getByCustomerAndItem(CartDto cart);
	
	public ResponseEntity<CartDto> addToCart(CartDto cart);
	
	public ResponseEntity<List<CartDto>> getCarts(CustomerLogin customer);
	
	public String deleteFromCart(CartDto cart);
	
	public ResponseEntity<String> updateQuantity(CartDto cart);
	
	public String updateOrderId(CartDto cart);
	 
}
