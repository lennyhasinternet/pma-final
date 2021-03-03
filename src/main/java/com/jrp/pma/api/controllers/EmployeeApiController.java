package com.jrp.pma.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jrp.pma.entities.Employee;
import com.jrp.pma.services.EmployeeService;

@RestController
@RequestMapping("/app-api/employees")
public class EmployeeApiController {

	@Autowired
	EmployeeService empServ;

	@GetMapping
	public Iterable<Employee> getEmployees() {
		return empServ.getAll();
	}

	@GetMapping("/{id}")
	public Employee getEmployeeById(@PathVariable("id") Long id) {
		return empServ.findById(id).get();
	}	

	@PostMapping(consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Employee create(@RequestBody Employee employee) {
		return empServ.save(employee);
	}

	// Update, patch used for partial update.
	@PutMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public Employee update(@RequestBody Employee employee) {
		return empServ.save(employee);
	}

	@PatchMapping(path="/{id}", consumes = "application/json")
	public Employee partialUpdate(@PathVariable("id") Long id, @RequestBody Employee patchEmployee) {
		Employee emp = empServ.findById(id).get();

		if (patchEmployee.getEmail() != null) {
			emp.setEmail(patchEmployee.getEmail());
		}
		
		if (patchEmployee.getFirstName() != null) {
			emp.setFirstName(patchEmployee.getFirstName());
		}
		
		if (patchEmployee.getLastName() != null) {
			emp.setLastName(patchEmployee.getLastName());
		}

		return empServ.save(emp);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {
		
		try {
			empServ.deleteById(id);
		} catch(EmptyResultDataAccessException e) {

		}
	}
	
}
