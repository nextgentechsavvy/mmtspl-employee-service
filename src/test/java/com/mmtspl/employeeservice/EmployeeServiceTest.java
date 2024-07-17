package com.mmtspl.employeeservice;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.mock.web.MockHttpServletResponse;
import com.mmtspl.employeeservice.exception.EmployeeInfoByIDNotFoundException;
import com.mmtspl.employeeservice.repository.EmployeeRepository;
import com.mmtspl.employeeservice.service.EmployeeService;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


//@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
//@WebMvcTest(value = RestSBHMySQLController.class)
//@WithMockUser
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc //need this in Spring Boot test

public class EmployeeServiceTest {

	@Autowired
	private MockMvc mockMvc;
	
	//@MockBean
	//private EmployeeService employeeService;
	
	@Autowired
	EmployeeService employeeService = new EmployeeService();
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	private Boolean matched = false;
	
	@Test
	//@DisplayName("Subscription message service test ")
	public void testSubscriptionMessage() {
		String user = "Mithun";
		String message;
		message = employeeService.getSubscriptionMessage(user);
		assertEquals("Hello "+user+", Thanks for the subscription!", message);
	}

	
	@Test
	public void addEmployeeTest() throws Exception{
		RequestBuilder requestBuilder;
		MvcResult result;
		//Employee_Master employee_master_record_mock = new Employee_Master(1000,"Mithun Majumdar", "150000.0", 42, "Tech Lead", "Pune,MH-12,India");
		//Employee_Master employee_master_record = new Employee_Master(3000,"Mithran Majumdar", "200000.0", 20, "Sr. Tech Lead", "Mumbai,MH-01,India");

		String expectedResultJson = "{\"employeeId\":1060,\"employeeName\":\"Test-Employee\",\"employeeSalary\":\"200000.0\",\"employeeAge\":20,\"employeeDesignation\":\"Sr. Tech Lead\",\"employeeAddress\":\"Mumbai,MH-01,India\"}";		 
		 
		//Mockito.when(employeeService.addEmployee(Mockito.anyString(),Mockito.any(Employee_Master.class))).thenReturn(employee_master_record);
		//Mockito.when(employeeService.addEmployee(Mockito.any(Employee_Master.class))).thenReturn(employee_master_record_mock);
		
		requestBuilder = MockMvcRequestBuilders.get("/restapiemployeeservices/getEmployeeById/1060").accept(MediaType.APPLICATION_JSON);
		result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println("\n getEmployeeTest Response :: " + result.getResponse().getContentAsString() +"\n");
		 	
	 	JSONAssert.assertEquals(expectedResultJson, result.getResponse().getContentAsString(), false);

		if(expectedResultJson.equals(result.getResponse().getContentAsString())) {
	 		matched = true;
	 		System.out.println("Employee ID exists in Employee_Master Table.....");
	 	}else {
			requestBuilder = MockMvcRequestBuilders.post("/restapiemployeeservices/addEmployee").accept(MediaType.APPLICATION_JSON).content(expectedResultJson)
					.contentType(MediaType.APPLICATION_JSON);

			result = mockMvc.perform(requestBuilder).andReturn();
			System.out.println("addEmployeeTest Response :: " + result.getResponse().getContentAsString());
			
			MockHttpServletResponse response = result.getResponse();
			System.out.println("addEmployeeTest Response Status :: " + result.getResponse());
			assertEquals(HttpStatus.OK.value(), response.getStatus());
	
			//assertEquals("http://localhost:8000/restapiservices/getEmployee/3000",response.getHeader(HttpHeaders.LOCATION));		 
	 	}
	}

	
	@Test
	public void getEmployeeTest() throws EmployeeInfoByIDNotFoundException{
		
		RequestBuilder requestBuilder;
		MvcResult result;
		//Employee_Master employee_master_record_mock = new Employee_Master(1000,"Mithun Majumdar", "150000.0", 42, "Tech Lead", "Pune,MH-12,India");
		String expected = "{\"employeeId\":1060,\"employeeName\":\"Test-Employee\",\"employeeSalary\":\"150000.0\",\"employeeAge\":42,\"employeeDesignation\":\"Tech Lead\",\"employeeAddress\":\"Pune,MH-12,India\"}";
		
		try {
				//Mockito.when(employeeService.getEmployee(Mockito.anyInt())).thenReturn(employee_master_record_mock);
				
				requestBuilder = MockMvcRequestBuilders.get("/restapiemployeeservices/getEmployeeById/1060").accept(MediaType.APPLICATION_JSON);
				result = mockMvc.perform(requestBuilder).andReturn();
				System.out.println("\n getEmployeeTest Response :: " + result.getResponse().getContentAsString() +"\n");
				 	
			 	JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

			 	if(expected.equals(result.getResponse().getContentAsString())) {
			 		matched = true;
			 	}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("matched :: " + matched);
	}
	 

	 
	 
	/* 
	@Test
	public void deleteEmployeeTest() {
		RequestBuilder requestBuilder;
		MvcResult result;
		
		try {
				//Mockito.when(employeeService.getEmployee(Mockito.anyInt())).thenReturn(employee_master_record_mock);
				
				requestBuilder = MockMvcRequestBuilders.delete("/restapiservices/deleteEmployee/2001").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
				result = mockMvc.perform(requestBuilder).andReturn();
				System.out.println(" Delete Response :: " + result.getResponse().getContentAsString());
				 	
			 	//JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	 
	/* @Test
		 public void updateEmployeeTest() {
			 mockMvc.perform(put("/heavyresource/1")
					  .contentType(MediaType.APPLICATION_JSON_VALUE)
					  .content(objectMapper.writeValueAsString(
					    new Employee_Master(1, "Tom", "Jackson", 12, "heaven street")))
					  ).andExpect(status().isOk());
		 }
	 */
	 

	 
	 
}