package com.example.demo.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class Bank implements Serializable {

	private String bankId;
	private String token;
	
	protected Bank() {
	}
	
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	public Bank(String bankId, String token) {
		this.bankId = bankId;
		this.token = token;
	}
	
}
