package com.example.restaurant_finder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository; 
import com.example.restaurant_finder.model.OrderDetail;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
	
	@Query(value = "select * from order_detail where customer_id = :id", nativeQuery = true)
	public List<OrderDetail> getOrdersByCustomerId(@Param("id") int id);

	public List<OrderDetail> findByCustomerCustomerId(int customerId);
	
	@Query(value = "select c.order_detail_id, c.cart_id, "
			+ "f.itemr_name, f.item_price, c.item_quantity "
			+ "from item_cart c "
			+ "join food_item f "
			+ "on c.item_id = f.item_id "
			+ "where c.order_detail_id = :id;", nativeQuery = true)
	public List<Object[]> getDeatilsByOrderId(@Param("id") int orderId);
}
