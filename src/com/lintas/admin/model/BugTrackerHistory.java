package com.lintas.admin.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.lintas.admin.DAO.UserDAO;
import com.lintas.admin.common.model.Timestampable;
import com.lintas.utility.DateConversion;

@Entity
@Table(name="bug_tracker_history")
public class BugTrackerHistory extends Timestampable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Transient
	private String transAssignedDate;
	@Transient
	private String  transStartToWorkDate;
	@Transient
	private String   transWorkFinishDate;
	@Transient
	private String   createdDate;
	@Transient
	private String   updatedDate;
	@Transient
	private String   assignedToName;
	@Transient
	private String   assignedByName;
	@Transient
	private String createdByName;
	@Transient
	private String updatedByName;
	@Transient
	private String createDate;
	
	@Column(name="assign_to",columnDefinition = "integer DEFAULT 0", nullable = false)
	private int assignTo;
	@Column(name="assign_by",columnDefinition = "integer DEFAULT 0", nullable = false)
	private int assignedBy;
	@Column(name="created_by")
	private String createdBy;
	@Column(name="updated_by")
	private String updatedBy;
	@Column(name="file_path")
	private String filePath;
	@ManyToOne (cascade = CascadeType.ALL) 
	@JoinColumn(name = "bug_trarcker_id", referencedColumnName = "id")
	private BugTracker bugTracker; 

	@Column(name="_level")
	private String level;
	@Column(name="bug_type")
	private String bugType;
	@Column(name="_status")
	private String status;

	@Column(name="working_hours")
	private Integer workingHours;
	@Column(name="extra_hours")
	private Integer extraHours;

	@Column(name="estimated_hours")
	private Integer estimatedHours;
	@Column(name="developer_estimated_hours")
	private Integer developerEstimatedHours;
	
	@Column(name="updated_username")
	private String Username;
	@Column(name="updated_user_email")
	private String userEmail;

	
	@Column(name="assign_date")
	private Date assignDate;

	@Column(name="start_to_work_date")
	private Date startToWorkDate;

	@Column(name="work_finish_date")
	private Date workFinishDate;

	
	public String getTransAssignedDate() {
		return transAssignedDate;
	}
	public void setTransAssignedDate(String transAssignedDate) {
		this.transAssignedDate = transAssignedDate;
	}
	public String getTransStartToWorkDate() {
		return transStartToWorkDate;
	}
	public void setTransStartToWorkDate(String transStartToWorkDate) {
		this.transStartToWorkDate = transStartToWorkDate;
	}
	public String getTransWorkFinishDate() {
		return transWorkFinishDate;
	}
	public void setTransWorkFinishDate(String transWorkFinishDate) {
		this.transWorkFinishDate = transWorkFinishDate;
	}

	public Date getAssignDate() {
		return assignDate;
	}
	public void setAssignDate(Date assignDate) {
		this.assignDate = assignDate;
	}
	public Date getStartToWorkDate() {
		return startToWorkDate;
	}
	public void setStartToWorkDate(Date startToWorkDate) {
		this.startToWorkDate = startToWorkDate;
	}
	public Date getWorkFinishDate() {
		return workFinishDate;
	}
	public void setWorkFinishDate(Date workFinishDate) {
		this.workFinishDate = workFinishDate;
	}
	public String getBugType() {
		return bugType;
	}
	public void setBugType(String bugType) {
		this.bugType = bugType;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public int getAssignedBy() {
		return assignedBy;
	}
	public void setAssignedBy(int assignedBy) {
		this.assignedBy = assignedBy;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public BugTracker getBugTracker() {
		return bugTracker;
	}
	public void setBugTracker(BugTracker bugTracker) {
		this.bugTracker = bugTracker;
	}
	public int getAssignTo() {
		return assignTo;
	}
	public void setAssignTo(int assignTo) {
		this.assignTo = assignTo;
	}
	public String getCreatedDate() {
		if(createdDate==null){
			createdDate=DateConversion.convertDateToStringToDate(getCreatedAt(),"dd-MM-yyyy HH:mm");
		}
		
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getUpdatedDate() {
		if(updatedDate==null){
			updatedDate=DateConversion.convertDateToStringToDate(getCreatedAt(),"dd-MM-yyyy HH:mm");
		}
		return updatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
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
	public String getCreateDate() {
		return createDate;
	}
	public Integer getWorkingHours() {
		return workingHours;
	}
	public void setWorkingHours(Integer workingHours) {
		this.workingHours = workingHours;
	}
	public Integer getExtraHours() {
		return extraHours;
	}
	public void setExtraHours(Integer extraHours) {
		this.extraHours = extraHours;
	}
	public Integer getEstimatedHours() {
		return estimatedHours;
	}
	public void setEstimatedHours(Integer estimatedHours) {
		this.estimatedHours = estimatedHours;
	}
	public Integer getDeveloperEstimatedHours() {
		return developerEstimatedHours;
	}
	public void setDeveloperEstimatedHours(Integer developerEstimatedHours) {
		this.developerEstimatedHours = developerEstimatedHours;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getAssignedToName() {
		if(assignedToName==null){
			if(getAssignTo()==0){
				assignedToName="UnKnown";
			}
			else{
				User createdBy=new UserDAO().getUserDetailsByUserId(getAssignTo()).get(0);	
				assignedToName=createdBy.getUsername();
			}
		}
		
		return assignedToName;
	}
	public void setAssignedToName(String assignedToName) {
		this.assignedToName = assignedToName;
	}
	public String getAssignedByName() {
		if(assignedByName==null){
			if(getAssignedBy()==0){
				assignedByName="UnKnown";
			}
			else{
				User createdBy=new UserDAO().getUserDetailsByUserId(getAssignedBy()).get(0);	
				assignedByName=createdBy.getUsername();
			}
		}
		return assignedByName;
	}
	public void setAssignedByName(String assignedByName) {
		this.assignedByName = assignedByName;
	}
	
}
