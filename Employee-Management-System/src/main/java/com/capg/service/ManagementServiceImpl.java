package com.capg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.entity.Employee;
import com.capg.repository.EmployeeRepository;
import com.capg.repository.ManagerRepository;
import com.capg.repository.ProjectRepository;

@Service
public class ManagementServiceImpl implements ManagementService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private ManagerRepository managerRpository;
	@Autowired
	private ProjectRepository projectRepository;
	
	@Override
	public List<Employee> fetchAllEmployees() {
		return employeeRepository.findAll();
	}
	
	@Override
	public Employee fetchById(int id) {
		return employeeRepository.getById(id);	
	}
	
	@Override
	public void deleteById(int id) {
		employeeRepository.deleteById(id);
	}
	
	@Override
	public Employee updateEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	@Override
	public Employee addEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	
}
