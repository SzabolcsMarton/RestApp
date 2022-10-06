package com.szabolcs.rest.RestApp.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Root {

    private Employees1 employees = new Employees1();

    @JsonProperty("Employees")
    public Employees1 getEmployees() {
	return this.employees;
    }

    public void setEmployees(Employees1 employees) {
	this.employees = employees;
    }
    
    public void addEmployee(Employee1 employee1) {
	employees.getEmployee().add(employee1);
    }

}
