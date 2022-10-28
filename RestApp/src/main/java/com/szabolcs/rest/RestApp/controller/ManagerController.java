package com.szabolcs.rest.RestApp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.szabolcs.rest.RestApp.model.Department;
import com.szabolcs.rest.RestApp.model.Employee;
import com.szabolcs.rest.RestApp.model.Role;
import com.szabolcs.rest.RestApp.services.DepartmentService;
import com.szabolcs.rest.RestApp.services.ManagerService;
import com.szabolcs.rest.RestApp.services.RoleService;

@Controller
@RequestMapping("/api/manager")
public class ManagerController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ManagerController.class);
    
    @Autowired
    private ManagerService managerService;
    
    @Autowired
    private DepartmentService departmentService;
    
    @Autowired
    private RoleService roleService;
    
    @GetMapping("/activate/{employeeId}/{departmentId}")
    public ResponseEntity<Employee> activateEmployee(@PathVariable Long employeeId, @PathVariable Long departmentId) {
	LOGGER.info("Activate employee with id: " + employeeId);
	Department dep = departmentService.getById(departmentId);
	departmentService.addEmployeeToDeptById(employeeId, dep.getName());
	Employee activatedEmp = managerService.activateBasicEmployee(employeeId, departmentId);
	return ResponseEntity.ok(activatedEmp);
    }
    
    
    @GetMapping("/addRole/{employeeId}/{roleId}")
    public ResponseEntity<Employee> getOneEmployeeById(@PathVariable Long employeeId, @PathVariable Long roleId) {
	Role role = roleService.getRoleById(roleId);
   	LOGGER.info("Add role \"" + role.getRole() + "\" to employee with id: " + employeeId);
   	Employee emp = managerService.addRoleToEmployee(employeeId, role);
   	return ResponseEntity.ok(emp);
       }

}
