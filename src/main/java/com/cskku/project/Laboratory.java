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
public class Laboratory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long laboratory_id;
	@ManyToOne
	@JoinColumn(name = "classRoom_id", nullable=false)
	private ClassRoom classRoom;
	@Column(nullable = false)
	private String name;
	private String description;
	@Column(nullable = false)
	private String functionName;
	@OneToMany(mappedBy="laboratory",fetch= FetchType.EAGER,cascade = CascadeType.REMOVE)
	@OrderBy("labDetail_id") 
	private Set<LabDetail> labDetails;
	@OneToMany(mappedBy="laboratory",fetch= FetchType.EAGER,cascade = CascadeType.REMOVE)
	@OrderBy("work_id") 
	private Set<Work> works;
	@OneToMany(mappedBy="laboratory",fetch= FetchType.EAGER,cascade = CascadeType.REMOVE)
	@OrderBy("testCase_id") 
	private Set<TestCase> testCases;
	
	public Laboratory() {
		super();
	}

	public Long getLaboratory_id() {
		return laboratory_id;
	}

	public void setLaboratory_id(Long laboratory_id) {
		this.laboratory_id = laboratory_id;
	}

	public ClassRoom getClassRoom() {
		return classRoom;
	}

	public void setClassRoom(ClassRoom classRoom) {
		this.classRoom = classRoom;
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

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public Set<LabDetail> getLabDetails() {
		return labDetails;
	}

	public void setLabDetails(Set<LabDetail> labDetails) {
		this.labDetails = labDetails;
	}

	public Set<Work> getWorks() {
		return works;
	}

	public void setWorks(Set<Work> works) {
		this.works = works;
	}

	public Set<TestCase> getTestCases() {
		return testCases;
	}

	public void setTestCases(Set<TestCase> testCases) {
		this.testCases = testCases;
	}


}
