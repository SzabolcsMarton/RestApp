package com.szabolcs.rest.RestApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.szabolcs.rest.RestApp.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long>{
    
    @Query("select d from Department d where d.name = :name")
    Department findByName(@Param("name") String name);

}
