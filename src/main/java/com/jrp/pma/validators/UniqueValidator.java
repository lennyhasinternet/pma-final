package com.jrp.pma.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.jrp.pma.entities.Employee;
import com.jrp.pma.services.EmployeeService;

public class UniqueValidator implements ConstraintValidator<UniqueValue, String> {
	
	@Autowired
	EmployeeService empServ;
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		System.out.println("Entered validation method...");
		Employee emp = empServ.findByEmail(value);
		
		if (emp != null) 
			return false;
		else 
			return true;
		
	}
}
