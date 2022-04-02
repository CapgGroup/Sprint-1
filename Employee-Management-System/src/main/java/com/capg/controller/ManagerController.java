package com.capg.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

import com.capg.entity.Employee;
import com.capg.entity.Manager;
import com.capg.exception.EnterValidDetailsException;
import com.capg.exception.ManagerEmptyException;
import com.capg.exception.ManagerNotFoundException;
import com.capg.exception.ProjectNotFoundException;
import com.capg.service.ManagementService;
import com.google.common.base.Predicate;

@RestController
@RequestMapping("/managers")
public class ManagerController {
	@Autowired
	private ManagementService managementService;

	@PostMapping("/save-manager")
	public ResponseEntity<Manager> addManager(@RequestBody Manager manager) {
		
		if(manager.getId()<0)
			throw new EnterValidDetailsException("Please Enter Valid Manager Id");
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
		if(managerId<0) {
			throw new EnterValidDetailsException("Please Enter Valid Manager Id");
			
		}
		else {
    	if(!managementService.findManagerById(managerId).isPresent()) {
			throw new ManagerNotFoundException("Manager not found with managerId "+ managerId);
		}
		return new ResponseEntity<Manager>(managementService.findManagerById(managerId).get(), HttpStatus.FOUND);
    }
}
	
	
	@GetMapping("/get-manager-by-project/{projectId}")
    public ResponseEntity<Manager> getManagerByProjectId(@PathVariable int projectId) {
		
		if(projectId<0) {		
			throw new EnterValidDetailsException("Please Enter Valid Project Id");
		}
		else {
    	if(!managementService.findProjectById(projectId).isPresent()) {
			throw new ProjectNotFoundException("Project not found with projectId "+ projectId);
		}
		return new ResponseEntity<Manager>(managementService.findProjectById(projectId).get().getManager(), HttpStatus.FOUND);
    }
}		
	
	@DeleteMapping("/delete-by-id/{managerId}")
    public void deleteByid(@PathVariable int managerId) {
//		
//		
//		List<Employee> list=managementService.getAllEmployees();
//		for(Employee employee: list) {
//			
//			if(employee.getManager().getId()==managerId) {		
//			throw new EnterValidDetailsException("You Cant Delete This Manager Id As this Manager Is Assigned to Employees ");
//			}
//			
//		}
//		
		if(managerId<0) {		
			throw new EnterValidDetailsException("Please Enter Valid Manager Id");
		}
		else {
		Optional<Manager> manager = managementService.findManagerById(managerId);
    	if (!manager.isPresent()) {
			throw new ManagerNotFoundException("Does not found manager with " + managerId);
		}
    	Predicate<Manager> isProjectPresent = (m) -> m.getProject() != null;
    	Predicate<Manager> areEmployeesPresent = (m) -> !(m.getEmployees()==null || m.getEmployees().isEmpty());
    	if(isProjectPresent.apply(manager.get()) & areEmployeesPresent.apply(manager.get())) {
    		throw new EnterValidDetailsException("You cant delete this manager with id " + manager.get().getId() +  " as this manager is assigned to both employee(s) with id(s) " + manager.get().getEmployees().stream().map((e) -> String.valueOf(e.getId())).collect(Collectors.joining(",")) + " and project with id " + manager.get().getProject().getId());
    	} else if(isProjectPresent.apply(manager.get())) {
    		throw new EnterValidDetailsException("You cant delete this manager with id " + manager.get().getId() +  " as this manager is assigned to project with id " + manager.get().getProject().getId());
    	}else if(areEmployeesPresent.apply(manager.get())) {
    		throw new EnterValidDetailsException("You cant delete this manager with id " + manager.get().getId() +  " as this manager is assigned to employee(s) with id(s) - " + manager.get().getEmployees().stream().map((e) -> String.valueOf(e.getId())).collect(Collectors.joining(",")));
    	} else {
    		managementService.deleteByManagerId(managerId);
    	}
    }
 }
}
