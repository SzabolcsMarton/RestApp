package com.szabolcs.rest.RestApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.szabolcs.rest.RestApp.model.Department;
import com.szabolcs.rest.RestApp.model.Role;
import com.szabolcs.rest.RestApp.repositories.DepartmentRepository;
import com.szabolcs.rest.RestApp.repositories.EmployeeRepository;
import com.szabolcs.rest.RestApp.repositories.RoleRepository;

@Service
public class AdminService {
   
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private DepartmentRepository departmentRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    public Role createNewRole(Role role) {
	return roleRepository.save(role);
    }
    
    public Department createNewDepartment(Department dep) {
	return departmentRepository.save(dep);
    }
    
    
}
