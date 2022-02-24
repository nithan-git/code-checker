package com.cskku.project;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Document {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long document_id;
	private String name;
	@Lob
	@Column(columnDefinition="BLOB")
	private byte[] content;
	private long size;
	private Date upload_time;
	@OneToOne(mappedBy="document",fetch= FetchType.EAGER,cascade = CascadeType.REMOVE)
	private LabDetail labDetail;
	@OneToOne(mappedBy="document",fetch= FetchType.EAGER,cascade = CascadeType.REMOVE)
	private Work work;
	
	
	public Document() {
		super();
	}

	public Document(Long document_id, String name, long size) {
		super();
		this.document_id = document_id;
		this.name = name;
		this.size = size;
	}

	public Long getDocument_id() {
		return document_id;
	}

	public void setDocument_id(Long document_id) {
		this.document_id = document_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public Date getUpload_time() {
		return upload_time;
	}

	public void setUpload_time(Date upload_time) {
		this.upload_time = upload_time;
	}


}
