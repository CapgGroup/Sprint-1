package com.capg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.entity.Employee;
import com.capg.entity.Manager;
import com.capg.entity.Project;
import com.capg.exception.EmployeeNotFoundException;
import com.capg.exception.EmployeesEmptyException;
import com.capg.exception.ProjectEmptyException;
import com.capg.exception.ProjectNotFoundException;
import com.capg.service.ManagementService;

@RestController
@RequestMapping("/projects")
public class ProjectController {
	@Autowired
	private ManagementService managementService;

	@PostMapping("/save-project")
	public Project addProject(@RequestBody Project project) {
		return managementService.saveProject(project);
	}

	 @GetMapping("/{projectId}")
	    public Project getByProjectId(@PathVariable int projectId) {
	    	if(!managementService.findProjectById(projectId).isPresent()) {
				throw new EmployeeNotFoundException("Employee not found with projectId "+ projectId);
			}
	    	return managementService.findProjectById(projectId).get();	
	    }

	 
	@PutMapping("/{projectId}/set-manager/{managerId}")
	private Project assignManagerToProject(@PathVariable int projectId, @PathVariable int managerId) {
		Project project = managementService.findProjectById(projectId).get();
		Manager manager = managementService.findManagerById(managerId).get();
		project.setManager(manager);
		return managementService.saveProject(project);
	}

	@GetMapping("")
	public List<Project> getAllProjects() {
		List<Project> list = managementService.getAllProjects();
		if (list.isEmpty())
			throw new ProjectEmptyException("No Employees Data is present right now");
		return list;
	}
	
	
	
	@DeleteMapping("/delete-by-id/{projectId}")
    public void deleteByid(@PathVariable int projectId) {
    	if (!managementService.findProjectById(projectId).isPresent()) {
			throw new ProjectNotFoundException("Does not found Employee with " + projectId);
		}
    	 managementService.deleteByProjectId(projectId);
    }

}
