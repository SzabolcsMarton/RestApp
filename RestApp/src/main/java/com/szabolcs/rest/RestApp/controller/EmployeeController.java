package com.szabolcs.rest.RestApp.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.szabolcs.rest.RestApp.model.Department;
import com.szabolcs.rest.RestApp.model.Employee;
import com.szabolcs.rest.RestApp.services.DepartmentService;
import com.szabolcs.rest.RestApp.services.EmployeeService;

@RestController
@RequestMapping("/api/emp")
public class EmployeeController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService service;
    
    @Autowired
    private DepartmentService depService;

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
	LOGGER.info("Get all employees from the database");
	List<Employee> emps = service.getAllEmployees();
	return ResponseEntity.ok(emps);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getOneEmployeeById(@PathVariable Long id) {
	LOGGER.info("Get one employee by id: " + id);
	Employee emp = service.getOneEmployeeById(id);
	return ResponseEntity.ok(emp);
    }

    @PostMapping("/employees")
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
	Employee savedEmployee = service.createEmployee(employee);
	URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
		.buildAndExpand(savedEmployee.getId()).toUri();
	LOGGER.info("Save employee in database with  the generated id: " + savedEmployee.getId());
	return ResponseEntity.created(location).build();
    }
    
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Employee> deleteEmployeeById(@PathVariable Long id){
	LOGGER.info("Delete employee from database with the id: " + id);
	boolean success = service.deleteEmployeeById(id);
	return success ?  ResponseEntity.ok().build() :  ResponseEntity.badRequest().build();

    }
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployeeById(@PathVariable Long id, @RequestBody Employee employee){
	LOGGER.info("Update employee in database with the id: " + id);
	Employee emp = service.updateEmployeeById(id, employee);
	return ResponseEntity.ok(emp);
    }
    
    
    @GetMapping("/employees/{empId}/{depName}")
    public ResponseEntity<Department> addDepToEmp(@PathVariable String depName, @PathVariable Long empId) {
	LOGGER.info("Add department : " +  depName + " to Employee with id:  "+ empId);
	Department dep = depService.findDepByNameReturnsDepartment(depName);
	service.addDepartmentToEmployee(empId, dep);
	Department depWithEmpl = depService.addEmployeeToDeptById(empId, depName.toLowerCase());
	return ResponseEntity.ok(depWithEmpl);

    }
    
  

}
