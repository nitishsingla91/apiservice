package com.example.demo.service.banks.openbankmodels;



public class BankVO {
	private String id;
	private String short_name;
	private String full_name;
	private BankRouting bank_routing;
	
	
	public BankRouting getBank_routing() {
		return bank_routing;
	}
	public void setBank_routing(BankRouting bank_routing) {
		this.bank_routing = bank_routing;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getShort_name() {
		return short_name;
	}
	public void setShort_name(String short_name) {
		this.short_name = short_name;
	}
	public String getFull_name() {
		return full_name;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	@Override
	public String toString() {
		return "BankVO [id=" + id + ", short_name=" + short_name
				+ ", full_name=" + full_name + ", bank_routing=" + bank_routing
				+ "]";
	}
	
}
