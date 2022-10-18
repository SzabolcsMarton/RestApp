package com.szabolcs.rest.RestApp.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Employee> employees;

    public Department() {
    }

    public Department(Long id, String name, List<Employee> employees) {
	this.id = id;
	this.name = name;
	this.employees = employees;
    }

    public Long getId() {
	return id;
    }

    public String getName() {
	return name;
    }

    public List<Employee> getEmployees() {
	return employees;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public void setName(String name) {
	this.name = name;
    }

    public void setEmployees(List<Employee> employees) {
	this.employees = employees;
    }

    public void addEmployeeToList(Employee employee) {
	this.employees.add(employee);
    }

    public void removeEmployeeFromList(Employee employee) {
	this.employees.remove(employee);
    }

}
