package com.mmtspl.employeeservice.controller;

import java.util.List;
import javax.transaction.Transactional;

import com.mmtspl.employeeservice.model.*;
import com.mmtspl.employeeservice.service.AddressService;
import com.mmtspl.employeeservice.service.DepartmentService;
import com.mmtspl.employeeservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins = {"${settings.cors_origin}"})
@CrossOrigin(origins = {"${settings.cors_origin_localhost}", "${settings.cors_origin_localhost_global}", "${settings.cors_origin}"})
@RestController
//@RequestMapping(value="/restapiemployeeservices")
@RequestMapping(value = {"${rest.request.mapping}"} )
public class MMTSPLEmployeeServiceController {
	

	@Autowired
	private EmployeeService employeeService;
	

	//********************************* Employee_Master Start *********************************************//

	
	
	//********Feign call***********//
	
	
	//@Autowired
	//private CloudConfigDBServiceProxyNew proxy;  
	
	/*
	//mapping for currency-converter-feign service
	@GetMapping("/getMySQLBDUrlsProxy")
	//returns a bean 
	public MySQLBDUrls getMySQLBDUrlsProxy()
	//public String getMySQLBDUrlsProxy()
	{
		MySQLBDUrls mMySQLBDUrls = new MySQLBDUrls();
		if(proxy != null) {
			mMySQLBDUrls = proxy.getMySQLBDUrlsProxy();
			//logger.info("{}", response);
			if(mMySQLBDUrls != null) {
				System.out.println("Not null..." + mMySQLBDUrls);
				//mMySQLBDUrls = response.getBody();
				mMySQLBDUrls.setStatus(true);
			}else {
				System.out.println("null..." + mMySQLBDUrls);
				mMySQLBDUrls.setStatus(false);
			}
		}else {
			System.out.println("proxy is null...");
		}
		return mMySQLBDUrls;
	}
	*/
	
	
	
	//@GetMapping("/employeeservices")
	@GetMapping("${rest.get.mapping.employeeservices}")
	public String display() {
		return "Employee Services returns Hiiii...";
	}
	
	
	// ****************** Calling from FrontController ********************** //

	@Transactional
	//@GetMapping("/getAllEmployeeDetails")
	@GetMapping("${rest.get.mapping.getAllEmployeeDetails}")
	public List<EmployeeDetails> getAllEmployeeDetails() {
		return employeeService.getAllEmployeeDetails();
	}

	@Transactional
	//@GetMapping("/getAllEmployee")
	@GetMapping("${rest.get.mapping.getAllEmployee}")
	public List<Employee_Master> getAllEmployee() {
		return employeeService.getAllEmployee();
	}

	@Transactional
	@GetMapping("/getAddressByEmployeeID/{employeeId}")
	//@GetMapping("${rest.get.mapping.getEmployeeById}")
	public List<Address_Master> getAddressByEmployeeID(@PathVariable int employeeId) {
		return employeeService.getAddressByEmployeeID(employeeId);
	}

	
	
	
	
	
	@Transactional
	//@GetMapping("/getEmployeeById/{employeeId}")
	@GetMapping("${rest.get.mapping.getEmployeeById}")
	public Employee_Master getEmployeeById(@PathVariable int employeeId) {
		return employeeService.getEmployeeById(employeeId);
	}
	
	@Transactional
	//@PostMapping("/addEmployee")
	@PostMapping("${rest.post.mapping.addEmployee}")
	public Employee_Master addEmployee(@RequestBody Employee_Master employee) {
		return employeeService.addEmployee(employee);
	}

	@Transactional
	//@PutMapping("/updateEmployee")
	@PutMapping("${rest.put.mapping.updateEmployee}")
	public void updateEmployee(@RequestBody Employee_Master employee) {
		employeeService.updateEmployee(employee);
	}

	@Transactional
	//@PutMapping("/updateEmployeeById/{employeeId}")
	@PutMapping("${rest.put.mapping.updateEmployeeById}")
	public void updateEmployeeById(@RequestBody Employee_Master employee, @PathVariable int employeeId) {
		employeeService.updateEmployeeById(employee, employeeId);
	}

	
	@Transactional
	//@DeleteMapping("/deleteEmployee/{id}")
	//@GetMapping("/deleteEmployee/{employeeId}")
	@GetMapping("${rest.get.mapping.deleteEmployee}")
	public Boolean deleteEmployee(@PathVariable int employeeId) {
		return employeeService.deleteEmployee(employeeId);
	}

	// ****************** @NamedQueries ********************** //
	@Transactional
	//@GetMapping("/getEmployeeByName/{employeeName}")
	@GetMapping("${rest.get.mapping.getEmployeeByName}")
	public List<Employee_Master> getEmployeeByName(@PathVariable String employeeName) {
		System.out.print("\n Employee name : " + employeeName + "\n");
		return employeeService.getEmployeeByName(employeeName.trim());
	}
	
	
	// ****************** Calling from FrontController ********************** //
	
	
	
	
	
	
	
	

	@Transactional
	@PatchMapping("/updateEmployeeByAddress/{employeeId}")
	public void updateEmployeeByAddress(@RequestBody EmployeeAddress employeeAddress, int employeeId) {
		employeeService.updateEmployeeByAddress(employeeAddress, employeeId);

	}
	
	// ****************** @NamedQueries ********************** //

	@Transactional
	@GetMapping("/getEmpDeptDetailsByEmpId/{employeeID}")
	public List<Object[]> getEmpDeptDetailsByEmpId(@PathVariable int employeeID) {
		return employeeService.getEmpDeptDetailsByEmpId(employeeID);
	}

	
	//********************************* Employee_Master End *********************************************//

	
	//********************************* Department_Master Start *********************************************//
	
	@Autowired
	DepartmentService departmentService;
	
	@Transactional
	@GetMapping("/getAllDepartments")
	public List<Department_Master> getAllDepartments() {
		return departmentService.getAllDepartments();
	}

	@Transactional
	@GetMapping("/getDepartmentByID/{departmentId}")
	public Department_Master getDepartmentByID(@PathVariable int departmentId) {
		return departmentService.getDepartmentByID(departmentId);
	}
	
	// ****************** @NamedQueries ********************** //
	@Transactional
	@GetMapping("/getDepartmentByName/{deptName}")
	public List<Department_Master> getDepartmentByName(@PathVariable String deptName) {
		return departmentService.getDepartmentByName(deptName);
	}


	//********************************* Department_Master End *********************************************//
	
	//********************************* Address_Master Start *********************************************//
	
	@Autowired
	AddressService addressService;
	
	@Transactional
	@GetMapping("/getAllAddress")
	public List<Address_Master> getAllAddress() {
		return addressService.getAllAddress();
	}

	@Transactional
	@GetMapping("/getAddressByID/{AddressId}")
	public Address_Master getAddressByID(@PathVariable int AddressId) {
		return addressService.getAddressByID(AddressId);
	}
	
	// ****************** @NamedQueries ********************** //
	@Transactional
	@GetMapping("/getAddressByCity/{city}")
	public List<Address_Master> getAddressByCity(@PathVariable String city) {
		return addressService.getAddressByCity(city);
	}


	//********************************* Address_Master End *********************************************//
	
	
}
