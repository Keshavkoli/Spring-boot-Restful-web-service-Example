package com.javatpoint.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@JsonIgnoreProperties({"name", "phone"})  
@JsonFilter("SomeBeanFilter")
public class SomeBean {
	// Example of Implementing Static Filtering for RESTful Services
	private String name;
	private String phone;
	//JsonIgnore indicates that the annotated method or field is to be ignored 
//	@JsonIgnore
	private String salary;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public SomeBean(String name, String phone, String salary) {
		super();
		this.name = name;
		this.phone = phone;
		this.salary = salary;
	}

	public SomeBean() {
		super();
	}

	@Override
	public String toString() {
		return "SomeBean [name=" + name + ", phone=" + phone + ", salary=" + salary + "]";
	}

}
