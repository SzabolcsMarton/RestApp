package com.szabolcs.rest.RestApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.szabolcs.rest.RestApp.exceptions.DepartmentNotFoundException;
import com.szabolcs.rest.RestApp.exceptions.EmployeeNotFoundException;
import com.szabolcs.rest.RestApp.model.Department;
import com.szabolcs.rest.RestApp.model.Employee;
import com.szabolcs.rest.RestApp.repositories.DepartmentRepository;
import com.szabolcs.rest.RestApp.repositories.EmployeeRepository;
import com.szabolcs.rest.RestApp.repositories.RoleRepository;

@Service
public class ManagerService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    
    public Employee setUpBasicEmployee(Long employeeId,Long departmentId, int salary ) {
	Employee emp = employeeRepository.findById(employeeId).orElseThrow(()-> new EmployeeNotFoundException("Cannot find employee with id: " + employeeId));
	Department dep = departmentRepository.findById(departmentId).orElseThrow(()-> new DepartmentNotFoundException("Cannot find department with id: " + departmentId));
	emp.setDepartment(dep);
	emp.setSalary(salary);
	return employeeRepository.save(emp);
	
    }
}
