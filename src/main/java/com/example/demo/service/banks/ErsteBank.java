package com.example.demo.service.banks;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.service.banks.erstemodels.Accounts;
import com.example.demo.model.response.Account;
import com.example.demo.service.banks.erstemodels.AccountEG;

@Service
public class ErsteBank {

	@Autowired
	private Environment env;

	
	
	public List<Account> getAccounts(String token) {
		
		String baseURI = env.getProperty("baseErsteURI");
		String accountsURI = env.getProperty("getErsteAccounts");
		
		
		HttpHeaders headers = createHeader(token);
	    HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
	     
	     RestTemplate restTemplate = null;
		try {
			restTemplate = restTemplate();
		} catch (KeyManagementException | KeyStoreException | NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    ResponseEntity<AccountEG> resp = restTemplate.exchange(baseURI.concat(accountsURI), HttpMethod.GET, entity, AccountEG.class);
	    AccountEG accountsVO = resp.getBody();
	    
	    List<Account> accounts = new ArrayList();
	    
	    
	    for(Accounts accountVO : accountsVO.getAccounts()){
	    	Account account = new Account();
	    	account.setAccount_no(accountVO.getIdentification().getOther());
	    	account.setBank_id(accountVO.getServicer().getBankCode());
	    	account.setBank_name(accountVO.getServicer().getBic());
	    	accounts.add(account);
	    }
		return accounts;
	}
	
	private HttpHeaders createHeader(String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		//String authorization = "DirectLogin token="+'"'+token+'"';
		headers.set("Authorization", "Bearer AbCdEf123456");
		headers.set("WEB-API-key", token);
		return headers;
	}
	
	
	@Bean
	public RestTemplate restTemplate() 
	                throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
		
	    TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
	 
	    SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
	                    .loadTrustMaterial(null, acceptingTrustStrategy)
	                    .build();
	 
	    SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
	 
	    CloseableHttpClient httpClient = HttpClients.custom()
	                    .setSSLSocketFactory(csf)
	                    .build();
	 
	    HttpComponentsClientHttpRequestFactory requestFactory =
	                    new HttpComponentsClientHttpRequestFactory();
	 
	    requestFactory.setHttpClient(httpClient);
	    RestTemplate restTemplate = new RestTemplate(requestFactory);
	    return restTemplate;
	 }
}
