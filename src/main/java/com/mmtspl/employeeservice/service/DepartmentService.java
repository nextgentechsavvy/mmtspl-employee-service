package com.mmtspl.employeeservice.service;

import java.util.List;

import javax.transaction.Transactional;

import com.mmtspl.employeeservice.exception.DepartmentInfoByIDNotFoundException;
import com.mmtspl.employeeservice.exception.DepartmentInfoByNameNotFoundException;
import com.mmtspl.employeeservice.exception.EmployeeInfoByIDNotFoundException;
import com.mmtspl.employeeservice.exception.EmployeeInfoByNameNotFoundException;
import com.mmtspl.employeeservice.exception.NoDepartmentDataFoundException;
import com.mmtspl.employeeservice.exception.NoEmployeeDataFoundException;
import com.mmtspl.employeeservice.model.Department_Master;
import com.mmtspl.employeeservice.model.Employee_Master;
import com.mmtspl.employeeservice.repository.DepartmentRepository;
import com.mmtspl.employeeservice.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("departmentService")
public class DepartmentService {

	@Autowired
	DepartmentRepository departmentRepository;
	
	@Transactional
	public List<Department_Master> getAllDepartments() {
		List<Department_Master> departmetList = departmentRepository.getAllDepartments(); 
		
		if(departmetList.isEmpty())
			 throw new NoDepartmentDataFoundException();
		
		return departmetList;
	}
	
	@Transactional
	public Department_Master getDepartmentByID(int departmentId) {
		
		//return employeeRepository.getEmployee(id).orElseThrow(() -> new EmployeeNotFoundException(id));;
		Department_Master department = departmentRepository.getDepartmentByID(departmentId);
			if(department == null) throw new DepartmentInfoByIDNotFoundException(departmentId);
		return department;
	}
	
	
	// ****************** @NamedQueries ********************** //
	@Transactional
	public List<Department_Master> getDepartmentByName(String departmentName) {
		
		//return employeeRepository.getEmployee(id).orElseThrow(() -> new EmployeeNotFoundException(id));;
		List<Department_Master> departmetList = departmentRepository.getDepartmentByName(departmentName);
			if(departmetList == null) throw new DepartmentInfoByNameNotFoundException(departmentName);
		return departmetList;
	}


/*
	@Transactional
	public Employee_Master getEmployee(int id) {
		
		//return employeeRepository.getEmployee(id).orElseThrow(() -> new EmployeeNotFoundException(id));;
		Employee_Master employee = employeeRepository.getEmployee(id);
			if(employee == null) throw new EmployeeNotFoundException(id);
		return employee;
	}
	
	@Transactional
	public List<Employee_Master> getEmployeeByName(String employeeName) {
		
		//return employeeRepository.getEmployee(id).orElseThrow(() -> new EmployeeNotFoundException(id));;
		List<Employee_Master> employeeList = employeeRepository.getEmployeeByName(employeeName);
			if(employeeList == null) throw new EmployeeNotFoundByNameException(employeeName);
		return employeeList;
	}

	@Transactional
	public void addEmployee(Employee_Master employee) {
		employeeRepository.addEmployee(employee);
	}

	@Transactional
	public void updateEmployee(Employee_Master employee) {
		employeeRepository.updateEmployee(employee);

	}

	@Transactional
	public String deleteEmployee(int id) {
		return employeeRepository.deleteEmployee(id);
	}
	
	
	public String getSubscriptionMessage(String user) {
		
		return "Hello "+user+", Thanks for the subscription!";
	}
	*/
}
