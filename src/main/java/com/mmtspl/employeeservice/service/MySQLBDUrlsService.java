package com.mmtspl.employeeservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.mmtspl.employeeservice.model.MySQLBDUrls;


@Service("mySQLBDUrlsService")
public class MySQLBDUrlsService {

	Logger logger = LoggerFactory.getLogger(MySQLBDUrlsService.class);
	
	public MySQLBDUrls getMySQLBDUrls(String baseUrl, String port, String mySQLBDUrlsServiceUrl) {
		ResponseEntity<MySQLBDUrls> responseEntity = null;
		MySQLBDUrls mMySQLBDUrls = new MySQLBDUrls();
		
		logger.info("Formatted getMySQLBDUrls : "+ baseUrl + port + mySQLBDUrlsServiceUrl);
		
		try {
		    	RestTemplate restTemplate = new RestTemplate();
		        responseEntity = restTemplate.getForEntity(baseUrl + port + mySQLBDUrlsServiceUrl, MySQLBDUrls.class);
		    	//responseEntity = restTemplate.getForEntity("http://localhost:9008/cloud-config-db-service/mysql-db-cloud-urls", MySQLBDUrls.class);
		        //response = responseEntity.getBody();  
		        //System.out.println(responseEntity.getBody());
		        //System.out.println(response.getBaseUrl());
		}catch(Exception e) {
		    	e.printStackTrace();
		}finally {
		    	
		}
		if(responseEntity != null) {
			mMySQLBDUrls = responseEntity.getBody();
			mMySQLBDUrls.setStatus(true);
		}else {
			mMySQLBDUrls.setStatus(false);
		}
		
		return mMySQLBDUrls;
	}
	
	public MySQLBDUrls getMySQLBDUrlsZullAPIGateway(String zullAPIGatewayHTTP, String zullAPIGatewayIP,
			String zullAPIGatewayPort, String dbServiceApplicationName, String mySQLBDUrlsServiceUrl) {
		ResponseEntity<MySQLBDUrls> responseEntity = null;
		MySQLBDUrls mMySQLBDUrls = new MySQLBDUrls();
		
		logger.info("Formatted getMySQLBDUrlsZullAPIGateway : "+ zullAPIGatewayHTTP + "://" + zullAPIGatewayIP + ":" + 
					zullAPIGatewayPort + "/" + dbServiceApplicationName + mySQLBDUrlsServiceUrl);
		
		try {
		    	RestTemplate restTemplate = new RestTemplate();
		        responseEntity = restTemplate.getForEntity(zullAPIGatewayHTTP + "://" + zullAPIGatewayIP + ":" + 
						zullAPIGatewayPort + "/" + dbServiceApplicationName +  mySQLBDUrlsServiceUrl, MySQLBDUrls.class);
		}catch(Exception e) {
		    	e.printStackTrace();
		}finally {
		    	
		}
		if(responseEntity != null) {
			mMySQLBDUrls = responseEntity.getBody();
			mMySQLBDUrls.setStatus(true);
		}else {
			mMySQLBDUrls.setStatus(false);
		}
		
		return mMySQLBDUrls;
	}
}
