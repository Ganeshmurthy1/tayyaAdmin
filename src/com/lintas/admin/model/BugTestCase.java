package com.lintas.admin.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lintas.admin.common.model.Timestampable;

@Entity
@Table(name="bug_test_case")
public class BugTestCase extends Timestampable{

	/**
	 * @author Basha
	 * o3/27/2017
	 */
	private static final long serialVersionUID = 1L;
	 
	
	@Column(name="test_case")
	private String testCase;
	@Column(name="is_verify" , columnDefinition="BIT(1)   default 0")
	private boolean verify;
	@Column(name="created_username")
	private String createdByUserName;

	@Column(name="created_by",columnDefinition = "integer DEFAULT 0", nullable = false)
	private int createdBy;
	@Column(name="updated_by",columnDefinition = "integer DEFAULT 0", nullable = false)
	private int updatedBy;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "bug_tracker_id", referencedColumnName = "id")
	private BugTracker bugTracker;
	
	public String getTestCase() {
		return testCase;
	}
	public void setTestCase(String testCase) {
		this.testCase = testCase;
	}
	public boolean isVerify() {
		return verify;
	}
	public void setVerify(boolean verify) {
		this.verify = verify;
	}
	public BugTracker getBugTracker() {
		return bugTracker;
	}
	public void setBugTracker(BugTracker bugTracker) {
		this.bugTracker = bugTracker;
	}
	public String getCreatedByUserName() {
		return createdByUserName;
	}
	public void setCreatedByUserName(String createdByUserName) {
		this.createdByUserName = createdByUserName;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public int getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}
	 

}
