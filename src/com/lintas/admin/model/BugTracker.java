package com.lintas.admin.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.lintas.admin.DAO.UserDAO;
import com.lintas.admin.common.model.Timestampable;
/*import com.tayyarah.hotel.model.HotelRoomDetails;*/
@Entity
@Table(name="bug_tracker")
public class BugTracker extends Timestampable implements Serializable {
	/**
	 * @author Basha
	 */
	private static final long serialVersionUID = 1L;

	@Transient
	private String tag;
	private boolean active;
	@Transient
	private String createdByName;
	@Transient
	private String updatedByName;
	@Transient
	private String createDate;
	@Transient
	private String assignedToName;
	@Transient
	private String assignedByName;

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
	private boolean statusChanged;

	@Column(name="created_by",columnDefinition = "integer DEFAULT 0", nullable = false)
	private int createdBy;
	@Column(name="updated_by",columnDefinition = "integer DEFAULT 0", nullable = false)
	private int updatedBy;
	@Column(name="ref_no")
	private String referenceNo;

	@Column(name="assign_to" ,columnDefinition = "integer DEFAULT 0", nullable = false)
	private int assignTo;
	@Column(name="assign_by" ,columnDefinition = "integer DEFAULT 0", nullable = false)
	private int assignedBy;

	@Column(name="_level")
	private String level;
	@Column(name="bug_type")
	private String bugType;
	@Column(name="_status")
	private String status;

	@Column(name="total_working_hours")
	private Integer totalWorkingHours;
	@Column(name="total_extra_hours")
	private Integer totalExtraHours;

	@Column(name="total_estimated_hours")
	private Integer totalEstimatedHours;
	@Column(name="total_developer_estimated_hours")
	private Integer totalDeveloperEstimatedHours;

	@Column(name="created_username")
	private String createdByUserName;
	@Column(name="created_user_email")
	private String createdByUserEmail;

	@Column(name="assign_date")
	private Date assignDate;

	@Column(name="start_to_work_date")
	private Date startToWorkDate;

	@Column(name="work_finish_date")
	private Date workFinishDate;

	@Column(columnDefinition = "LONGTEXT")
	private String  title;
	@Column(columnDefinition = "LONGTEXT")
	private String description;


	@OneToMany(cascade = CascadeType.ALL, mappedBy = "bugTracker")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<BugTestCase> bugTestCases;

	@OneToMany(targetEntity = BugTrackerHistory.class,mappedBy = "bugTracker", cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<BugTrackerHistory> bugTrackerHistoryList;

	@OneToMany(targetEntity = BugTrackerHistory.class,mappedBy = "bugTracker", cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<BugTrackerComment> bugTrackerCommentList;

	public List<BugTrackerHistory> getBugTrackerHistoryList() {
		return bugTrackerHistoryList;
	}
	public void setBugTrackerHistoryList(List<BugTrackerHistory> bugTrackerHistoryList) {
		this.bugTrackerHistoryList = bugTrackerHistoryList;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getReferenceNo() {
		return referenceNo;
	}
	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
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
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	/*public List<BugTag> getBugTagList() {
		return bugTagList;
	}
	public void setBugTagList(List<BugTag> bugTagList) {
		this.bugTagList = bugTagList;
	} */
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public List<BugTestCase> getBugTestCases() {
		return bugTestCases;
	}
	public void setBugTestCases(List<BugTestCase> bugTestCases) {
		this.bugTestCases = bugTestCases;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getBugType() {
		return bugType;
	}
	public void setBugType(String bugType) {
		this.bugType = bugType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getTotalWorkingHours() {
		return totalWorkingHours;
	}
	public void setTotalWorkingHours(Integer totalWorkingHours) {
		this.totalWorkingHours = totalWorkingHours;
	}
	public Integer getTotalExtraHours() {
		return totalExtraHours;
	}
	public void setTotalExtraHours(Integer totalExtraHours) {
		this.totalExtraHours = totalExtraHours;
	}
	public Integer getTotalEstimatedHours() {
		return totalEstimatedHours;
	}
	public void setTotalEstimatedHours(Integer totalEstimatedHours) {
		this.totalEstimatedHours = totalEstimatedHours;
	}
	public Integer getTotalDeveloperEstimatedHours() {
		return totalDeveloperEstimatedHours;
	}
	public void setTotalDeveloperEstimatedHours(Integer totalDeveloperEstimatedHours) {
		this.totalDeveloperEstimatedHours = totalDeveloperEstimatedHours;
	}
	public int getAssignTo() {
		return assignTo;
	}
	public void setAssignTo(int assignTo) {
		this.assignTo = assignTo;
	}
	public int getAssignedBy() {
		return assignedBy;
	}
	public void setAssignedBy(int assignedBy) {
		this.assignedBy = assignedBy;
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
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
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
	public boolean isStatusChanged() {
		return statusChanged;
	}
	public void setStatusChanged(boolean statusChanged) {
		this.statusChanged = statusChanged;
	}

}
