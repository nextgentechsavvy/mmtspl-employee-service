package com.mmtspl.employeeservice.exception;

public class AddressInfoByCityNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public AddressInfoByCityNotFoundException(String cityName) {
		super(String.format("Address Info with City:-   %s   not found", cityName)); // Displayed message on Console for reference
	}

}
