package com.szabolcs.rest.RestApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private RoleRepository repository;
    
    
    
    
}
