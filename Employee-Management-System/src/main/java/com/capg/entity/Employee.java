package com.capg.entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Employee {
	@Id
	private int empId;
	private String firstName;
	private String lastName;
	private String email;

//	@ManyToOne
//	@JoinColumn(name = "manager_id")
//	private Manager manager;
//	
//	@ManyToMany()
//	@JoinTable(name = "project_employee", joinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"))
//	private List<Project> projects;
	
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

//	@Override
//	public String toString() {
//		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
//				+ ", manager=" + manager + "]";
//	}
}

