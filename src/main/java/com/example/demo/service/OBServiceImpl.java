package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.Bank;
import com.example.demo.model.Login;
import com.example.demo.service.banks.ErsteBank;
import com.example.demo.service.banks.OpenBank;
import com.example.demo.model.response.Account;
import com.example.demo.model.response.AccountsResponse;
import com.example.demo.model.response.LoginResponse;


@Service
public class OBServiceImpl implements OBService {

	private static final String LOGIN_KEY = "Login";
	
	
	private RedisTemplate<String, Object> redisTemplate;
	private HashOperations<String, Long, Login> hashOperations;
	
	@Autowired
	private OpenBank openBank;
	
	@Autowired
	private ErsteBank ersteBank;
	
	@Autowired
	private Environment env;
	
	private RestTemplate restTemplate = new RestTemplate();
 
	@Autowired
	public OBServiceImpl(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
 
	@PostConstruct
	private void init() {
		hashOperations = redisTemplate.opsForHash();
	
	}
 
	@Override
	public void save(Login customer) {
		hashOperations.put(LOGIN_KEY, customer.getId(), customer);
	}
	
	
	@Override
	public Login find(Long id) {
		return hashOperations.get(LOGIN_KEY, id);
	}
 
	@Override
	public Login getBanks(String username) {
		Login login = hashOperations.get(LOGIN_KEY, username);
		return login;
	}
 
	
	@Override
	public Map<Long, Login> findAll() {
		return hashOperations.entries(LOGIN_KEY);
	}
	
	@Override
	public void update(Login customer) {
		hashOperations.put(LOGIN_KEY, customer.getId(), customer);
	}
	
	@Override
	public LoginResponse authenticateAndGetToken(Login user) {
		
		Map<Long, Login> getusers = findAll();
		LoginResponse loginResponse= new LoginResponse();
		loginResponse.setStatus("fail");
		loginResponse.setStatusCode(1);
		
		for (Login userobj : getusers.values()) {
			
			if(userobj.getUserName().equalsIgnoreCase(user.getUserName()) && userobj.getPassword().equalsIgnoreCase(user.getPassword())){
				
				loginResponse.setStatus("success");
				loginResponse.setStatusCode(0);
				loginResponse.setToken(String.valueOf(userobj.getId()));
				
				return loginResponse;
			}
		}
		
	
		return loginResponse;
	}
	
	@Override
	public AccountsResponse getAccounts(Long id) {
		Login login = find(id);
		
		List<Account> accounts = new ArrayList();
		   
		List<Bank> getuserbanks = login.getBanks();
		
		for(Bank userbank : getuserbanks){
			
			if("openbank".equals(userbank.getBankId())){
				
				List<Account> account= openBank.getAccounts(userbank.getToken());
				
				accounts.addAll(account);
				
			}else if("erste".equals(userbank.getBankId())){
				
				List<Account> account= ersteBank.getAccounts(userbank.getToken());
				
				accounts.addAll(account);
				
			}
			
		}
		
	    
	    AccountsResponse accountResp = new AccountsResponse();  
	    
	    accountResp.setStatus("success");
	    accountResp.setStatusCode(0);
	    accountResp.setAccounts(accounts);
	    
		return accountResp;
	}

	
 
}
