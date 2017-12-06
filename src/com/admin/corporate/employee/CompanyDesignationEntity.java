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

/**
 * @author Vimal Susai Raj
 *
 */

@Entity
@Table(name="company_designations")
public class CompanyDesignationEntity {

	@Id
	@GeneratedValue
	@Column(name="id")
	private long id;
	@Column(name="designation_name")
	private String designationName;
	@Column(name="designation_code")
	private String designationCode;
	@Column(name="companyid")
	private long companyId;
	@Column(name="company_userid")
	private long companyUserId;	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "company_department_id", referencedColumnName = "id")
	private CompanyDepartmentEntity companyDepartmentEntity;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDesignationName() {
		return designationName;
	}
	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}
	public String getDesignationCode() {
		return designationCode;
	}
	public void setDesignationCode(String designationCode) {
		this.designationCode = designationCode;
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
	public CompanyDepartmentEntity getCompanyDepartmentEntity() {
		return companyDepartmentEntity;
	}
	public void setCompanyDepartmentEntity(CompanyDepartmentEntity companyDepartmentEntity) {
		this.companyDepartmentEntity = companyDepartmentEntity;
	}
	
	
}
