package com.example.demo.model.response;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import org.springframework.stereotype.Component;

@Component
public class AccountsResponse {
	@XmlElement(name="status_code")
	private int statusCode;
	private String status;
	private List<Account> accounts;
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
	public List<Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	
}
