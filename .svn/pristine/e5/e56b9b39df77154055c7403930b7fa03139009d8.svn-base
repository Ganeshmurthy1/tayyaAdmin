package com.admin.corporate.employee;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * @author Vimal Susai Raj
 *
 */

@Entity
@Table(name="company_department")
public class CompanyDepartmentEntity {

	@Id
	@GeneratedValue
	@Column(name="id")
	private long id;
	@Column(name="department_name")
	private String departmentName;
	@Column(name="department_code")
	private String departmentCode;
	@Column(name="companyid")
	private long companyId;
	@Column(name="company_userid")
	private long companyUserId;

	@OneToMany(targetEntity = CompanyDesignationEntity.class, cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<CompanyDesignationEntity> companyDesignationEntityList;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
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
	public List<CompanyDesignationEntity> getCompanyDesignationEntityList() {
		return companyDesignationEntityList;
	}
	public void setCompanyDesignationEntityList(List<CompanyDesignationEntity> companyDesignationEntityList) {
		this.companyDesignationEntityList = companyDesignationEntityList;
	}
	
}
