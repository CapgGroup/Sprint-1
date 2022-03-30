package com.capg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capg.entity.Employee;
import com.capg.service.ManagementService;
@RestController
public class ManagementController {
	
	@Autowired
	private ManagementService managementService;
	
	@GetMapping("/employee-list")
	public List<Employee> fetchAllEmployees()
	{
		return managementService.fetchAllEmployees();
	}
	
	@PostMapping("/add-employee")
	public Employee addEmployee(@RequestBody Employee employee) {
		return managementService.addEmployee(employee);
	}
	
}
