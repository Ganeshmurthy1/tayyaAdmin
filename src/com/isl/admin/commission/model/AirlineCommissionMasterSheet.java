package com.isl.admin.commission.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="airline_commission_master_sheet")
public class AirlineCommissionMasterSheet implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name="id") 
	private Long id;
	@Column(name="name",columnDefinition="VARCHAR(100)") 
	private String  name;
	@Column(name="description") 
	private String  description;
	@Column(name="is_active",columnDefinition="BIT(1) default 0") 
	private boolean  isActive;
	@Column(name="created_by_user_id") 
	private int  createdByUserID;
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
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public int getCreatedByUserID() {
		return createdByUserID;
	}
	public void setCreatedByUserID(int createdByUserID) {
		this.createdByUserID = createdByUserID;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
