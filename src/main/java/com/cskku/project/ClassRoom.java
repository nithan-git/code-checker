package com.cskku.project;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.springframework.stereotype.Component;

@Component
@Entity
public class ClassRoom {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long classRoom_id;
	@ManyToOne
	@JoinColumn(name = "teacher_id",nullable=false)
	private Teacher teacher;
	@Column(nullable = false)
	private String name;
	private String description;
	@OneToMany(mappedBy="classRoom",fetch= FetchType.EAGER,cascade = CascadeType.REMOVE)
	@OrderBy("laboratory_id") 
	private Set<Laboratory> laboratorys;
	@OneToMany(mappedBy="classRoom",fetch= FetchType.EAGER,cascade = CascadeType.REMOVE)
	@OrderBy("register_id") 
	private Set<Register> registers;
	
	public ClassRoom() {
		super();
	}

	public Long getClassRoom_id() {
		return classRoom_id;
	}

	public void setClassRoom_id(Long classRoom_id) {
		this.classRoom_id = classRoom_id;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Laboratory> getLaboratorys() {
		return laboratorys;
	}

	public void setLaboratorys(Set<Laboratory> laboratorys) {
		this.laboratorys = laboratorys;
	}

	public Set<Register> getRegisters() {
		return registers;
	}

	public void setRegisters(Set<Register> registers) {
		this.registers = registers;
	}

	


	


	


	


	
	
	


}
