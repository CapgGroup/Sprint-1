package com.capg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.entity.Employee;
import com.capg.entity.Manager;
import com.capg.entity.Project;
import com.capg.exception.EmployeeAlreadyPresentException;
import com.capg.exception.EmployeeNotFoundException;
import com.capg.exception.EmployeesEmptyException;
import com.capg.exception.ManagerNotFoundException;
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
		if(employeeRepository.findById(employee.getId()).isPresent()) {
			throw new EmployeeAlreadyPresentException("Entered id"+employee.getId()+"is already Present Please Enter another id");
		}
			
		
		return employeeRepository.save(employee);
	}

	@Override
	public Optional<Employee> findEmployeeById(int id) {
//		Optional<Employee> employee = employeeRepository.findById(id);
		
		if(!employeeRepository.findById(id).isPresent()) {
			throw new EmployeeNotFoundException("Employee not found with empId"+id);
		}
		return employeeRepository.findById(id);
	}

	@Override
	public List<Employee> findByManagerId(int managerId) {
		if(!employeeRepository.findById(managerId).isPresent()) {
			throw new ManagerNotFoundException("Manager not found with manager_id"+managerId);
		}
		return employeeRepository.findByManagerId(managerId);
	}

	@Override
	public List<Employee> getAllEmployees() {
         List<Employee> list=employeeRepository.findAll();
         if(list.isEmpty())
        	 throw new EmployeesEmptyException("No Employees Data is present right now");
		return list;
	}

	@Override
	public void deleteById(int id) {
		if(!employeeRepository.findById(id).isPresent()) {
			throw new EmployeeNotFoundException("Does not found Employee with"+id);
		}
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

	// Manager Methods
	@Override
	public Manager saveManager(Manager manager) {
		return managerRepository.save(manager);
	}

	@Override
	public Optional<Manager> findManagerById(int id) {
		return managerRepository.findById(id);
	}

}
