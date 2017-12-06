package com.tayyarah.notification;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
 
 
@Entity
@Table(name = "notification_detail")
public class NotificationDetail  implements Serializable {
	 
	/**
	 * @author Ramesh
	 */
	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue
	private Long id;
	
	@Column(name = "created_at")
	Timestamp createdAt;

	@Column(name = "updated_at",
			insertable = false,
			columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL")
	Timestamp updatedAt;

	@Version
	@Column(name = "version",columnDefinition = "integer DEFAULT 0", nullable = false)
	int version;
	 
	@Column(name = "inventory_id")
	private String inventoryId;	
	@Column(name = "type")
	private Integer type;	
	@Column(name = "description",columnDefinition="LONGTEXT")
	private String description;	
	
	@Column(name = "viewState",columnDefinition="BIT(1) default false")
	private boolean viewState;
	
	@Column(name="comments",columnDefinition="LONGTEXT")
	private String comments;
	
	 
	@ManyToOne(cascade = CascadeType.ALL)	
	@JoinColumn(name = "notification_id", referencedColumnName = "id")	
	private Notification notification;

	public Notification getNotification() {
		return notification;
	}
	public void setNotification(Notification history) {
		this.notification = history;
	} 
	public String getInventoryId() {
		return inventoryId;
	}
	
	public boolean isViewState() {
		return viewState;
	}
	public void setViewState(boolean viewState) {
		this.viewState = viewState;
	}
	public void setInventoryId(String inventoryId) {
		this.inventoryId = inventoryId;
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
	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Timestamp getCreatedAt()
	{
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt)
	{
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt()
	{
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt)
	{
		this.updatedAt = updatedAt;
	}

	public int getVersion()
	{
		return version;
	}

	public void setVersion(int version)
	{
		this.version = version;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}

}
