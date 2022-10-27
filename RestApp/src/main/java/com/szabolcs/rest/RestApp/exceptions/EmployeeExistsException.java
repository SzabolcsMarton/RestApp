package com.szabolcs.rest.RestApp.exceptions;

public class EmployeeExistsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EmployeeExistsException(String message) {
	super(message);
    }

    public EmployeeExistsException(String message, Throwable couse) {
	super(message, couse);
    }

}
