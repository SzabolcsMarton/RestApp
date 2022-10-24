package com.szabolcs.rest.RestApp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Table(name = "roles")
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String role;

    @ManyToMany(mappedBy = "roles")
    private Set<Employee> employees = new HashSet<>();

    public Role() {
    }

    public Role(long id, String role, Set<Employee> employees) {
	this.id = id;
	this.role = role;
	this.employees = employees;
    }

    public long getId() {
	return id;
    }

    public String getRole() {
	return role;
    }

    public Set<Employee> getEmployees() {
	return employees;
    }

    public void setId(long id) {
	this.id = id;
    }

    public void setRole(String role) {
	this.role = role;
    }

    public void setEmployees(Set<Employee> employees) {
	this.employees = employees;
    }

}
