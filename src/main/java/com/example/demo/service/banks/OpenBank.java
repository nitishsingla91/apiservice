package com.example.demo.service.banks;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import com.example.demo.model.response.Account;
import com.example.demo.service.banks.openbankmodels.AccountVO;
import com.example.demo.service.banks.openbankmodels.AccountsVO;
import com.example.demo.service.banks.openbankmodels.BankVO;
import com.example.demo.service.banks.openbankmodels.BanksVO;

@Service
public class OpenBank {
	
	@Autowired
	private Environment env;

	private RestTemplate restTemplate = new RestTemplate();
	
	
	public List<Account> getAccounts(String token) {
		
		String baseURI = env.getProperty("baseOpenBankURI");
		String accountsURI = env.getProperty("getOBAccounts");
		String getBanks = env.getProperty("getOBBanks");
		HttpHeaders headers = createHeader(token);
	    HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
	     
	    ResponseEntity<AccountsVO> resp = restTemplate.exchange(baseURI.concat(accountsURI), HttpMethod.GET, entity, AccountsVO.class);
	    AccountsVO accountsVO = resp.getBody();
	    
	    ResponseEntity<BanksVO> resp1 = restTemplate.exchange(baseURI.concat(getBanks), HttpMethod.GET, entity, BanksVO.class);
	    BanksVO banks = resp1.getBody();
	     
	    List<Account> accounts = new ArrayList();
	    
	   
	    for(AccountVO accountVO: accountsVO.getAccounts()){
	    	Account account = new Account();
	    	account.setAccount_no(accountVO.getId());
	    	account.setBank_id(accountVO.getBank_id());
	    	account.setBank_name(getBankName(accountVO.getBank_id(),banks));
	    	accounts.add(account);
	    }
		return accounts;
	}
	
	private HttpHeaders createHeader(String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String authorization = "DirectLogin token="+'"'+token+'"';
		headers.set("Authorization", authorization);
		return headers;
	}
	
	private String getBankName(String bank_id, BanksVO banks) {
		String bankName= null;
		for(BankVO bankVO: banks.getBanks()){
			if(bankVO.getId().equals(bank_id)){
				bankName = bankVO.getShort_name();
				break;
			}
		}
		return bankName;
	}
}
