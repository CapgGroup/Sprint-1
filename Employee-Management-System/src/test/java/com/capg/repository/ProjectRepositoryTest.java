package com.capg.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.capg.entity.Project;

@DataJpaTest
public class ProjectRepositoryTest {
	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private TestEntityManager entityManager;

	// JUnit for saveEmployee
	@BeforeEach
	void setUp() throws Exception {
		Project project = new Project();
		project.setProjectName("project1");
		project.setId(999);
		entityManager.persist(project);

	}

	@Test
	void saveTest() {
		Project project = projectRepository.findById(999).get();
		assertEquals(project.getProjectName(), "project1");
	}
	
	@Test
	void deleteTest() {
		projectRepository.deleteById(999);
		assertEquals(projectRepository.findById(999).isPresent(), false);
	}
	
	@Test
	void getAllManagersTest() {
		Project project2 = new Project();
		project2.setProjectName("project2");
		project2.setId(1000);
		entityManager.persist(project2);
		List<Project> managers = new ArrayList<Project>();
		managers.add(projectRepository.findById(999).get());
		managers.add(project2);
		assertEquals(projectRepository.findAll(), managers);
	}
}
