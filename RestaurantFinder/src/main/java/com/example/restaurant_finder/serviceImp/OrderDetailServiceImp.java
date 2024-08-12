package com.example.restaurant_finder.serviceImp;
 
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
 
import com.example.restaurant_finder.dto.OrderDto;
import com.example.restaurant_finder.model.Customer; 
import com.example.restaurant_finder.model.OrderDetail;
import com.example.restaurant_finder.repository.CustomerRepository;
import com.example.restaurant_finder.repository.ItemcartRepository;
import com.example.restaurant_finder.repository.OrderDetailRepository;
import com.example.restaurant_finder.service.OrderDetailService;

@Service
public class OrderDetailServiceImp implements OrderDetailService {
	
	@Autowired
	private OrderDetailRepository repository; 
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ItemcartRepository itemcartRepository;
	
	public ResponseEntity<OrderDto> addOrder(OrderDto order) {
		
		LocalDateTime date = LocalDateTime.now();  
		
		@SuppressWarnings("deprecation")
		Customer customer = customerRepository.getById(order.getcustomerId());
		
		OrderDetail newOrder = new OrderDetail(customer, order.getOrderAmount(), date);
		OrderDetail addedOrder = repository.save(newOrder); 
		
		OrderDto result = new OrderDto(addedOrder.getOrderDetailId(), order.getcustomerId(), addedOrder.getTotalAmount(), addedOrder.getOrderDetailDate());
		
		return ResponseEntity.ok(result);
	}

	@Override
	public List<OrderDto> getOrders(OrderDto order) { 
		
		List<OrderDetail> orders = repository.findByCustomerCustomerId(order.getcustomerId());
		List<OrderDto> result = new ArrayList<>();
		
		int number = 1;
		
		for(OrderDetail o : orders) {
			
			OrderDto temp = new OrderDto();
			temp.setOrderNo(number);
			temp.setOrderId(o.getOrderDetailId());
			temp.setcustomerId(order.getcustomerId());
			temp.setOrderAmount(o.getTotalAmount());
			temp.setOrderDate(o.getOrderDetailDate());
			
			result.add(temp);
			number++;
		} 
		
		return result;
	}

	@Override
	public String deleteOrder(OrderDto order) {
		
		itemcartRepository.deleteCartByOrderId(order.getOrderId());
		repository.deleteById(order.getOrderId());
		 
		return "Order deleted successfully";
	}

	@Override
	public List<Object> getDetailsByOrderId(OrderDto order) {
		
		List<Object[]> details = repository.getDeatilsByOrderId(order.getOrderId());
 
		return details.parallelStream().map( detail -> Map.of(
			"orderId",detail[0],
			"cartId",detail[1],
			"itemName",detail[2],
			"itemPrice",detail[3],
			"itemQuantity",detail[4]
		)).collect(Collectors.toList());
	}

}
