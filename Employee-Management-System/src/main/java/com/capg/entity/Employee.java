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
	private int empid;
	private String fName;
	private String lName;
	private String email;

	@ManyToOne
	@JoinColumn(name = "manager_id")
	private Manager manager;
	
	@ManyToMany()
	@JoinTable(name = "project_employee", joinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"))
	private List<Project> projects;
	
	public Employee() {
	}

	public Employee(int empId, String fName, String lName, String email) {
		super();
		this.empid = empId;
		this.fName = fName;
		this.lName = lName;
		this.email = email;
	}

	public int getEmpId() {
		return empid;
	}

	public void setEmpId(int empId) {
		this.empid = empId;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Employee [id=" + empid + ", firstName=" + fName + ", lastName=" + lName + ", email=" + email
				+ ", manager=" + manager + "]";
	}
}
