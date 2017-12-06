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

/**
 * @author Vimal Susai Raj
 *
 */

@Entity
@Table(name="company_bands")
public class CompanyBandEntity {

	@Id
	@GeneratedValue
	@Column(name="id")
	private long id;
	@Column(name="band_name")
	private String bandName;
	@Column(name="band_code")
	private String bandCode;
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
	public String getBandName() {
		return bandName;
	}
	public void setBandName(String bandName) {
		this.bandName = bandName;
	}
	public String getBandCode() {
		return bandCode;
	}
	public void setBandCode(String bandCode) {
		this.bandCode = bandCode;
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
