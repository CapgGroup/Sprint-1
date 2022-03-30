package com.capg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capg.entity.Project;
import com.capg.repository.ProjectRepository;
@RestController
public class ProjectController {

	
	@Autowired
	private ProjectRepository projectRepository;
	
	@PostMapping("/add-project")
	public Project addProject(@RequestBody Project project) {
		return projectRepository.save(project);
	}
	
	
}
