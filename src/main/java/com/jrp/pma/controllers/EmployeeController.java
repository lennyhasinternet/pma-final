package com.jrp.pma.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jrp.pma.entities.Employee;
import com.jrp.pma.services.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	EmployeeService empServ;
	
	@GetMapping
	public String displayEmployees(Model model) {
		List<Employee> employees = empServ.getAll();
		model.addAttribute("employees", employees);
		return "employees/list-employees";
	}
	
	@RequestMapping("/new")
	public String displayProjectForm(Model model) {
		
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "employees/new-employee";
	}
	
	@PostMapping("/save")
	public String createEmployee(@Valid Employee employee, Errors errors, Model model) {
		
		if (errors.hasErrors())
			return "employees/new-employee";
		
		empServ.save(employee);
		return "redirect:/employees";
	}
	
	@GetMapping("/update")
	public String displayEmployeeUpdateForm(@RequestParam("id") Long id, Model model) {
		
		Employee emp = empServ.findByEmployeeId(id);
		model.addAttribute("employee", emp);

		return "employees/new-employee";
	}
	
	@GetMapping("/delete")
	public String deleteEmployee(@RequestParam("id") Long id, Model model) {
		
		Employee emp = empServ.findByEmployeeId(id);
	    empServ.delete(emp);
	    return "redirect:/employees";
	}
}
