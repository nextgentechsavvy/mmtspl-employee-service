package com.mmtspl.employeeservice.exception;



public class DepartmentInfoByIDNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DepartmentInfoByIDNotFoundException(int departmentId) {

		super(String.format("Department with Id %d not found", departmentId)); // Displayed message on Console for reference
	}
}
