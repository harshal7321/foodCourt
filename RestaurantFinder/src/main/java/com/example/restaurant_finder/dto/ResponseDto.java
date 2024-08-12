package com.example.restaurant_finder.dto;

import org.springframework.stereotype.Component;

@Component
public class ResponseDto {
	
	private int status;
	private Object dto;
	private String message;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Object getDto() {
		return dto;
	}
	public void setDto(Object dto) {
		this.dto = dto;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
