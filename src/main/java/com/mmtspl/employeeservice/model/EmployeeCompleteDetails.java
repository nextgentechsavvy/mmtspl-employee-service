package com.mmtspl.employeeservice.model;

public class EmployeeCompleteDetails {

	private int employeeId;
	private String employeeName; 
	private String employeeSalary;
	private int employeeAge;
	private String employeeDesignation;
	private String employeeAddress;
	private String employeeDepartmentName;
	private String employeeDepartmentLocation;

	
	
	public EmployeeCompleteDetails() {
		super();
	}
	
	public EmployeeCompleteDetails(int employeeId, String employeeName, String employeeSalary, int employeeAge, String employeeDesignation, String employeeAddress, String employeeDepartmentName, String employeeDepartmentLocation) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.employeeSalary = employeeSalary;
		this.employeeAge = employeeAge;
		this.employeeDesignation = employeeDesignation;
		this.employeeAddress = employeeAddress;
		this.employeeDepartmentName = employeeDepartmentName;
		this.employeeDepartmentLocation = employeeDepartmentLocation;
	}
	
	
	
	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeSalary() {
		return employeeSalary;
	}

	public void setEmployeeSalary(String employeeSalary) {
		this.employeeSalary = employeeSalary;
	}
	
	public int getEmployeeAge() {
		return employeeAge;
	}

	public void setEmployeeAge(int employeeAge) {
		this.employeeAge = employeeAge;
	}

	public String getEmployeeDesignation() {
		return employeeDesignation;
	}

	public void setEmployeeDesignation(String employeeDesignation) {
		this.employeeDesignation = employeeDesignation;
	}

	public String getEmployeeAddress() {
		return employeeAddress;
	}

	public void setEmployeeAddress(String employeeAddress) {
		this.employeeAddress = employeeAddress;
	}

	public String getEmployeeDepartmentName() {
		return employeeDepartmentName;
	}

	public void setEmployeeDepartmentName(String employeeDepartmentName) {
		this.employeeDepartmentName = employeeDepartmentName;
	}

	public String getEmployeeDepartmentLocation() {
		return employeeDepartmentLocation;
	}

	public void setEmployeeDepartmentLocation(String employeeDepartmentLocation) {
		this.employeeDepartmentLocation = employeeDepartmentLocation;
	}



}
