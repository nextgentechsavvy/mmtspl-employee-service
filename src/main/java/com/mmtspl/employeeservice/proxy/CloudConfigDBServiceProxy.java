package com.mmtspl.employeeservice.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import com.mmtspl.employeeservice.model.MySQLBDUrls;

//@Component
//@FeignClient(name="mmtspl-netflix-zuul-api-gateway-server")
//@RibbonClient(name="mmtspl-cloud-config-db-service")

@FeignClient(name="mmtspl-cloud-config-db-service", url="localhost:9008")  
public interface CloudConfigDBServiceProxy {

	//mapping for zuul-api-gateway-server
	//@GetMapping("/mmtspl-cloud-config-db-service/cloud-config-db-service/mysql-db-cloud-urls")
	//public MySQLBDUrls getMySQLBDUrlsProxy();
}
