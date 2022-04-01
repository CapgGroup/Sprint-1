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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.entity.Employee;
import com.capg.entity.Manager;
import com.capg.entity.Project;
import com.capg.exception.EmployeeAlreadyPresentException;
import com.capg.exception.EmployeeNotFoundException;
import com.capg.exception.EmployeesEmptyException;
import com.capg.exception.EnterValidDetailsException;
import com.capg.exception.ManagerNotFoundException;
import com.capg.service.ManagementService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {	
	@Autowired
	private ManagementService managementService;
	
	@PostMapping("/add-employee")
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
		
		if(employee.getId()<0) {
			throw new EnterValidDetailsException("Please Enter Valid Employee ID");		
		}
		
		else {
			
		if(managementService.findEmployeeById(employee.getId()).isPresent()) {
			throw new EmployeeAlreadyPresentException("Entered id"+employee.getId()+"is already Present Please Enter another id");
		}
		
		return new ResponseEntity<Employee>(managementService.saveEmployee(employee), HttpStatus.CREATED);
	}
}
    @PutMapping("/{employeeId}/add-project/{projectId}")
    private ResponseEntity<Employee> addProjectToEmployee(@PathVariable int employeeId, @PathVariable int projectId) {
    	
    	if(employeeId<0 || projectId<0) {
    		
    		throw new EnterValidDetailsException("Either empId Or projectId Is Invalid Please Enter Correct ");
    	}
    	else {
    	Employee employee = managementService.findEmployeeById(employeeId).get();
        Project project = managementService.findProjectById(projectId).get();
        employee.getProjects().add(project);
        return new ResponseEntity<Employee>(managementService.saveEmployee(employee), HttpStatus.ACCEPTED);
    }
 }  
    @PutMapping("/{employeeId}/assign-manager/{managerId}")
    private ResponseEntity<Employee> assignManagerToEmployee(@PathVariable int employeeId, @PathVariable int managerId) {
        if(employeeId<0 || managerId<0) {	
    		throw new EnterValidDetailsException("Either empId Or managerId Is Invalid Please Enter Correct ");
    	}
        else {
    	Employee employee = managementService.findEmployeeById(employeeId).get();
        Manager manager = managementService.findManagerById(managerId).get();
        employee.setManager(manager);
        return new ResponseEntity<Employee>(managementService.saveEmployee(employee), HttpStatus.ACCEPTED);
    }
}
    @GetMapping("")
    	public ResponseEntity<List<Employee>> getAllEmployees(){
    	List<Employee> list = managementService.getAllEmployees();
		if (list.isEmpty())
			throw new EmployeesEmptyException("No Employees Data is present right now");
    	return new ResponseEntity<List<Employee>>(list, HttpStatus.OK);
    }
    
    @GetMapping("/{empId}")
    public ResponseEntity<Employee> getByEmployeeId(@PathVariable int empId) {
    	
    	if(empId<0) {
    		
    		throw new EnterValidDetailsException("Please Enter Valid Employee ID");
    	}
    	else {
    	if(!managementService.findEmployeeById(empId).isPresent()) {
			throw new EmployeeNotFoundException("Employee not found with empId"+empId);
		}
    	return new ResponseEntity<Employee>(managementService.findEmployeeById(empId).get(), HttpStatus.FOUND);
    }
  }
    
    @GetMapping("/get-by-managerId/{managerId}")
	public ResponseEntity<List<Employee>> getByManagerId(@PathVariable int managerId) {
    	if(managerId<0) {
    		
    		throw new EnterValidDetailsException("Please Enter Valid Manager ID");
    	}
 	
    	else {
    	if(!managementService.findManagerById(managerId).isPresent()) {
			throw new ManagerNotFoundException("Manager not found with manager_id"+managerId);
		}
		return new ResponseEntity<List<Employee>>(managementService.findByManagerId(managerId), HttpStatus.FOUND);
	}
 }
    
    @GetMapping("/get-by-projectId/{projectId}")
    public ResponseEntity<List<Employee>> getByProjectId(@PathVariable int projectId){
    	if(projectId<0) {
    		throw new EnterValidDetailsException("Please Enter Valid projectId");
    	}	
    	return new ResponseEntity<List<Employee>>(managementService.findProjectById(projectId).get().getEmployees(), HttpStatus.FOUND);
    }
    
    @DeleteMapping("/delete-by-id/{id}")
    public void deleteByid(@PathVariable int id) { 	
    	if(id<0) {   		
    		throw new EnterValidDetailsException("Please Enter Valid Employee ID");
    	}
    	else {
    	if (!managementService.findEmployeeById(id).isPresent()) {
			throw new EmployeeNotFoundException("Does not found Employee with" + id);
		}
    	 managementService.deleteById(id);
    }
}    	
    
    @PutMapping("/update")
    public ResponseEntity<Employee> updateEmployeeById(@RequestBody Employee employee) {
    	Optional<Employee> newEmployee = managementService.findEmployeeById(employee.getId());
    	if(!newEmployee.isPresent()) {
    		throw new EmployeeNotFoundException("Employee does not exist with id "+employee.getId());
    	}
    	else {
    		newEmployee.get().setId(employee.getId());
    		newEmployee.get().setFirstName(employee.getFirstName());
    		newEmployee.get().setLastName(employee.getLastName());
    		newEmployee.get().setEmail(employee.getEmail());
    		return new ResponseEntity<Employee>(managementService.saveEmployee(newEmployee.get()), HttpStatus.OK);
    	}
    }
    
    
    
	

}
