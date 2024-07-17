package com.mmtspl.employeeservice.exception;

public class NoAddressDataFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoAddressDataFoundException() {

        super("No data found"); // Displayed message on Console for reference
    }
}