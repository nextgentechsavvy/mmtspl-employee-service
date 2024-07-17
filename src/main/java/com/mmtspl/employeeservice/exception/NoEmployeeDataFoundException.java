package com.mmtspl.employeeservice.exception;


public class NoEmployeeDataFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoEmployeeDataFoundException() {

        super("No data found"); // Displayed message on Console for reference
    }
}
