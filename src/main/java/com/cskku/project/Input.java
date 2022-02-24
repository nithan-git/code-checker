package com.cskku.project;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Input {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long input_id;
	@ManyToOne
	@JoinColumn(name = "testCase_id", nullable=false)
	private TestCase testCase;
	@Column(nullable = false)
	private String value;
	@Column(nullable = false)
	private String type;
	
	public Input() {
		super();
	}

	public Long getInput_id() {
		return input_id;
	}

	public void setInput_id(Long input_id) {
		this.input_id = input_id;
	}

	public TestCase getTestCase() {
		return testCase;
	}

	public void setTestCase(TestCase testCase) {
		this.testCase = testCase;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
	
}
