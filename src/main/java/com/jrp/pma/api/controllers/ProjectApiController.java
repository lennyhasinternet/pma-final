package com.jrp.pma.api.controllers;

import javax.validation.Valid;

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
import com.jrp.pma.entities.Project;
import com.jrp.pma.services.ProjectService;

@RestController
@RequestMapping("/app-api/projects")
public class ProjectApiController {

	@Autowired
	ProjectService projServ;

	@GetMapping
	public Iterable<Project> getProjects() {
		return projServ.getAll();
	}

	@GetMapping("/{id}")
	public Project getProjectById(@PathVariable("id") Long id) {
		return projServ.findById(id).get();
	}	

	@PostMapping(consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Project create(@RequestBody @Valid Project project) {
		return projServ.save(project);
	}

	// Update, patch used for partial update.
	@PutMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public Project update(@RequestBody @Valid Project project) {
		return projServ.save(project);
	}

	@PatchMapping(path="/{id}", consumes = "application/json")
	public Project partialUpdate(@PathVariable("id") Long id, @RequestBody @Valid Project patchProject) {
		Project emp = projServ.findById(id).get();

		if (patchProject.getName() != null) {
			emp.setName(patchProject.getName());
		}
		
		if (patchProject.getStage() != null) {
			emp.setStage(patchProject.getStage());
		}
		
		if (patchProject.getDescription() != null) {
			emp.setDescription(patchProject.getDescription());
		}

		return projServ.save(emp);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {
		
		try {
			projServ.deleteById(id);
		} catch(EmptyResultDataAccessException e) {

		}
	}
	
}
