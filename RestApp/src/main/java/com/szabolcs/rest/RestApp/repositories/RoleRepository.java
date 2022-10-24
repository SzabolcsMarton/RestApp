package com.szabolcs.rest.RestApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.szabolcs.rest.RestApp.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
