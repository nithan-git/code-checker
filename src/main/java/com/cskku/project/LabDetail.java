package com.cskku.project;

import javax.persistence.CascadeType;
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
public class LabDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long labDetail_id;
	@ManyToOne
	@JoinColumn(name = "laboratory_id", nullable=false)
	private Laboratory laboratory;
	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "document_id", nullable=false)
	private Document document;
	
	public LabDetail() {
		super();
	}

	public Long getLabDetail_id() {
		return labDetail_id;
	}

	public void setLabDetail_id(Long labDetail_id) {
		this.labDetail_id = labDetail_id;
	}

	public Laboratory getLaboratory() {
		return laboratory;
	}

	public void setLaboratory(Laboratory laboratory) {
		this.laboratory = laboratory;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	
	
	
	


}
