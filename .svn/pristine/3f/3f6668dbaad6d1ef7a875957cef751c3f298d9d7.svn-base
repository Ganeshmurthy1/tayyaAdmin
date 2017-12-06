package com.admin.corporate.employee;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.lintas.admin.model.BugTracker;

/**
 * @author Vimal Susai Raj
 *
 */

@Entity
@Table(name="company_cost_centers")
public class CompanyCostCenterEntity {

	@Id
	@GeneratedValue
	@Column(name="id")
	private long id;
	@Column(name="costcenter_code")
	private String costCenterCode;
	@Column(name="costcenter_name")
	private String costCenterName;
	@Column(name="companyid")
	private long companyId;
	@Column(name="company_userid")
	private long companyUserId;	

	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCostCenterCode() {
		return costCenterCode;
	}
	public void setCostCenterCode(String costCenterCode) {
		this.costCenterCode = costCenterCode;
	}
	public String getCostCenterName() {
		return costCenterName;
	}
	public void setCostCenterName(String costCenterName) {
		this.costCenterName = costCenterName;
	}
	public long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
	public long getCompanyUserId() {
		return companyUserId;
	}
	public void setCompanyUserId(long companyUserId) {
		this.companyUserId = companyUserId;
	}


}
