package com.capg.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.entity.Manager;
import com.capg.exception.ManagerEmptyException;
import com.capg.exception.ManagerNotFoundException;
import com.capg.exception.ProjectNotFoundException;
import com.capg.service.ManagementService;

@RestController
@RequestMapping("/managers")
public class ManagerController {
	@Autowired
	private ManagementService managementService;

	@PostMapping("/save-manager")
	public ResponseEntity<Manager> addManager(@RequestBody Manager manager) {
		return new ResponseEntity<Manager>(managementService.saveManager(manager), HttpStatus.CREATED);
	}

	@GetMapping("")
	public ResponseEntity<List<Manager>> getAllManger() {
		List<Manager> list = managementService.getAllManager();
		if (list.isEmpty())
			throw new ManagerEmptyException("No Manager Data is present right now");
		return new ResponseEntity<List<Manager>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/{managerId}")
    public ResponseEntity<Manager> getByManagerId(@PathVariable int managerId) {
    	if(!managementService.findManagerById(managerId).isPresent()) {
			throw new ManagerNotFoundException("Manager not found with managerId "+ managerId);
		}
		return new ResponseEntity<Manager>(managementService.findManagerById(managerId).get(), HttpStatus.FOUND);
    }
	
	
	@GetMapping("/get-manager-by-project/{projectId}")
    public ResponseEntity<Manager> getManagerByProjectId(@PathVariable int projectId) {
    	if(!managementService.findProjectById(projectId).isPresent()) {
			throw new ProjectNotFoundException("Project not found with projectId "+ projectId);
		}
		return new ResponseEntity<Manager>(managementService.findProjectById(projectId).get().getManager(), HttpStatus.FOUND);
    }
	
	@DeleteMapping("/delete-by-id/{managerId}")
    public void deleteByid(@PathVariable int managerId) {
		Optional<Manager> manager = managementService.findManagerById(managerId);
    	if (!manager.isPresent()) {
			throw new ManagerNotFoundException("Does not found manager with " + managerId);
		}
    	manager.get().getEmployees().stream().forEach((p) -> p.setManager(null));
    	manager.get().getProject().setManager(null);
    	managementService.deleteByManagerId(managerId);
    }
}
