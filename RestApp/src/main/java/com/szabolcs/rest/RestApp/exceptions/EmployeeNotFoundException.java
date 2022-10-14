package com.szabolcs.rest.RestApp.exceptions;

public class EmployeeNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EmployeeNotFoundException(String message) {
	super(message);
    }

    public EmployeeNotFoundException(String message, Throwable couse) {
	super(message, couse);
    }

}
