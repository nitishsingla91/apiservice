package com.example.demo.model.response;

import javax.xml.bind.annotation.XmlElement;

import org.springframework.stereotype.Component;


@Component
public class LoginResponse {
	@XmlElement(name="status_code")
	private int statusCode;
	private String status;
	private String token;
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}