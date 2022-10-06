package com.szabolcs.rest.RestApp.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Employee1 {

    private int age;
    private String name;
    private String gender;
    private String role;

    public Employee1() {
    }

    public Employee1(int age, String name, String gender, String role) {
	this.age = age;
	this.name = name;
	this.gender = gender;
	this.role = role;
    }
    @JsonProperty("age")
    public int getAge() {
	return age;
    }
    @JsonProperty("name")
    public String getName() {
	return name;
    }
    @JsonProperty("gender")
    public String getGender() {
	return gender;
    }
    @JsonProperty("role")
    public String getRole() {
	return role;
    }

    public void setAge(int age) {
	this.age = age;
    }

    public void setName(String name) {
	this.name = name;
    }

    public void setGender(String gender) {
	this.gender = gender;
    }

    public void setRole(String role) {
	this.role = role;
    }

}
