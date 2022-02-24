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
public class Teacher {
	
	@Id
	@Column(length = 30)
	private String teacher_id;
	@Column(nullable = false)
	private String password;
	@Column(length = 30,nullable = false)
	private String fname;
	@Column(length = 30,nullable = false)
	private String lname;
	@OneToMany(mappedBy="teacher",fetch= FetchType.EAGER,cascade = CascadeType.REMOVE)
	@OrderBy("classRoom_id") 
	private Set<ClassRoom> classRooms;
	
	public Teacher() {
		super();
	}

	public String getTeacher_id() {
		return teacher_id;
	}

	public void setTeacher_id(String teacher_id) {
		this.teacher_id = teacher_id;
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

	public Set<ClassRoom> getClassRooms() {
		return classRooms;
	}

	public void setClassRooms(Set<ClassRoom> classRooms) {
		this.classRooms = classRooms;
	}

	

	

	




	
	

	
	
	
	
	
	
	
	

	


}
