package com.szabolcs.rest.RestApp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szabolcs.rest.RestApp.services.DepartmentService;
import com.szabolcs.rest.RestApp.services.EmployeeService;

@RestController
@RequestMapping("/api/dep")
public class DepartmentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentService depService;
    
    @Autowired
    private EmployeeService empService;
    
    



}
