package com.example.demo.service.banks.openbankmodels;

import java.util.List;

import org.springframework.stereotype.Component;



@Component
public class AccountVO {
	private String id;
	private String bank_id;
	private List<Owner> owners;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBank_id() {
		return bank_id;
	}
	public void setBank_id(String bank_id) {
		this.bank_id = bank_id;
	}
	
	public List<Owner> getOwners() {
		return owners;
	}
	public void setOwners(List<Owner> owners) {
		this.owners = owners;
	}
	
}
