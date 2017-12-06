package com.lintas.admin.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class BugTag implements Serializable{

	/**
	 * @author Basha
	 */
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue
	Long id;
 
	private String tag;
	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn(name = "bug_request_id", referencedColumnName = "id")
	private BugTracker bugTrackerRequest; 
	public BugTracker getBugTrackerRequest() {
		return bugTrackerRequest;
	}
	public void setBugTrackerRequest(BugTracker bugTrackerRequest) {
		this.bugTrackerRequest = bugTrackerRequest;
	}
	
	public String getTag() {
		return tag;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	 
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	  
}
