package com.capg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.entity.Employee;
import com.capg.entity.Manager;
import com.capg.entity.Project;
import com.capg.service.ManagementService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	private ManagementService managementService;
	
	@PostMapping("/save-employee")
	public Employee saveEmployee(@RequestBody Employee employee) {
		return managementService.saveEmployee(employee);
	}
	
    @PutMapping("/{employeeId}/add-project/{projectId}")
    private Employee addProjectToEmployee(@PathVariable int employeeId, @PathVariable int projectId) {
    	Employee employee = managementService.findEmployeeById(employeeId).get();
        Project project = managementService.findProjectById(projectId).get();
        employee.getProjects().add(project);
        return managementService.saveEmployee(employee);
    }
    
    @PutMapping("/{employeeId}/assign-manager/{managerId}")
    private Employee assignManagerToEmployee(@PathVariable int employeeId, @PathVariable int managerId) {
    	Employee employee = managementService.findEmployeeById(employeeId).get();
        Manager manager = managementService.findManagerById(managerId).get();
        employee.setManager(manager);
        return managementService.saveEmployee(employee);
    }
	
//	@Autowired
//	private EmployeeRepository employeeRepository;
//	
//	@Autowired
//	private ProjectRepository projectRepository;
//	
//	@PostMapping("/add-employee")
//	public Employee addEmployee(@RequestBody Employee employee) {
//		return employeeRepository.save(employee);
//	}
//	
//	@PutMapping("/{employeeId}/project/{projectId}")
//	Employee assignprojectToemployee(
//			@PathVariable int employeeId,
//			@PathVariable int projectId
//			) {
//		Employee employee = employeeRepository.findById(employeeId).get();
//		Project project = projectRepository.findById(projectId).get();
//		employee.assignProject(project);
//		return employeeRepository.save(employee);
//	}
//	
//	
//	
	

}
