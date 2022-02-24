package com.cskku.project;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Register {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long register_id;
	@ManyToOne
	@JoinColumn(name = "student_id", nullable=false)
	private Student student;
	@ManyToOne
	@JoinColumn(name = "classRoom_id", nullable=false)
	private ClassRoom classRoom;
	
	
	public Register() {
		
	}

	public Long getRegister_id() {
		return register_id;
	}

	public void setRegister_id(Long register_id) {
		this.register_id = register_id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public ClassRoom getClassRoom() {
		return classRoom;
	}

	public void setClassRoom(ClassRoom classRoom) {
		this.classRoom = classRoom;
	}

	
	
	
	
	


}
