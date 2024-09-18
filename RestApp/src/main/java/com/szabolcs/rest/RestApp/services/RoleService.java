package com.szabolcs.rest.RestApp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.szabolcs.rest.RestApp.exceptions.RoleCanNotFoundException;
import com.szabolcs.rest.RestApp.model.Role;
import com.szabolcs.rest.RestApp.repositories.RoleRepository;

@Service
public class RoleService {
    
    @Autowired
    private RoleRepository roleRepository;
    
    public Role getRoleById(Long id) {
	return roleRepository.findById(id).orElseThrow(()-> new RoleCanNotFoundException("Cannot find role with id : " + id));
    }
    
    public Role getRoleByRole(String role) {
	return roleRepository.findByRole(role);
    }
    
    public List<Role> getAllRoles(){
	return roleRepository.findAll();
    }
    
    public Role createRole(String role) {
	Role roleToSave = new Role(role);
	return roleRepository.save(roleToSave);
    }
    
    public boolean deleteRole(Long id) {
	if (roleRepository.existsById(id)) {
	    roleRepository.deleteById(id);
	    return true;
	}
	return false;
    }

    
}
