package com.capg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capg.entity.Employee;
import com.capg.entity.Project;
import com.capg.repository.EmployeeRepository;
import com.capg.repository.ProjectRepository;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@PostMapping("/add-employee")
	public Employee addEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	
	@PutMapping("/{employeeId}/project/{projectId}")
	Employee assignprojectToemployee(
			@PathVariable int employeeId,
			@PathVariable int projectId
			) {
		Employee employee = employeeRepository.findById(employeeId).get();
		Project project = projectRepository.findById(projectId).get();
		employee.assignProject(project);
		return employeeRepository.save(employee);
	}
	
	
	
	

}
