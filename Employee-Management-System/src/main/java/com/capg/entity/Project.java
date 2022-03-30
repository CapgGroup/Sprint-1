package com.capg.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Project {
	@Id
	private int id;
	private String projectName;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "managerId")
	Manager manager;

	@ManyToMany(mappedBy = "projects")
	private List<Employee> employees;

	public Project() {
	}

	public Project(int id, String projectName) {
		this.id = id;
		this.projectName = projectName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", projectName=" + projectName + "]";
	}
}
