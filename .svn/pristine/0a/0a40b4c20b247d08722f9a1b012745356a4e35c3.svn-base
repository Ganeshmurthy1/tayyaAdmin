package com.lintas.admin.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="sales_lead_gen")
public class SalesLeadGeneration implements Serializable{
	/**
	 * 
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;
	//@Column(name="user_id")
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user; 
	@Column(name="sales_person_remarks")
	private String  salesPersonRemarks;
	@Column(name="company_remarks")
	private String companyRemarks;
	@Column(name="lead_type")
	private String leadType;
	
	@Column(name="child_user_id",columnDefinition="INT(11) default 0")
	private int childUserId;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSalesPersonRemarks() {
		return salesPersonRemarks;
	}
	public void setSalesPersonRemarks(String salesPersonRemarks) {
		this.salesPersonRemarks = salesPersonRemarks;
	}
	public String getCompanyRemarks() {
		return companyRemarks;
	}

	public void setCompanyRemarks(String companyRemarks) {
		this.companyRemarks = companyRemarks;
	}
	public String getLeadType() {
		return leadType;
	}
	public void setLeadType(String leadType) {
		this.leadType = leadType;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getChildUserId() {
		return childUserId;
	}
	public void setChildUserId(int childUserId) {
		this.childUserId = childUserId;
	}

	
	

}
