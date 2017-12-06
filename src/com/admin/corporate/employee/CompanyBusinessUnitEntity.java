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

import com.lintas.admin.model.BugTrackerHistory;

/**
 * @author Vimal Susai Raj
 *
 */

@Entity
@Table(name="company_business_unit")
public class CompanyBusinessUnitEntity {

	@Id
	@GeneratedValue
	@Column(name="id")
	private long id;
	@Column(name="business_unit_name")
	private String businessUnitName;
	@Column(name="business_unit_code")
	private String businessUnitCode;
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
	public String getBusinessUnitName() {
		return businessUnitName;
	}
	public void setBusinessUnitName(String businessUnitName) {
		this.businessUnitName = businessUnitName;
	}
	public String getBusinessUnitCode() {
		return businessUnitCode;
	}
	public void setBusinessUnitCode(String businessUnitCode) {
		this.businessUnitCode = businessUnitCode;
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
