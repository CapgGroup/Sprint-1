package com.capg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.capg.repository.EmployeeRepository;

@DataJpaTest
public class EmployeeControllerTest {

	@Autowired
	private EmployeeRepository employeeRepository;
	
}
