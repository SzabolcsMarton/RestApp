package com.szabolcs.rest.RestApp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.szabolcs.rest.RestApp.exceptions.EmployeeNotFoundException;
import com.szabolcs.rest.RestApp.model.Department;
import com.szabolcs.rest.RestApp.model.Employee;
import com.szabolcs.rest.RestApp.repositories.EmployeeRepository;

@Service
public class EmployeeService {
    
    @Autowired
    private EmployeeRepository repository;
    
    public List<Employee> getAllEmployees(){
	return repository.findAll();
    }

    public Employee getOneEmployeeById(Long id) {
	return repository.findById(id).orElseThrow(()-> new EmployeeNotFoundException("Cannot find employee in DB with id : " + id));
    }
    
    public Employee createEmployee(Employee employee) {
	return repository.save(employee);
    }
    
    public boolean deleteEmployeeById(Long id) {
	if (repository.existsById(id)) {
	    repository.deleteById(id);
	    return true;
	}
	return false;
    }
    
    public Employee updateEmployeeById(Long id, Employee employee) {
	Employee empToUpdate = repository.findById(id).orElseThrow(()-> new EmployeeNotFoundException("Cannot find employee in DB with id : " + id));
	empToUpdate.setName(employee.getName());
	empToUpdate.setSalary(employee.getSalary());
	empToUpdate.setEmail(employee.getEmail());
	empToUpdate.setDepartment(employee.getDepartment());
	return repository.save(empToUpdate);
	
    }
    
    public Employee addDepartmentToEmployee(Long empId, Department department) {
	Employee emp =  repository.findById(empId).orElseThrow(()-> new EmployeeNotFoundException("Cannot find employee in DB with id : " + empId));
	emp.setDepartment(department);
	return this.updateEmployeeById(empId, emp);
	
    }
    
    
    
    
}
