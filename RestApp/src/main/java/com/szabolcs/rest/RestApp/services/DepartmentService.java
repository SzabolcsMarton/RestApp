package com.szabolcs.rest.RestApp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.szabolcs.rest.RestApp.exceptions.DepartmentNotFoundException;
import com.szabolcs.rest.RestApp.model.Department;
import com.szabolcs.rest.RestApp.repositories.DepartmentRepository;

@Service
public class DepartmentService {
    
    @Autowired
    private DepartmentRepository departmentRepository;
    
    public ResponseEntity<Department> findDepByName(String name) {
	Department department = departmentRepository.findByName(name);
	if (department == null) {
	    throw new DepartmentNotFoundException("Cannot find department with name: " + name);
	}
	return ResponseEntity.ok(department);
    }
    
    public ResponseEntity<List<Department>> getAllDepartments(){
	return ResponseEntity.ok(departmentRepository.findAll());
    }
    
    public ResponseEntity<Department> createDepartment(Department department){
	return ResponseEntity.ok(departmentRepository.save(department));
    }

}
