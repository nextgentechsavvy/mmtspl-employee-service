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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service("employeeService")
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;
	
	Boolean matched = true;
	List<EmployeeCompleteDetails> employeeDetailsList;
	
	Logger logger = LoggerFactory.getLogger(EmployeeService.class);

	//------------ Get All Employee Details from Employee DB, Address DB and Department DB --------------------------//
	@Value("${rest.get.url.employeeIDListFromAddress}")
	String employeeIDListFromAddress;

	@Value("${rest.get.url.addressByEmployeeID}")
	String addressByEmployeeID;

	@Value("${rest.get.url.allDepartmentEmployeeID}")
	String allDepartmentEmployeeID;

	@Value("${rest.get.url.departmentByEmployeeID}")
	String departmentByEmployeeID;
	//------------ Get All Employee Details from Employee DB, Address DB and Department DB --------------------------//


	// ****************** Calling from FrontController ********************** //

	public List<Employee_Master> getAllEmployee() {
		List<Employee_Master> employeeList = new ArrayList<>();
		try{
			 employeeList = employeeRepository.getAllEmployee();
		}catch(Exception e){
			e.printStackTrace();
		}
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
		if(!matched)
			throw new EmployeeInfoByIDNotFoundException(employeeId);

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


	//------------ Get All Employee Details from Employee DB, Address DB and Department DB --------------------------//

	public List<EmployeeCompleteDetails> getEmployeeCompleteDetailsList() {
		employeeDetailsList = new ArrayList<>();
		List<Employee_Master> employeeList = employeeRepository.getAllEmployee();

		if(employeeList.isEmpty())
			throw new NoEmployeeDataFoundException();
		else{
			employeeDetailsList = copyEmployeeListToEmployeeDetails(employeeList);
			List<Address_Master> addressList;
			List<Department_Master> departmentList;
			String strAddress;

			if(employeeDetailsList.isEmpty())
				throw new NoEmployeeDataFoundException();
			else {
				//Add Address Details to Employee Details List from Address_Master Database
				List<Integer> employeeIDList = getEmployeeIDListFromAddress();
				for(EmployeeCompleteDetails empDetails : employeeDetailsList) {
					if(empDetails != null) {

						boolean bVal = employeeIDList.stream().anyMatch((n -> n.equals(empDetails.getEmployeeId())));
						//logger.info("---bVal----"+ bVal);
						if(bVal) {
							addressList = getAddressByEmployeeID(empDetails.getEmployeeId()); // Address List taken for multiple address of an Employee
							if(addressList != null) {
								logger.info("Address ID ------- : "+ addressList.get(0).getAddressId());

								strAddress = addressList.get(0).getAddressType() + ": " +
										addressList.get(0).getLocality()+ " " + addressList.get(0).getCity()+ " " + addressList.get(0).getCountry()+ " " +
										addressList.get(0).getState()+ " " + addressList.get(0).getZipcode()+"\n";
								if(addressList.size()>1) {
									strAddress = strAddress + addressList.get(1).getAddressType() + ": " +
											addressList.get(1).getLocality()+ " " + addressList.get(1).getCity()+ " " + addressList.get(1).getCountry()+ " " +
											addressList.get(1).getState()+ " " + addressList.get(1).getZipcode();

								}
								empDetails.setEmployeeAddress(strAddress);
							}
						}
					}
				}

				//Add Department Details to Employee Details List from Department_Master Database
				employeeIDList = getAllDepartmentEmployeeID();
				for(EmployeeCompleteDetails empDetails : employeeDetailsList) {
					if(empDetails != null) {

						boolean bVal = employeeIDList.stream().anyMatch((n -> n.equals(empDetails.getEmployeeId())));
						//logger.info("---bVal----"+ bVal);
						if(bVal) {
							departmentList = getDepartmentByEmployeeID(empDetails.getEmployeeId()); // Department List taken for multiple address of an Employee
							if(departmentList != null) {
								logger.info("Department ID ------- : "+ departmentList.get(0).getDepartmentId());
								empDetails.setEmployeeDepartmentName(departmentList.get(0).getDepartmentName());
								empDetails.setEmployeeDepartmentLocation(departmentList.get(0).getDepartmentLocation());
							}
						}
					}
				}
			}
		}

		return employeeDetailsList;
	}

	private List<EmployeeCompleteDetails> copyEmployeeListToEmployeeDetails(List<Employee_Master> employeeList){
		List<EmployeeCompleteDetails> employeeDetailsList = new ArrayList<>();
		EmployeeCompleteDetails empDetails;

		for(Employee_Master empMaster : employeeList) {
			empDetails = new EmployeeCompleteDetails();
			empDetails.setEmployeeId(empMaster.getEmployeeId());
			empDetails.setEmployeeName(empMaster.getEmployeeName());
			empDetails.setEmployeeSalary(empMaster.getEmployeeSalary());
			empDetails.setEmployeeAge(empMaster.getEmployeeAge());
			empDetails.setEmployeeDesignation(empMaster.getEmployeeDesignation());
			empDetails.setEmployeeAddress(empMaster.getEmployeeAddress());
			empDetails.setEmployeeDepartmentName("");
			empDetails.setEmployeeDepartmentLocation("");

			employeeDetailsList.add(empDetails);
		}
		return employeeDetailsList;
	}

	public List<Integer> getEmployeeIDListFromAddress() {
		ResponseEntity<List<Integer>> responseEntity = null;
		List<Integer> employeeIDList = null;

		try {
			RestTemplate restTemplate = new RestTemplate();
			//responseEntity = restTemplate.exchange("http://localhost:9002/restapiaddressservices/getAllAddressEmployeeID", HttpMethod.GET,
			//		null, new ParameterizedTypeReference<List<Integer>>(){});
			responseEntity = restTemplate.exchange(employeeIDListFromAddress, HttpMethod.GET,
					null, new ParameterizedTypeReference<List<Integer>>(){});
		}catch(Exception e) {
			e.printStackTrace();
		}

		if(responseEntity != null) {
			employeeIDList = responseEntity.getBody();
		}
		return employeeIDList;
	}

	public List<Address_Master> getAddressByEmployeeID(int employeeId){
		ResponseEntity<List<Address_Master>> responseEntity = null;
		List<Address_Master> addressList=null;
		try {
			logger.info("Matched Employee ID-------: "+ employeeId);
			RestTemplate restTemplate = new RestTemplate();
			//responseEntity = restTemplate.exchange("http://localhost:9002/restapiaddressservices/getAddressByEmployeeID/"+employeeId, HttpMethod.GET,
			//		null, new ParameterizedTypeReference<List<Address_Master>>(){});
			responseEntity = restTemplate.exchange(addressByEmployeeID +employeeId, HttpMethod.GET,
					null, new ParameterizedTypeReference<List<Address_Master>>(){});
		}catch(Exception e) {
			e.printStackTrace();
		}
		if(responseEntity != null) {
			addressList = responseEntity.getBody();
		}
		return addressList;
	}


	public List<Integer> getAllDepartmentEmployeeID() {
		ResponseEntity<List<Integer>> responseEntity = null;
		List<Integer> employeeIDList = null;

		try {
			RestTemplate restTemplate = new RestTemplate();
			//responseEntity = restTemplate.exchange("http://localhost:9001/restapidepartmentservices/getAllDepartmentEmployeeID", HttpMethod.GET,
			//		null, new ParameterizedTypeReference<List<Integer>>(){});
			responseEntity = restTemplate.exchange(allDepartmentEmployeeID, HttpMethod.GET,
					null, new ParameterizedTypeReference<List<Integer>>(){});
		}catch(Exception e) {
			e.printStackTrace();
		}

		if(responseEntity != null) {
			employeeIDList = responseEntity.getBody();
		}
		return employeeIDList;
	}

	public List<Department_Master> getDepartmentByEmployeeID(int employeeId){
		ResponseEntity<List<Department_Master>> responseEntity = null;
		List<Department_Master> departmentList=null;
		try {
			logger.info("Matched Employee ID-------: "+ employeeId);
			RestTemplate restTemplate = new RestTemplate();
			//responseEntity = restTemplate.exchange("http://localhost:9001/restapidepartmentservices/getDepartmentByEmployeeID/"+employeeId, HttpMethod.GET,
			//		null, new ParameterizedTypeReference<List<Department_Master>>(){});
			responseEntity = restTemplate.exchange(departmentByEmployeeID +employeeId, HttpMethod.GET,
					null, new ParameterizedTypeReference<List<Department_Master>>(){});
		}catch(Exception e) {
			e.printStackTrace();
		}
		if(responseEntity != null) {
			departmentList = responseEntity.getBody();
		}
		return departmentList;
	}


	//------------ Get All Employee Details from Employee DB, Address DB and Department DB --------------------------//

}
