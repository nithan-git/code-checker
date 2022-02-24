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
public class TestCase {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long testCase_id;
	@Column(nullable = false)
	private String output;
	@Column(nullable = false)
	private String type;
	@ManyToOne
	@JoinColumn(name = "laboratory_id", nullable=false)
	private Laboratory laboratory;
	@OneToMany(mappedBy="testCase",fetch= FetchType.EAGER,cascade = CascadeType.REMOVE)
	@OrderBy("input_id") 
	private Set<Input> inputs;
	
	public TestCase() {
		super();
	}

	public Long getTestCase_id() {
		return testCase_id;
	}

	public void setTestCase_id(Long testCase_id) {
		this.testCase_id = testCase_id;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public Laboratory getLaboratory() {
		return laboratory;
	}

	public void setLaboratory(Laboratory laboratory) {
		this.laboratory = laboratory;
	}

	public Set<Input> getInputs() {
		return inputs;
	}

	public void setInputs(Set<Input> inputs) {
		this.inputs = inputs;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}



	
}
