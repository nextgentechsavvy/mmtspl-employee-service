package com.mmtspl.employeeservice.exception;


public class DepartmentInfoByNameNotFoundException extends RuntimeException {


	private static final long serialVersionUID = 1L;

	public DepartmentInfoByNameNotFoundException(String departmentName) {

		super(String.format("Department Info with Name:-   %s   not found", departmentName)); // Displayed message on Console for reference
	}
}
