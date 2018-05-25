package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.model.Bank;
import com.example.demo.model.Login;
import com.example.demo.model.response.AccountsResponse;
import com.example.demo.model.response.LoginResponse;
import com.example.demo.service.OBService;



@RestController
@Path("/api")
public class OBController {

	@Autowired
	private OBService obService;
	
	@POST
	@Path("/logintest")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public LoginResponse login(Login login) {
		LoginResponse response= obService.authenticateAndGetToken(login);
		return response;
	}
	
	@GET
	@Path("/save")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String save() {
		// save a single Customer
		
		Bank bank1 = new Bank("openbank","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyIiOiIifQ.gObc89vJgFjFvlBItWW_AmpT66oNe2-VGoU8FKEf5jE");
		Bank bank2 = new Bank("erste","2edac21b-3df8-4895-83c0-30f702d52c02");
		
		List<Bank> banks = new ArrayList<Bank>();
		banks.add(bank1);
		banks.add(bank2);
		
		obService.save(new Login(1, "nsingla@gmail.com", "123456",banks));	
		obService.save(new Login(2, "openbankdemo@gmail.com", "123789",banks));
	
	
		//obService.saveUserBanks();
		//obService.saveUserBanks(new UserBanks("nsingla@gmail.com", "openbank","2edac21b-3df8-4895-83c0-30f702d52c02"));
		//obService.saveUserBanks(new UserBanks("nishu@gmail.com", "erste","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyIiOiIifQ.inGy_ccWxd5vketh5OezUQj9WNja4J-D4_p_m__vGII"));
 
		return "Done";
	}
 
	@RequestMapping("/findall")
	public String findAll() {
		String result = "";
		Map<Long, Login> customers = obService.findAll();
 
		for (Login customer : customers.values()) {
			result += customer.toString() + "<br>";
		}
 
		return result;
	}
	
	@RequestMapping("/find")
	public String findById(@RequestParam("id") Long id) {
		String result = "";
		result = obService.find(id).toString();
		return result;
	}
	
	@RequestMapping(value = "/uppercase")
	public String postCustomer(@RequestParam("id") Long id) {
		Login customer = obService.find(id);
		customer.setUserName(customer.getUserName().toUpperCase());
		customer.setPassword(customer.getPassword().toUpperCase());
 
		obService.update(customer);
 
		return "Done";
	}
	
	
	
	@GET
	@Path("/getAccounts")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public AccountsResponse getAccounts(@QueryParam("username") Long username) {
		
		
		
		AccountsResponse response= obService.getAccounts(username);
		
		return response;
	}
	
	
	@GET
	@Path("/test")
	public String test() {
		return "test";
	}
}
