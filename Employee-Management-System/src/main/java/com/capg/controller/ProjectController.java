package com.capg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.capg.exception.EnterValidDetailsException;
import com.capg.exception.ProjectEmptyException;
import com.capg.exception.ProjectNotFoundException;
import com.capg.service.ManagementService;

@RestController
@RequestMapping("/projects")
public class ProjectController {
	@Autowired
	private ManagementService managementService;

	@PostMapping("/save-project")
	public ResponseEntity<Project> addProject(@RequestBody Project project) {
		if(project.getId()<0) {		
			throw new EnterValidDetailsException("Please Enter Valid Project Id");
		}
    	return new ResponseEntity<Project>(managementService.saveProject(project), HttpStatus.ACCEPTED);	
	}

	 @GetMapping("/{projectId}")
	    public ResponseEntity<Project> getByProjectId(@PathVariable int projectId) {
		 	if(projectId<0) {
		 		throw new EnterValidDetailsException("Please Enter Valid Project Id");
		 	}
		 	else {
	    	if(!managementService.findProjectById(projectId).isPresent()) {
				throw new EmployeeNotFoundException("Project not found with projectId "+ projectId);
			}
	    	return new ResponseEntity<Project>(managementService.findProjectById(projectId).get(), HttpStatus.FOUND);	
	    }
	 }

	 
	@PutMapping("/{projectId}/set-manager/{managerId}")
	private ResponseEntity<Project> assignManagerToProject(@PathVariable int projectId, @PathVariable int managerId) {
		
		if(projectId <0 || managerId<0) {
			
			throw new EnterValidDetailsException("Either Project Id or Manager Id is Invalid, Please Enter Valid Id's");
		}
		else {
		Project project = managementService.findProjectById(projectId).get();
		Manager manager = managementService.findManagerById(managerId).get();
		project.setManager(manager);
    	return new ResponseEntity<Project>(managementService.saveProject(project), HttpStatus.ACCEPTED);	
	}
}

	@GetMapping("")
	public ResponseEntity<List<Project>> getAllProjects() {
		List<Project> list = managementService.getAllProjects();
		if (list.isEmpty())
			throw new ProjectEmptyException("No Project Data is present right now");
    	return new ResponseEntity<List<Project>>(list, HttpStatus.OK);
	}
	
	
	
	@DeleteMapping("/delete-by-id/{projectId}")
    public void deleteByid(@PathVariable int projectId) {
		
		if(projectId<0) {
			throw new EnterValidDetailsException("Please Enter Valid Project Id");	
		}
		else {
    	if (!managementService.findProjectById(projectId).isPresent()) {
			throw new ProjectNotFoundException("Does not found Project with " + projectId);
		}
    	 managementService.deleteByProjectId(projectId);
    }
}

}
