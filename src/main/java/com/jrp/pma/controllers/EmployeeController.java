package com.jrp.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public String createEmployee(Employee employee, Model model) {
		empServ.save(employee);
		return "redirect:/employees";
	}
}
