package com.szabolcs.rest.RestApp.exceptions;

public class DepartmentNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DepartmentNotFoundException(String message) {
	super(message);
    }

    public DepartmentNotFoundException(String message, Throwable couse) {
	super(message, couse);
    }

}
