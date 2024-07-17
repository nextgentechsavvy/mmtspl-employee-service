package com.mmtspl.employeeservice.exception;

public class AddressInfoByIDNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AddressInfoByIDNotFoundException(int departmentId) {

		super(String.format("Department with Id %d not found", departmentId)); // Displayed message on Console for reference
	}
}