package com.example.demo.service.banks.openbankmodels;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class BanksVO {
	private List<BankVO> banks;
	@XmlElement(name="status_code")
	private int statusCode;
	private String status;

	public List<BankVO> getBanks() {
		return banks;
	}

	public void setBanks(List<BankVO> banks) {
		this.banks = banks;
	}

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
	
}
