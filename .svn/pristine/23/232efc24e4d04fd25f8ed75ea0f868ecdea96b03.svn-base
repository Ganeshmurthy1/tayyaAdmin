package com.tayyarah.notification;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

 
@Entity
@Table(name = "notification")
public class Notification implements Serializable {

	/**
	 * @author Ramesh
	 */
	private static final long serialVersionUID = 1L;

	@Transient
	 private String transFromDate; 
	 
	 @Transient
	 private String transToDate;
	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "created_at")
	Timestamp createdAt;

	@Column(name = "updated_at", insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL")
	Timestamp updatedAt;

	@Version
	@Column(name = "version", columnDefinition = "integer DEFAULT 0", nullable = false)
	int version;
	@Column(name = "type")
	private Integer type;

	@Column(name = "description",columnDefinition="LONGTEXT")
	public String description;

	@Column(name = "company_id")
	private Integer companyId;

	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "status_id")
	private Integer statusId;

	@Column(name = "createdby")
	private Integer createdby;

	@Column(name = "from_date")
	private Date fromDate;

	@Column(name = "to_date")
	private Date toDate;

	@Column(name = "current_notification_view")
	private Boolean currentNotificationView;

	@Column(name = "is_expired")
	private Boolean isExpired;
	
	@Temporal(TemporalType.TIME)
	@Column(name = "time_interval")
	private Date timeInterval;

	@Column(name = "is_admin")
	private Boolean isAdmin;

	@Column(name = "is_email")
	private Boolean isEmail;

	

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "notification", targetEntity = NotificationDetail.class)
	@LazyCollection(LazyCollectionOption.TRUE)
	private List<NotificationDetail> details;
	
	@Column(name = "customFlag")
	private Boolean customFlag;
	@Column(name = "to_emailaddress")
	 private String toEmailAddress;
	 
	 @Column(name = "cc_emailaddress")
	 private String ccEmailAddress;
	
	 
	public String getTransFromDate() {
		return transFromDate;
	}

	public void setTransFromDate(String transFromDate) {
		this.transFromDate = transFromDate;
	}

	public String getTransToDate() {
		return transToDate;
	}

	public void setTransToDate(String transToDate) {
		this.transToDate = transToDate;
	}
	
	public Boolean getCustomFlag() {
		return customFlag;
	}

	public void setCustomFlag(Boolean customFlag) {
		this.customFlag = customFlag;
	}

	public boolean isCurrentNotificationView() {
		return currentNotificationView;
	}

	public void setCurrentNotificationView(boolean currentNotificationView) {
		this.currentNotificationView = currentNotificationView;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCreatedby() {
		return createdby;
	}

	public void setCreatedby(Integer createdby) {
		this.createdby = createdby;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public List<NotificationDetail> getDetails() {
		return details;
	}

	public void setDetails(List<NotificationDetail> details) {
		this.details = details;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Boolean getCurrentNotificationView() {
		return currentNotificationView;
	}

	public void setCurrentNotificationView(Boolean currentNotificationView) {
		this.currentNotificationView = currentNotificationView;
	}

	public Boolean getIsExpired() {
		return isExpired;
	}

	public void setIsExpired(Boolean isExpired) {
		this.isExpired = isExpired;
	}


	public Boolean getIs_admin() {
		return isAdmin;
	}

	public void setIs_admin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Boolean getIs_email() {
		return isEmail;
	}

	public void setIs_email(Boolean isEmail) {
		this.isEmail = isEmail;
	}

	
	public Date getTimeInterval() {
		return timeInterval;
	}

	public void setTimeInterval(Date timeInterval) {
		this.timeInterval = timeInterval;
	}
	public String getToEmailAddress() {
		return toEmailAddress;
	}

	public void setToEmailAddress(String toEmailAddress) {
		this.toEmailAddress = toEmailAddress;
	}

	public String getCcEmailAddress() {
		return ccEmailAddress;
	}

	public void setCcEmailAddress(String ccEmailAddress) {
		this.ccEmailAddress = ccEmailAddress;
	}
}
