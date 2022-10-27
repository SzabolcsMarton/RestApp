package com.szabolcs.rest.RestApp.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.szabolcs.rest.RestApp.exceptions.DepartmentNotFoundException;
import com.szabolcs.rest.RestApp.exceptions.EmployeeNotFoundException;
import com.szabolcs.rest.RestApp.model.Department;
import com.szabolcs.rest.RestApp.model.Employee;
import com.szabolcs.rest.RestApp.model.Role;
import com.szabolcs.rest.RestApp.repositories.DepartmentRepository;
import com.szabolcs.rest.RestApp.repositories.EmployeeRepository;
import com.szabolcs.rest.RestApp.repositories.RoleRepository;

@Service
public class ManagerService {
    
    private static final int BASE_SALARY = 20000;

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    
    public Employee activateBasicEmployee(Long employeeId,Long departmentId) {
	Employee emp = employeeRepository.findById(employeeId).orElseThrow(()-> new EmployeeNotFoundException("Cannot find employee with id: " + employeeId));
	Department dep = departmentRepository.findById(departmentId).orElseThrow(()-> new DepartmentNotFoundException("Cannot find department with id: " + departmentId));	
	emp.setDepartment(dep);
	emp.setSalary(BASE_SALARY);

	emp.setActivationDate(LocalDate.now());
	return employeeRepository.save(emp);
	
    }
    
    public Employee addRoleToEmployee(Long employeeId, String roleName) {
	Employee emp = employeeRepository.findById(employeeId).orElseThrow(()-> new EmployeeNotFoundException("Cannot find employee with id: " + employeeId));
	Role role = roleRepository.findByRole(roleName.toUpperCase());
	emp.addRole(role);
	return employeeRepository.save(emp);
	
    }
    
    public Employee removeRoleFromEmployee(Long employeeId, String roleName) {
 	Employee emp = employeeRepository.findById(employeeId).orElseThrow(()-> new EmployeeNotFoundException("Cannot find employee with id: " + employeeId));
 	Role role = roleRepository.findByRole(roleName.toUpperCase());
 	emp.removeRole(role);
 	return employeeRepository.save(emp);
     }
    
    
    
    public boolean deleteEmployee(Long empId) {
	if(employeeRepository.existsById(empId)) {
	    employeeRepository.deleteById(empId);
	    return true;
	}
	return false;
    }
}
