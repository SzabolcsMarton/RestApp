package com.szabolcs.rest.RestApp.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityEmployee implements UserDetails{
   
    private static final long serialVersionUID = 1L;
    
    private Employee employee;
    
    public SecurityEmployee(Employee employee) {
	this.employee = employee;
    }
      
    
   @Override
    public String getUsername() {
	return employee.getEmail();
    }
    
     @Override
    public String getPassword() {
	return employee.getPassword();
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
	List<GrantedAuthority> authorities = new ArrayList<>();
	Set<Role> roles =  employee.getRoles();
	    for (Role actual: roles) {
	        authorities.add(new SimpleGrantedAuthority(actual.getRole()));
	        
	    }	    
	    return authorities;	
    }

    @Override
    public boolean isAccountNonExpired() {
	return true;
    }

    @Override
    public boolean isAccountNonLocked() {
	return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
	return true;
    }

    @Override
    public boolean isEnabled() {
	return true;
    }

}
