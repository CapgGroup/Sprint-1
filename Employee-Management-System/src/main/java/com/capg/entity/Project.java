package com.capg.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Project {
	@Id
	private int id;
	private String projectName;
    @JsonIgnore
	@ManyToMany(mappedBy = "ProjectAssigned")
	private Set<Employee> employee = new HashSet<>();
	

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

//	@Override
//	public String toString() {
//		return "Project [id=" + id + ", projectName=" + projectName + "]";
//	}
}
