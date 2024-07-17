package com.mmtspl.employeeservice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

//import brave.sampler.Sampler;

//@ComponentScan({"com.mmtspl.employeeservice.*"})
//@ComponentScan(basePackages = {"com.mmtspl"})
@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
//@EnableDiscoveryClient
//@EnableFeignClients("com.mmtspl.employeeservice.mmtspl-employee-service")

public class MMTSPLEmployeeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MMTSPLEmployeeServiceApplication.class, args);
	}
	
/*
	//creating a bean
	@Bean  
	//creating a sampler called   
	public Sampler defaultSampler()  
	{  
		return Sampler.ALWAYS_SAMPLE;  
	} 
*/

}
