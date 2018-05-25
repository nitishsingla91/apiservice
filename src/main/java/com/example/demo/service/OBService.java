package com.example.demo.service;

import java.util.List;
import java.util.Map;


import com.example.demo.model.Bank;
import com.example.demo.model.Login;
import com.example.demo.model.response.AccountsResponse;
import com.example.demo.model.response.LoginResponse;




public interface OBService {

	LoginResponse authenticateAndGetToken(Login login);
	void save(Login customer);
	Login find(Long id);
	Map<Long, Login> findAll();
	void update(Login customer);
	Login getBanks(String username);
	AccountsResponse getAccounts(Long username);
	
}
