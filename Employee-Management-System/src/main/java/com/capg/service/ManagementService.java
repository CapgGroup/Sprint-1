package com.capg.service;

import java.util.List;

import com.capg.entity.Employee;


public interface ManagementService {
	public List<Employee> fetchAllEmployees();

	public Employee fetchById(int id);

	public void deleteById(int id);

	public Employee updateEmployee(Employee employee);
	
	public Employee addEmployee(Employee employee);

	
}
