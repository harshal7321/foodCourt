package com.example.restaurant_finder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.RestController;
 
import com.example.restaurant_finder.dto.OrderDto;
import com.example.restaurant_finder.serviceImp.OrderDetailServiceImp;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class OrderDetailController {
	
	@Autowired
	private OrderDetailServiceImp service;
	
	@PostMapping("/addOrder")
	public ResponseEntity<OrderDto> addOrder(@RequestBody OrderDto order) { 
		return service.addOrder(order);
	}
	
	@PostMapping("/orders")
	public List<OrderDto> getorders(@RequestBody OrderDto order) {
		return service.getOrders(order);
	}
	
	@PostMapping("/deleteOrder")
	public String deleteOrder(@RequestBody OrderDto order) { 
		return service.deleteOrder(order);
	}
	
	@PostMapping("/orderedItem")
	public List<Object> getDetailsByOrderId(@RequestBody OrderDto order) { 
		return service.getDetailsByOrderId(order);
	}
	
}
