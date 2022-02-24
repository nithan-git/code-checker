package com.cskku.project;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Work {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long work_id;
	@Column(nullable = false)
	private Boolean isCorrect;
	@ManyToOne
	@JoinColumn(name = "student_id", nullable=false)
	private Student student;
	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "document_id", nullable=false)
	private Document document;
	@ManyToOne
	@JoinColumn(name = "laboratory_id", nullable=false)
	private Laboratory laboratory;
	
	public Work() {
		super();
	}

	public Long getWork_id() {
		return work_id;
	}

	public void setWork_id(Long work_id) {
		this.work_id = work_id;
	}

	public Boolean getIsCorrect() {
		return isCorrect;
	}

	public void setIsCorrect(Boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public Laboratory getLaboratory() {
		return laboratory;
	}

	public void setLaboratory(Laboratory laboratory) {
		this.laboratory = laboratory;
	}

	

	

	
	
}
