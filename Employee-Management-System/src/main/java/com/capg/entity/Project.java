package com.capg.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Project {
	
	@Id
	private int id;
	private String projectName;
	public Project(int id, String projectName) {
		super();
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
	public Project() {
		
	}
	@Override
	public String toString() {
		return "Project [id=" + id + ", projectName=" + projectName + "]";
	}
	
	
	

}
