package com.lintas.admin.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.lintas.admin.common.model.Timestampable;
@Entity
@Table(name="bug_tracker_comment")
public class BugTrackerComment extends Timestampable implements Serializable {
	/**
	 * @author Yogesh
	 */
	private static final long serialVersionUID = 1L;


	@Transient
	private String createdByName;
	@Transient
	private String updatedByName;

	@Column(name="created_by",columnDefinition = "integer DEFAULT 0", nullable = false)
	private int createdBy;
	@Column(name="updated_by",columnDefinition = "integer DEFAULT 0", nullable = false)
	private int updatedBy;


	@Column(name="created_username")
	private String createdByUserName;
	@Column(name="created_user_email")
	private String createdByUserEmail;

	@Column(columnDefinition = "LONGTEXT")
	private String comments;
	@Column(name="file_path")
	private String filePath;

	@ManyToOne (cascade = CascadeType.ALL) 
	@JoinColumn(name = "bug_trarcker_id", referencedColumnName = "id")
	private BugTracker bugTracker; 	

	public String getCreatedByName() {
		return createdByName;
	}

	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}

	public String getUpdatedByName() {
		return updatedByName;
	}

	public void setUpdatedByName(String updatedByName) {
		this.updatedByName = updatedByName;
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

	public String getCreatedByUserName() {
		return createdByUserName;
	}

	public void setCreatedByUserName(String createdByUserName) {
		this.createdByUserName = createdByUserName;
	}

	public String getCreatedByUserEmail() {
		return createdByUserEmail;
	}

	public void setCreatedByUserEmail(String createdByUserEmail) {
		this.createdByUserEmail = createdByUserEmail;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public BugTracker getBugTracker() {
		return bugTracker;
	}

	public void setBugTracker(BugTracker bugTracker) {
		this.bugTracker = bugTracker;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	
}
