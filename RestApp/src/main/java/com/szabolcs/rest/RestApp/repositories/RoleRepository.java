package com.szabolcs.rest.RestApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.szabolcs.rest.RestApp.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    
    @Query("select r from Role r where r.role = :role")
    Role findByRole(@Param("role") String role);

}
