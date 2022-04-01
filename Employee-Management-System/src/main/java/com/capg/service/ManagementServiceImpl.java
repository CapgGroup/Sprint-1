package com.capg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.entity.Employee;
import com.capg.entity.Manager;
import com.capg.entity.Project;
import com.capg.repository.EmployeeRepository;
import com.capg.repository.ManagerRepository;
import com.capg.repository.ProjectRepository;

@Service
public class ManagementServiceImpl implements ManagementService {

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private ManagerRepository managerRepository;

	// Employee Methods
	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public Optional<Employee> findEmployeeById(int id) {
//		Optional<Employee> employee = employeeRepository.findById(id);

		return employeeRepository.findById(id);
	}

	@Override
	public List<Employee> findByManagerId(int managerId) {

		return employeeRepository.findByManagerId(managerId);
	}

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> list = employeeRepository.findAll();

		return list;
	}

	@Override
	public void deleteById(int id) {

		employeeRepository.deleteById(id);
	}

	// Project Methods
	@Override
	public Project saveProject(Project project) {
		return projectRepository.save(project);
	}

	@Override
	public Optional<Project> findProjectById(int id) {
		return projectRepository.findById(id);
	}

	@Override
	public List<Project> getAllProjects() {
		List<Project> list = projectRepository.findAll();
		return list;
	}

	@Override
	public void deleteByProjectId(int project_id) {
		projectRepository.deleteById(project_id);
	}

	// Manager Methods
	@Override
	public Manager saveManager(Manager manager) {
		return managerRepository.save(manager);
	}

	@Override
	public Optional<Manager> findManagerById(int id) {
		return managerRepository.findById(id);
	}

	@Override
	public List<Manager> getAllManager() {
		List<Manager> list = managerRepository.findAll();
		return list;
	}

	@Override
	public void deleteByManagerId(int manager_id) {
		managerRepository.deleteById(manager_id);
		
	}



}
