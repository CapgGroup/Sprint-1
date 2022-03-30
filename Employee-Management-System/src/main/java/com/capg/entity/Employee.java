package com.capg.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Employee {
	@Id
	private int empId;
	private String firstName;
	private String lastName;
	private String email;
	
	@ManyToMany
	@JoinTable(
			name="Project_Assigned",
			joinColumns= @JoinColumn(name="employee_id"),
			inverseJoinColumns = @JoinColumn(name="project_id")
			)
	private Set<Project> ProjectAssigned =new HashSet<>();

	

	public Set<Project> getProjectAssigned() {
		return ProjectAssigned;
	}

	public void setProjectAssigned(Set<Project> projectAssigned) {
		ProjectAssigned = projectAssigned;
	}

	public Employee() {
	}

	public Employee(int empId, String firstName, String lastName, String email) {
		super();
		this.empId = empId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getfName() {
		return firstName;
	}

	public void setfName(String firstName) {
		this.firstName = firstName;
	}

	public String getlName() {
		return lastName;
	}

	public void setlName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void assignProject(Project project) {
		ProjectAssigned.add(project);
		
	}

//	@Override
//	public String toString() {
//		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
//				+ ", manager=" + manager + "]";
//	}
}

