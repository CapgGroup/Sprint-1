package com.capg.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.google.common.base.Optional;

@Entity
public class Employee {
	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String firstName;
	private String lastName;
	private String email;

	@ManyToOne
	@JoinColumn(name = "manager_id", referencedColumnName = "id")
	private Manager manager;

	@ManyToMany()
	@JoinTable(name = "employee_project", joinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"))
	private List<Project> projects;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public Manager getManager() {
		return manager;
	}

	
	public void setManager(Manager manager) {
		this.manager = manager;
	}
	
	

}
