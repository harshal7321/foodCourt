package com.example.restaurant_finder.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
 
import com.example.restaurant_finder.dto.OrderDto;
 
public interface OrderDetailService {
	
	public ResponseEntity<OrderDto> addOrder(OrderDto order);
		
	public List<OrderDto> getOrders(OrderDto order);
	
	public String deleteOrder(OrderDto order);
	
	public List<Object> getDetailsByOrderId(OrderDto order);
}
