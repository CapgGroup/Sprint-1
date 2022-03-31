package com.capg.service;

import java.util.List;
import java.util.Optional;

import com.capg.entity.Employee;
import com.capg.entity.Manager;
import com.capg.entity.Project;

public interface ManagementService {
	//Employee Methods
	public Employee saveEmployee(Employee employee);
	
	public Optional<Employee> findEmployeeById(int id);
	
	public List<Employee> findByManagerId(int manager_id);
	
	public List<Employee> getAllEmployees();
	
	public void deleteById(int id);
	
	/* public List<Employee> getByProjectId(int id); */
	
	
	//Project Methods
	public Project saveProject(Project project);
	
	public Optional<Project> findProjectById(int id);
	
	//Manager Methods
	public Manager saveManager(Manager manager);
	
	public Optional<Manager> findManagerById(int id);
	
}
