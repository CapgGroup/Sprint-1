package com.capg.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.capg.entity.Employee;

@DataJpaTest
public class EmployeeRepositoryTest {
	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private TestEntityManager entityManager;

	// JUnit for saveEmployee
	@BeforeEach
	void setUp() throws Exception {
		Employee employee = new Employee();
		employee.setEmail("employee@email.com");
		employee.setFirstName("first_name");
		employee.setLastName("last_name");
		employee.setId(999);
		employee.setManager(null);
		entityManager.persist(employee);

	}

	@Test
	void saveTest() {
		Employee employee = employeeRepository.findById(999).get();
		assertEquals(employee.getFirstName(), "first_name");
	}
	
	@Test
	void deleteTest() {
		employeeRepository.deleteById(999);
		assertEquals(employeeRepository.findById(999).isPresent(), false);
	}
	
	@Test
	void getAllEmployeesTest() {
		Employee employee2 = new Employee();
		employee2.setEmail("employee2@email.com");
		employee2.setFirstName("first_name2");
		employee2.setLastName("last_name2");
		employee2.setId(1000);
		employee2.setManager(null);
		entityManager.persist(employee2);
		List<Employee> employees = new ArrayList<Employee>();
		employees.add(employeeRepository.findById(999).get());
		employees.add(employee2);
		assertEquals(employeeRepository.findAll(), employees);
	}
}
