package com.capg.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.capg.entity.Manager;

@DataJpaTest
public class ManagerRepositoryTest {
	@Autowired
	private ManagerRepository managerRepository;

	@Autowired
	private TestEntityManager entityManager;

	// JUnit for saveEmployee
	@BeforeEach
	void setUp() throws Exception {
		Manager manager = new Manager();
		manager.setEmail("manager@email.com");
		manager.setFirstName("first_name");
		manager.setLastName("last_name");
		manager.setId(999);
		entityManager.persist(manager);

	}

	@Test
	void saveTest() {
		Manager manager = managerRepository.findById(999).get();
		assertEquals(manager.getFirstName(), "first_name");
	}
	
	@Test
	void deleteTest() {
		managerRepository.deleteById(999);
		assertEquals(managerRepository.findById(999).isPresent(), false);
	}
	
	@Test
	void getAllManagersTest() {
		Manager manager2 = new Manager();
		manager2.setEmail("employee2@email.com");
		manager2.setFirstName("first_name2");
		manager2.setLastName("last_name2");
		manager2.setId(1000);
		entityManager.persist(manager2);
		List<Manager> managers = new ArrayList<Manager>();
		managers.add(managerRepository.findById(999).get());
		managers.add(manager2);
		assertEquals(managerRepository.findAll(), managers);
	}
}
