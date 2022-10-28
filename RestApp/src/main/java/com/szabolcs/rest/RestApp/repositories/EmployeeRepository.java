package com.szabolcs.rest.RestApp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.szabolcs.rest.RestApp.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long >{
    
    Optional<Employee> findByEmail(String email);

}
