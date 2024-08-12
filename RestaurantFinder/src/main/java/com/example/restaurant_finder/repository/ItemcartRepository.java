package com.example.restaurant_finder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository; 
import com.example.restaurant_finder.model.ItemCart;

import jakarta.transaction.Transactional; 

@Repository
public interface ItemcartRepository extends JpaRepository<ItemCart, Integer> {
	
	@Query(value = "select * from item_cart where customer_id = :customer and item_id = :item and order_detail_id is null", nativeQuery = true)
	public ItemCart getByCustomerAndItem(@Param("customer") int customer, @Param("item") int item);
	
	@Transactional
	@Modifying
	@Query(value = "insert into item_cart (customer_id, item_id, item_quantity) value(:customer, :item, :quantity)", nativeQuery = true)
	public void addToCard(@Param("customer") int customer, @Param("item") int item, @Param("quantity") int quantity);
	
	@Query(value = "select * from item_cart where customer_id = :id and order_detail_id is null", nativeQuery = true)
	public List<ItemCart> getCartsByCustomerId(@Param("id") int id);
	
	@Transactional
	@Modifying
	@Query(value = "delete from item_cart where customer_id =:customer and item_id = :item", nativeQuery = true)
	public void deleteFromCart(@Param("customer") int customer, @Param("item") int item);

	@Transactional
	@Modifying
	@Query(value = "delete from item_cart where customer_id =:customer and item_id = :item", nativeQuery = true)
	public void deleteCarts(@Param("customer") int customer, @Param("item") int item);
	
	@Transactional
	@Modifying
	@Query(value = "update item_cart set item_quantity = :quantity where customer_id = :customer and item_id = :item", nativeQuery = true)
	public void updateQuantity(@Param("customer") int customer, @Param("item") int item, @Param("quantity") int quantity);
	
	@Transactional
	@Modifying
	@Query(value = "update item_cart set order_detail_id = :order where cart_id = :id", nativeQuery = true)
	public void updateOrderId(@Param("id") int id, @Param("order") int order);
	
	@Transactional
	@Modifying
	@Query(value = "delete from item_cart where order_detail_id =:order", nativeQuery = true)
	public void deleteCartByOrderId(@Param("order") int customer);
	
	public List<ItemCart> findByOrderDetailOrderDetailId(int orderId);
}
