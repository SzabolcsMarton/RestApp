package com.szabolcs.rest.RestApp.controller;

import java.net.URI;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.szabolcs.rest.RestApp.model.Employee;
import com.szabolcs.rest.RestApp.services.EmployeeService;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    private EmployeeService employeeService;
    
    @PostMapping("/emp")
    public ResponseEntity<Employee> registerEmployee(@Valid @RequestBody Employee employee) {
	Employee savedEmployee = employeeService.registerEmployee(employee);
	URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
		.buildAndExpand(savedEmployee.getId()).toUri();
	LOGGER.info("Registered employee in database with the generated id: " + savedEmployee.getId());
	return ResponseEntity.created(location).build();
    }
    


}
