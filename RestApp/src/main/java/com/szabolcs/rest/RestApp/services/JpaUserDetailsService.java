package com.szabolcs.rest.RestApp.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.szabolcs.rest.RestApp.model.SecurityEmployee;
import com.szabolcs.rest.RestApp.repositories.EmployeeRepository;

@Service
public class JpaUserDetailsService implements UserDetailsService{
    
    private final EmployeeRepository employeeRepository;
    
    public JpaUserDetailsService(EmployeeRepository employeeRepository) {
	this.employeeRepository = employeeRepository;
    }
    

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
	return employeeRepository
		.findByEmail(email)
		.map(SecurityEmployee::new)
		.orElseThrow(()-> new UsernameNotFoundException("Cannot find Employee with email: " + email));
    }

}
