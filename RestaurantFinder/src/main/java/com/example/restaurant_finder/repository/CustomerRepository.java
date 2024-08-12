package com.example.restaurant_finder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.restaurant_finder.model.Customer;

import jakarta.transaction.Transactional;

import java.time.LocalDateTime; 


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
	public Customer findByCustomerId(int customerId);
	
	@Query("from Customer where customerEmail = :email")
	public Customer getCustomerByCusomerEmail(@Param("email") String email);
	
	@Transactional
	@Modifying
	@Query(value = "update customer set customer_name = :name, customer_email = :email, customer_contact = :contact, customer_location = :location, updated_at = :date where customer_id = :id", nativeQuery = true)
	public void updateProfile(@Param("id") int id, @Param("name") String name, @Param("email") String email, @Param("contact") String contact, @Param("location") String location, @Param("date") LocalDateTime date);
	
	@Transactional
	@Modifying
	@Query(value = "update customer set customer_password = :password, updated_at = :date where customer_id = :id", nativeQuery = true)
	public void updatePassword(@Param("id") int id, @Param("password") String password, @Param("date") LocalDateTime date);
}
