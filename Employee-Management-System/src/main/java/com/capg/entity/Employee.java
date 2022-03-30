package com.capg.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Employee {
	@Id
	private int id;
	private String firstName;
	private String lastName;
	private String email;

	@ManyToOne
	@JoinColumn(name = "manager_id")
	private Manager manager;

	public Employee() {
	}

	public Employee(int empId, String fName, String lName, String email) {
		super();
		this.id = empId;
		this.firstName = fName;
		this.lastName = lName;
		this.email = email;
	}

	public int getEmpId() {
		return id;
	}

	public void setEmpId(int empId) {
		this.id = empId;
	}

	public String getfName() {
		return firstName;
	}

	public void setfName(String fName) {
		this.firstName = fName;
	}

	public String getlName() {
		return lastName;
	}

	public void setlName(String lName) {
		this.lastName = lName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", manager=" + manager + "]";
	}
}
