package com.szabolcs.rest.RestApp.exceptions;

public class RoleCanNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public RoleCanNotFoundException(String message) {
	super(message);
    }

    public RoleCanNotFoundException(String message, Throwable couse) {
	super(message, couse);
    }

}
