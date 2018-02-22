package com.kafka.praneeth;

import java.io.Serializable;

public class Employee implements Serializable {

	private String name;
	private String branch;
	private String practice;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getPractice() {
		return practice;
	}

	public void setPractice(String practice) {
		this.practice = practice;
	}

	public Employee(String name, String branch, String practice) {
		super();
		this.name = name;
		this.branch = branch;
		this.practice = practice;
	}

	public Employee() {
		super();
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", branch=" + branch + ", practice=" + practice + "]";
	}

}
