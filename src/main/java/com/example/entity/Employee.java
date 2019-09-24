package com.example.entity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class Employee {

	private int id;
	@NotEmpty(message = "name is mandatory field")
	private String name;
	@Pattern(regexp = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Please Provide Valid Email")
	private String email;
	@Pattern(regexp = "[6789][0-9]{9}", message = "Please Give Valid Contact Number")
	private String contact;
	private String dept;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", email=" + email + ", contact=" + contact + ", dept=" + dept
				+ "]";
	}
}
