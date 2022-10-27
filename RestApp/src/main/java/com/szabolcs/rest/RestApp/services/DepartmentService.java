package com.szabolcs.rest.RestApp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.szabolcs.rest.RestApp.exceptions.DepartmentNotFoundException;
import com.szabolcs.rest.RestApp.exceptions.EmployeeNotFoundException;
import com.szabolcs.rest.RestApp.model.Department;
import com.szabolcs.rest.RestApp.model.Employee;
import com.szabolcs.rest.RestApp.repositories.DepartmentRepository;
import com.szabolcs.rest.RestApp.repositories.EmployeeRepository;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public Department findDepByName(String name) {
	Department department = departmentRepository.findByName(name);
	if (department == null) {
	    throw new DepartmentNotFoundException("Cannot find department with name: " + name);
	}
	return department;
    }

    public Department findDepByNameReturnsDepartment(String name) {
	Department department = departmentRepository.findByName(name);
	if (department == null) {
	    throw new DepartmentNotFoundException("Cannot find department with name: " + name);
	}
	return department;
    }
    
    public Department getById(Long id) {
	Department department = departmentRepository.findById(id).orElseThrow(()-> new DepartmentNotFoundException("Cannot find dep with id: " + id));
	return department;
    }

    public List<Department> getAllDepartments() {
	return departmentRepository.findAll();
    }

    public Department createDepartment(Department department) {
	return departmentRepository.save(department);
    }

    public Department addEmployeeToDeptById(Long employeeId, String departmentName) {
	Employee emp = employeeRepository.findById(employeeId)
		.orElseThrow(() -> new EmployeeNotFoundException("Cannot find employee in DB with id : " + employeeId));
	
	Department dep = this.findDepByNameReturnsDepartment(departmentName);	
	dep.addEmployeeToList(emp);
	return departmentRepository.save(dep);

    }

}
