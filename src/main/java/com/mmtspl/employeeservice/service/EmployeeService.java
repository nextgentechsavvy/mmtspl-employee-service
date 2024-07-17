package com.mmtspl.employeeservice.service;

import java.util.ArrayList;
import java.util.List;

import com.mmtspl.employeeservice.exception.EmployeeInfoByIDNotFoundException;
import com.mmtspl.employeeservice.exception.EmployeeInfoByNameNotFoundException;
import com.mmtspl.employeeservice.exception.NoEmployeeDataFoundException;
import com.mmtspl.employeeservice.model.*;
import com.mmtspl.employeeservice.repository.EmployeeRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service("employeeService")
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	private Boolean matched = false;
	private List<EmployeeDetails> employeeDetailsList;
	
	Logger logger = LoggerFactory.getLogger(EmployeeService.class);
	
	
	// ****************** Calling from FrontController ********************** //

	public List<EmployeeDetails> getAllEmployeeDetails() {
		employeeDetailsList = new ArrayList<EmployeeDetails>();
		List<Employee_Master> employeeList = employeeRepository.getAllEmployee();

		if(employeeList.isEmpty())
			throw new NoEmployeeDataFoundException();
		else{
			employeeDetailsList = copyEmployeeListToEmployeeDetails(employeeList);
			List<Address_Master> addressList = null;
			String strArress = "";

			if(employeeDetailsList.isEmpty())
				throw new NoEmployeeDataFoundException();
			else {
				List<Integer> employeeIDList = getEmployeeIDListFromAddress();
				for(EmployeeDetails empDetails : employeeDetailsList) {
					if(empDetails != null) {

						boolean bVal = employeeIDList.stream().anyMatch((n -> n.equals(empDetails.getEmployeeId())));
						//logger.info("---bVal----"+ bVal);
						if(bVal) {
							addressList = getAddressByEmployeeID(empDetails.getEmployeeId()); // Address List taken for multiple address of an Employee
							if(addressList != null) {
								logger.info("Address ID ------- : "+ addressList.get(0).getAddressId());

								strArress = addressList.get(0).getAddressType() + ": " +
										addressList.get(0).getLocality()+ " " + addressList.get(0).getCity()+ " " + addressList.get(0).getCountry()+ " " +
										addressList.get(0).getState()+ " " + addressList.get(0).getZipcode()+"\n";
								if(addressList.size()>1) {
									strArress = strArress + addressList.get(1).getAddressType() + ": " +
											addressList.get(1).getLocality()+ " " + addressList.get(1).getCity()+ " " + addressList.get(1).getCountry()+ " " +
											addressList.get(1).getState()+ " " + addressList.get(1).getZipcode();

								}
								empDetails.setEmployeeAddress(strArress);
							}
						}


					}
				}
			}
		}

		return employeeDetailsList;
	}

	private List<EmployeeDetails> copyEmployeeListToEmployeeDetails(List<Employee_Master> employeeList){
		List<EmployeeDetails> employeeDetailsList = new ArrayList<EmployeeDetails>();
		EmployeeDetails empDetails = null;

		for(Employee_Master empMaster : employeeList) {
			empDetails = new EmployeeDetails();
			empDetails.setEmployeeId(empMaster.getEmployeeId());
			empDetails.setEmployeeName(empMaster.getEmployeeName());
			empDetails.setEmployeeSalary(empMaster.getEmployeeSalary());
			empDetails.setEmployeeAge(empMaster.getEmployeeAge());
			empDetails.setEmployeeDesignation(empMaster.getEmployeeDesignation());
			empDetails.setEmployeeAddress(empMaster.getEmployeeAddress());
			employeeDetailsList.add(empDetails);
		}
		return employeeDetailsList;
	}

	public List<Integer> getEmployeeIDListFromAddress() {
		ResponseEntity<List<Integer>> responseEntity = null;
		List<Integer> employeeIDList = null;
		
		try {
		    	RestTemplate restTemplate = new RestTemplate();
		    	//responseEntity = restTemplate.exchange("http://localhost:8765/mmtspl-address-service/restapiaddressservices/getAllEmployeeID", HttpMethod.GET,
		    	//		  null, new ParameterizedTypeReference<List<Integer>>(){});
				responseEntity = restTemplate.exchange("http://localhost:9002/restapiaddressservices/getAllEmployeeID", HttpMethod.GET,
			 			  null, new ParameterizedTypeReference<List<Integer>>(){});
		}catch(Exception e) {
		    	e.printStackTrace();
		}finally {
		    	
		}
		if(responseEntity != null) {
				employeeIDList = responseEntity.getBody();
		}else {
			
		}
		return employeeIDList;
	}
	
	
	public List<Address_Master> getAddressByEmployeeID(int employeeId){
		ResponseEntity<List<Address_Master>> responseEntity = null;
		List<Address_Master> addressList=null;
		try {
				logger.info("Matched Employee ID-------: "+ employeeId);
				RestTemplate restTemplate = new RestTemplate();
		    	responseEntity = restTemplate.exchange("http://localhost:9002/restapiaddressservices/getAddressByEmployeeID/"+employeeId, HttpMethod.GET,
		    			  null, new ParameterizedTypeReference<List<Address_Master>>(){});
		}catch(Exception e) {
		    	e.printStackTrace();
		}finally {
		    	
		}
		if(responseEntity != null) {
			addressList = responseEntity.getBody();
		}else {
			
		}
		
		
		return addressList;
	}




	public List<Employee_Master> getAllEmployee() {
		List<Employee_Master> employeeList = employeeRepository.getAllEmployee();
		return employeeList;
	}


		public Employee_Master getEmployeeById(int employeeId) {
		
		//return employeeRepository.getEmployee(id).orElseThrow(() -> new EmployeeNotFoundException(id));;
		Employee_Master employee = employeeRepository.getEmployeeById(employeeId);
			if(employee == null) throw new EmployeeInfoByIDNotFoundException(employeeId);
		return employee;
	}
	
	public Employee_Master addEmployee(Employee_Master employee) {
		return employeeRepository.addEmployee(employee);
	}
	
	public void updateEmployee(Employee_Master employee) {
		employeeRepository.updateEmployee(employee);
	}
	
	public void updateEmployeeById(Employee_Master employee, int employeeId) {
		employeeRepository.updateEmployeeById(employee, employeeId);
	}

	public Boolean deleteEmployee(int employeeId) {
		matched = employeeRepository.deleteEmployee(employeeId);
		if(matched == false) throw new EmployeeInfoByIDNotFoundException(employeeId);
		return matched;
	}
	
	
	// ****************** @NamedQueries ********************** //
	public List<Employee_Master> getEmployeeByName(String employeeName) {
		
		//return employeeRepository.getEmployee(id).orElseThrow(() -> new EmployeeNotFoundException(id));;
		List<Employee_Master> employeeList = employeeRepository.getEmployeeByName(employeeName);
			if(employeeList == null) throw new EmployeeInfoByNameNotFoundException(employeeName);
		return employeeList;
	}
	
	// ****************** Calling from FrontController ********************** //
	
	
	public void updateEmployeeByAddress(EmployeeAddress employeeAddress, int employeeId) {
		employeeRepository.updateEmployeeByAddress(employeeAddress, employeeId);

	}

	public String getSubscriptionMessage(String user) {
		return "Hello "+user+", Thanks for the subscription!";
	}

	
	// ****************** @NamedQueries ********************** //
	
	public List<Object[]> getEmpDeptDetailsByEmpId(int employeeId) {
		
		//return employeeRepository.getEmployee(id).orElseThrow(() -> new EmployeeNotFoundException(id));;
		List<Object[]> employeeList = employeeRepository.getEmpDeptDetailsByEmpId(employeeId);
			if(employeeList == null) throw new EmployeeInfoByIDNotFoundException(employeeId);
		return employeeList;
	}
	
	
	

}
