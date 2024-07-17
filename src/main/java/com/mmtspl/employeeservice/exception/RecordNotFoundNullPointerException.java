package com.mmtspl.employeeservice.exception;

public class RecordNotFoundNullPointerException extends NullPointerException{

	private static final long serialVersionUID = 1L;
	
	public RecordNotFoundNullPointerException() {
		 super("Record not found - return null ...."); // Displayed message on Console for reference
	}
	

}
