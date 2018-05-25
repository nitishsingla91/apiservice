package com.example.demo.model;

import java.io.Serializable;
import java.util.List;


public class Login implements Serializable {
	 
	private static final long serialVersionUID = 1L;
 
	private long id;
	private String userName;
	private String password;
	private List<Bank> banks;
 
	protected Login() {
	}
 
	public long getId() {
		return id;
	}
 
	public void setId(long id) {
		this.id = id;
	}
 
	public String getUserName() {
		return userName;
	}
 
	public void setUserName(String userName) {
		this.userName = userName;
	}
 
	public String getPassword() {
		return password;
	}
 
	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<Bank> getBanks() {
		return banks;
	}

	public void setBanks(List<Bank> banks) {
		this.banks = banks;
	}
 
	public Login(long id, String userName, String password,List<Bank> banks) {
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.banks = banks;
	}
 
	
	@Override
	public String toString() {
		return String.format("Login[id=%d, firstName='%s', lastName='%s']", id, userName, password);
	}
}