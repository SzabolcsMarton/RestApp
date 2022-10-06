package com.szabolcs.rest.RestApp.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.szabolcs.rest.RestApp.user.Employee1;
import com.szabolcs.rest.RestApp.user.Root;

@RestController
@RequestMapping("/sendjson")
public class EmployeeController {

    @GetMapping
    public ResponseEntity<Root> get() throws StreamReadException, DatabindException, IOException {
	Root root = new ObjectMapper().readValue(new URL("file:src/main/resources/Employee.json"), Root.class);
	return ResponseEntity.ok(root);
    }
    
    //should-be-post?age=29&name=Pankaj&gender=Male&role=Java Developer&age=35&name=Lisa&gender=Female&role=CEO&age=40&name=Tom&gender=Male&role=Manager&age=25&name=Meghna&gender=Female&role=Manager
    @GetMapping("/should-be-post")
    public ResponseEntity<Root> getByQueryList(int[] age, String[] name, String[] gender, String[] role)
	    throws StreamReadException, DatabindException, IOException {
	Root root11 = new Root();
	for (int i = 0; i < age.length; i++) {
	    root11.addEmployee(new Employee1(age[i], name[i], gender[i], role[i]));
	}

	return ResponseEntity.ok(root11);
    }

    @GetMapping("/query1")
    public ResponseEntity<Root> getByQuery1(int age, String name, String gender, String role)
	    throws StreamReadException, DatabindException, IOException {
	Employee1 employee1 = new Employee1(age, name, gender, role);
	Root root = new Root();
	root.addEmployee(employee1);

	return ResponseEntity.ok(root);
    }

    @GetMapping("/query2")
    public ResponseEntity<Root> getByQuery2(Employee1 employee1)
	    throws StreamReadException, DatabindException, IOException {
	Root root = new Root();
	root.addEmployee(employee1);

	return ResponseEntity.ok(root);
    }

    @PostMapping("/query3")
    public ResponseEntity<Root> postByQueryList(@RequestBody ArrayList<Employee1> employees)
	    throws StreamReadException, DatabindException, IOException {
	Root root = new Root();
	for (Employee1 current : employees) {
	    root.addEmployee(current);
	}

	return ResponseEntity.ok(root);
    }

    @PostMapping("/query4")
    public ResponseEntity<Root> postByQueryList(@RequestBody Root root)
	    throws StreamReadException, DatabindException, IOException {

	return ResponseEntity.ok(root);
    }

  
}
