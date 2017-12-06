package com.lintas.admin.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "company_role")
public class CompanyRole implements Serializable {

	/**@author info raham
	 * created date:17-09-2015
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue
	@Column(name="company_roleid")
	private Long companyRoleId;
	@Column(name="is_agent" ,columnDefinition = "BOOLEAN DEFAULT false")
	private boolean isAgent;
	@Column(name="is_distributor",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean isDistributor;
	@Column(name="is_corporate",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean isCorporate;
	@Column(name="is_superUser",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean isSuperUser;
	/**
	 * @return the isAgent
	 */
	public boolean isAgent() {
		return isAgent;
	}
	/**
	 * @param isAgent the isAgent to set
	 */
	public void setAgent(boolean isAgent) {
		this.isAgent = isAgent;
	}
	/**
	 * @return the isDistributor
	 */
	public boolean isDistributor() {
		return isDistributor;
	}
	/**
	 * @param isDistributor the isDistributor to set
	 */
	public void setDistributor(boolean isDistributor) {
		this.isDistributor = isDistributor;
	}
	/**
	 * @return the isCorporate
	 */
	public boolean isCorporate() {
		return isCorporate;
	}
	/**
	 * @param isCorporate the isCorporate to set
	 */
	public void setCorporate(boolean isCorporate) {
		this.isCorporate = isCorporate;
	}
	public Long getCompanyRoleId() {
		return companyRoleId;
	}
	public void setCompanyRoleId(Long companyRoleId) {
		this.companyRoleId = companyRoleId;
	}
	public boolean isSuperUser() {
		return isSuperUser;
	}
	public void setSuperUser(boolean isSuperUser) {
		this.isSuperUser = isSuperUser;
	}


}
