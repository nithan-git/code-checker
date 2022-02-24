package com.cskku.project;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Student {
	
	@Id
	@Column(columnDefinition = "CHAR(11)")
	private String student_id;
	@Column(nullable = false)
	private String password;
	@Column(length = 30,nullable = false)
	private String fname;
	@Column(length = 30,nullable = false)
	private String lname;
	@OneToMany(mappedBy="student",fetch= FetchType.EAGER,cascade = CascadeType.REMOVE)
	@OrderBy("register_id") 
	private Set<Register> registers;
	@OneToMany(mappedBy="student",fetch= FetchType.EAGER,cascade = CascadeType.REMOVE)
	@OrderBy("work_id")
	private Set<Work> works;
	
	public Student() {
		super();
	}

	public String getStudent_id() {
		return student_id;
	}

	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public Set<Register> getRegisters() {
		return registers;
	}

	public void setRegisters(Set<Register> registers) {
		this.registers = registers;
	}

	public Set<Work> getWorks() {
		return works;
	}

	public void setWorks(Set<Work> works) {
		this.works = works;
	}

}
