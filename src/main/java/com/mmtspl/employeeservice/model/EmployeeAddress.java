package com.mmtspl.employeeservice.model;

public class EmployeeAddress {
	
	private int employeeId;
	private String employeeAddress;
	
	public EmployeeAddress(int employeeId, String employeeAddress) {
		this.employeeId = employeeId;
		this.employeeAddress = employeeAddress;
	}
	
	
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeAddress() {
		return employeeAddress;
	}
	public void setEmployeeAddress(String employeeAddress) {
		this.employeeAddress = employeeAddress;
	}

}
