package com.mmtspl.employeeservice.exception;



public class EmployeeInfoByIDNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmployeeInfoByIDNotFoundException(int id) {

		super(String.format("Employee with Id %d not found", id)); // Displayed message on Console for reference
	}
}
