package com.capg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.entity.Manager;
import com.capg.entity.Project;
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
	
    @PutMapping("/{projectId}/set-manager/{managerId}")
    private Project assignManagerToEmployee(@PathVariable int projectId, @PathVariable int managerId) {
    	Project project = managementService.findProjectById(projectId).get();
        Manager manager = managementService.findManagerById(managerId).get();
        project.setManager(manager);
        return managementService.saveProject(project);
    }
}
