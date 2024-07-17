package com.mmtspl.employeeservice.service;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmtspl.employeeservice.exception.AddressInfoByCityNotFoundException;
import com.mmtspl.employeeservice.exception.AddressInfoByIDNotFoundException;
import com.mmtspl.employeeservice.exception.NoAddressDataFoundException;
import com.mmtspl.employeeservice.model.Address_Master;
import com.mmtspl.employeeservice.repository.AddressRepository;

@Service("AddressService")
public class AddressService {
	@Autowired
	AddressRepository addressRepository;
	
	@Transactional
	public List<Address_Master> getAllAddress() {
		List<Address_Master> addressList = addressRepository.getAllAddress(); 
		
		if(addressList.isEmpty())
			 throw new NoAddressDataFoundException();
		
		return addressList;
	}
	
	@Transactional
	public Address_Master getAddressByID(int addressId) {
		
		//return employeeRepository.getEmployee(id).orElseThrow(() -> new EmployeeNotFoundException(id));;
		Address_Master address = addressRepository.getAddressByID(addressId);
			if(address == null) throw new AddressInfoByIDNotFoundException(addressId);
		return address;
	}
	
	
	// ****************** @NamedQueries ********************** //
	@Transactional
	public List<Address_Master> getAddressByCity(String city) {
		
		//return employeeRepository.getEmployee(id).orElseThrow(() -> new EmployeeNotFoundException(id));;
		List<Address_Master> addressList = addressRepository.getAddressByCity(city);
			if(addressList == null) throw new AddressInfoByCityNotFoundException(city);
		return addressList;
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
