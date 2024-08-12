package com.example.restaurant_finder.dto;

public class CustomerLogin {
	
	private int customerId; 
	
	private String customerName;
	 
	private String customerEmail;
	 
	private String customerContact;
	
	private String customerLocation;
	 
	private String customerPassword;

	private String status;

	public CustomerLogin(int customerId, String customerName, String customerEmail, String customerContact,
			String customerLocation, String customerPassword, String status) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.customerContact = customerContact;
		this.customerLocation = customerLocation;
		this.customerPassword = customerPassword;
		this.status = status;
	}

	public CustomerLogin(int customerId, String customerName, String customerEmail, String customerContact,
			String customerLocation, String status) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.customerContact = customerContact;
		this.customerLocation = customerLocation;
		this.status = status;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerContact() {
		return customerContact;
	}

	public void setCustomerContact(String customerContact) {
		this.customerContact = customerContact;
	}

	public CustomerLogin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getCustomerLocation() {
		return customerLocation;
	}

	public void setCustomerLocation(String customerLocation) {
		this.customerLocation = customerLocation;
	}

	public String getCustomerPassword() {
		return customerPassword;
	}

	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	 
}
