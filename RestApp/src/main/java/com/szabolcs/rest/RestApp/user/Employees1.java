package com.szabolcs.rest.RestApp.user;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Employees1 {

    private ArrayList<Employee1> employee = new ArrayList<>();

    @JsonProperty("Employee")
    public ArrayList<Employee1> getEmployee() {
	return this.employee;
    }

    public void setEmployee(ArrayList<Employee1> employee) {
	this.employee = employee;
    }

}
