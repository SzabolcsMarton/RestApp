package com.szabolcs.rest.RestApp.controller;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.szabolcs.rest.RestApp.model.Role;
import com.szabolcs.rest.RestApp.services.DepartmentService;
import com.szabolcs.rest.RestApp.services.EmployeeService;
import com.szabolcs.rest.RestApp.services.RoleService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private RoleService roleService;
    
    @Autowired
    private DepartmentService departmentService;


    
    @GetMapping("/createrole/{roleName}")
    public ResponseEntity<Role> createRole(@PathVariable String roleName) {
	Role role = roleService.createRole(roleName);
	URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
		.buildAndExpand(role.getId()).toUri();
	LOGGER.info("Save employee in database with  the generated id: " + role.getId());
	return ResponseEntity.created(location).build();
    }
    
    



}
