package com.capg.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Manager {
	@Id
	private int id;
	private String fName;
	private String lName;
	private String email;

	@OneToOne(mappedBy = "manager")
	Project project;

	@OneToMany(mappedBy = "manager")
	private List<Employee> employees;
	
	public Manager() {
	}

	public Manager(int id, String fName, String lName, String email) {
		super();
		this.id = id;
		this.fName = fName;
		this.lName = lName;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		return "Manager [id=" + id + ", firstName=" + fName + ", lastName=" + lName + ", email=" + email
				+ ", project=" + project + ", employees=" + employees + "]";
	}

	
}
