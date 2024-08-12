package com.example.restaurant_finder.serviceImp;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.restaurant_finder.dto.CustomerLogin;
import com.example.restaurant_finder.model.Customer;
import com.example.restaurant_finder.repository.CustomerRepository;
import com.example.restaurant_finder.service.CustomerService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class CustomerServiceImp implements CustomerService {
	
	@Autowired
	private CustomerRepository repository;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private Customer customer;
	
	public ResponseEntity<CustomerLogin> getCustomerByCusomerEmail(CustomerLogin login) {
		
		try {
			customer = repository.getCustomerByCusomerEmail(login.getCustomerEmail());
			HttpSession session = request.getSession(true);
			
			if (session.getAttribute("customer") == null) {
				
				if (login.getCustomerPassword().equals(customer.getCustomerPassword())) {
					
					System.out.println("Session ----------------------------------------------\n"+session);
					session.setAttribute("customer", customer.getCustomerName());
					String status = "success";
					CustomerLogin result = new CustomerLogin(customer.getCustomerId(), customer.getCustomerName(), customer.getCustomerEmail(), customer.getCustomerContact(), customer.getCustomerLocation(), status);
					return ResponseEntity.ok(result);
				} 
				else {
					String status = "failed";
					CustomerLogin result = new CustomerLogin(login.getCustomerId(), login.getCustomerName(), login.getCustomerEmail(), login.getCustomerContact(), login.getCustomerLocation(), status);
					return ResponseEntity.status(200).body(result);
				}
			} 
			else { 
				String status = "alreadyLogin";
				CustomerLogin result = new CustomerLogin(customer.getCustomerId(), customer.getCustomerName(), customer.getCustomerEmail(), customer.getCustomerContact(), customer.getCustomerLocation(), status);
				return ResponseEntity.status(200).body(result);
			}  
		} 
		catch (Exception e) { 
			String status = "failed";
			CustomerLogin result = new CustomerLogin(login.getCustomerId(), login.getCustomerName(), login.getCustomerEmail(), login.getCustomerContact(), login.getCustomerLocation(), status);
			return ResponseEntity.status(200).body(result);
		}
	}
	
	public ResponseEntity<String> logoutCustomer(CustomerLogin login) {
		
		HttpSession session = request.getSession();
		
		if(session != null) {
			
			customer.remove();
			session.invalidate();
			System.out.println("Customer ----------------------------------------------\n"+customer.toString());
			return ResponseEntity.status(200).body("Logout successfully");
		}
		else {
			System.out.println("Session ----------------------------------------------\n"+session);
			return ResponseEntity.status(440).body("Session Expired !");
		}
		
	}

	public ResponseEntity<CustomerLogin> addCustomer(CustomerLogin register) {
		
		customer = repository.getCustomerByCusomerEmail(register.getCustomerEmail());
		
		if(customer == null) {
			LocalDateTime registereAt = LocalDateTime.now();
			
			Customer customer = new Customer(register.getCustomerName(), register.getCustomerEmail(), register.getCustomerContact(), register.getCustomerLocation(), register.getCustomerPassword(), registereAt);
			try {
				repository.save(customer);
				CustomerLogin result = new CustomerLogin(customer.getCustomerId(), customer.getCustomerName(), customer.getCustomerEmail(), customer.getCustomerContact(), customer.getCustomerLocation(), "success");
				return ResponseEntity.ok(result);
			} 
			catch (Exception e) {
				return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
			}
		}
		else {
			
			register.setStatus("exist");
			return ResponseEntity.ok(register);
		}
		
	}
 
	@Override
	public CustomerLogin getPassword(CustomerLogin customer) {
		  
		Customer data = repository.findByCustomerId(customer.getCustomerId());
		
		if(data.getCustomerPassword().equals(customer.getCustomerPassword())) {
			
			customer.setStatus("success");
			return customer;
		}
		else {
			customer.setStatus("fail");
			return customer;
		} 
	}

	@Override
	public ResponseEntity<String> updatePassword(CustomerLogin customer) {
		
		LocalDateTime updatedAt = LocalDateTime.now();
		Customer data = repository.findByCustomerId(customer.getCustomerId());
		 
		repository.updatePassword(data.getCustomerId(), customer.getCustomerPassword(), updatedAt);;
		return ResponseEntity.ok("Password Updated Successfully");
	}

	@Override
	public CustomerLogin updateProfile(CustomerLogin customer) { 
		
		LocalDateTime updatedAt = LocalDateTime.now();
		Customer data = repository.findByCustomerId(customer.getCustomerId());
		 
		repository.updateProfile(data.getCustomerId(), customer.getCustomerName(), customer.getCustomerEmail(), customer.getCustomerContact(), customer.getCustomerLocation(), updatedAt);
		 
		return customer;
	}
}
