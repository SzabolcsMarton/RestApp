package com.szabolcs.rest.RestApp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.szabolcs.rest.RestApp.exceptions.EmployeeExistsException;
import com.szabolcs.rest.RestApp.exceptions.EmployeeNotFoundException;
import com.szabolcs.rest.RestApp.model.Department;
import com.szabolcs.rest.RestApp.model.Employee;
import com.szabolcs.rest.RestApp.model.Role;
import com.szabolcs.rest.RestApp.repositories.EmployeeRepository;

@Service
public class EmployeeService {
    
    private final String EMPLOYEE_ROLE = "EMPLOYEE";
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private EmployeeRepository repository;
    
    @Autowired
    private RoleService roleService;
    
    public List<Employee> getAllEmployees(){
	return repository.findAll();
    }

    public Employee getOneEmployeeById(Long id) {
	return repository.findById(id).orElseThrow(()-> new EmployeeNotFoundException("Cannot find employee in DB with id : " + id));
    }
    
    public Employee createEmployee(Employee employee) {
	return repository.save(employee);
    }
    
    public boolean deleteEmployeeById(Long id) {
	if (repository.existsById(id)) {
	    repository.deleteById(id);
	    return true;
	}
	return false;
    }
    
    public Employee updateEmployeeById(Long id, Employee employee) {
	Employee empToUpdate = repository.findById(id).orElseThrow(()-> new EmployeeNotFoundException("Cannot find employee in DB with id : " + id));
	empToUpdate.setName(employee.getName());
	empToUpdate.setSalary(employee.getSalary());
	empToUpdate.setEmail(employee.getEmail());
	empToUpdate.setDepartment(employee.getDepartment());
	return repository.save(empToUpdate);
	
    }
    
    public Employee addDepartmentToEmployee(Long empId, Department department) {
	Employee emp =  repository.findById(empId).orElseThrow(()-> new EmployeeNotFoundException("Cannot find employee in DB with id : " + empId));
	emp.setDepartment(department);
	return this.updateEmployeeById(empId, emp);
	
    }
    
    public Employee registerEmployee(Employee employeeToRegister) {
	Optional<Employee> checkEmployee = repository.findByEmail(employeeToRegister.getEmail());
	if(checkEmployee.isPresent()) {
	    throw new EmployeeExistsException("Employee email is already registered: "+ checkEmployee.get().getEmail());
	}
	Role role = roleService.getRoleByRole(EMPLOYEE_ROLE);
	Employee empToReg = new Employee();
	empToReg.setName(employeeToRegister.getName());
	empToReg.setEmail(employeeToRegister.getEmail());
	empToReg.setPassword(employeeToRegister.getPassword());
	empToReg.setPassword(passwordEncoder.encode(employeeToRegister.getPassword()));
	empToReg.addRole(role);
	
	return repository.save(empToReg);
	
    }
    
    
    
    
}
