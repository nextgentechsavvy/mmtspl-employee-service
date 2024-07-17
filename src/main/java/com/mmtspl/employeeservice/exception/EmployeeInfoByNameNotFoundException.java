package com.mmtspl.employeeservice.exception;


public class EmployeeInfoByNameNotFoundException extends RuntimeException {


	private static final long serialVersionUID = 1L;

	public EmployeeInfoByNameNotFoundException(String empname) {

		super(String.format("Employee with Name:-   %s   not found", empname)); // Displayed message on Console for reference
	}
}
