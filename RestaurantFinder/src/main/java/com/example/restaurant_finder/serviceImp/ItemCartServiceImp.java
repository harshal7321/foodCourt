package com.example.restaurant_finder.serviceImp;

import java.util.ArrayList;
import java.util.List; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service; 
import com.example.restaurant_finder.dto.CartDto;
import com.example.restaurant_finder.dto.CustomerLogin;
import com.example.restaurant_finder.model.FoodItem;
import com.example.restaurant_finder.model.ItemCart; 
import com.example.restaurant_finder.repository.ItemcartRepository;
import com.example.restaurant_finder.service.ItemCartService; 

@Service
public class ItemCartServiceImp implements ItemCartService {
	
	@Autowired
	private ItemcartRepository repository; 
	
	public ResponseEntity<CartDto> addToCart(CartDto cart) {
		
		try {
			ItemCart itemCart = repository.getByCustomerAndItem(cart.getCustomerId(), cart.getItemId());
			
			if (itemCart == null) {
				
				repository.addToCard(cart.getCustomerId(), cart.getItemId(), cart.getQuantity());
				
				ItemCart item = repository.getByCustomerAndItem(cart.getCustomerId(), cart.getItemId());
				
				CartDto result = new CartDto(item.getCartId(), cart.getCustomerId(), cart.getItemId(), cart.getQuantity());
				return ResponseEntity.ok(result);
			} 
			else {
				CartDto result = new CartDto(itemCart.getCartId(), cart.getCustomerId(), cart.getItemId(), cart.getQuantity());
				return ResponseEntity.ok(result);
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(cart);
		}  
	}

	public ResponseEntity<List<CartDto>> getCarts(CustomerLogin customer) {
		
		try {
			
			List<ItemCart> carts = repository.getCartsByCustomerId(customer.getCustomerId());
			
			List<CartDto> result  = new ArrayList<>();
			
			carts.forEach( cart -> { 
				
				FoodItem foodItem = cart.getFoodItem();
				CartDto cartDto = new CartDto(cart.getCartId(), customer.getCustomerId(), foodItem.getItemId(), cart.getItemQuantity()); 
				result.add(cartDto); 
			}); 
			
			return ResponseEntity.ok(result);
		} 
		catch (Exception e) {
			return ResponseEntity.ok(null);
		}
	}
	
	public String deleteFromCart(CartDto cart) {
		
		repository.deleteFromCart(cart.getCustomerId(), cart.getItemId());
		return "Deleted Successfully";
	}

	@Override
	public ResponseEntity<String> updateQuantity(CartDto cart) {
		
		repository.updateQuantity(cart.getCustomerId(), cart.getItemId(), cart.getQuantity());
		
		return ResponseEntity.ok("Updated successfully");
	}

	@Override
	public String updateOrderId(CartDto cart) { 
		
		repository.updateOrderId(cart.getCartId(), cart.getOrderId());
		return "orderId Updated Successfully";
	}

	@Override
	public ItemCart getByCustomerAndItem(CartDto cart) {
		// TODO Auto-generated method stub
		return null;
	}
}
