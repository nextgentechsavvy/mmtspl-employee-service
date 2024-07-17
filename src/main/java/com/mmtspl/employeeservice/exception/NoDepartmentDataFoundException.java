package com.mmtspl.employeeservice.exception;


public class NoDepartmentDataFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoDepartmentDataFoundException() {

        super("No data found"); // Displayed message on Console for reference
    }
}
