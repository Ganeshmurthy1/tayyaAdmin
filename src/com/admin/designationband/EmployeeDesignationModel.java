/**
 * 
 */
package com.admin.designationband;

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
 * @author Manish Kumar
 *
 */
@Entity
@Table(name = "employee_designation")
public class EmployeeDesignationModel {

	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	@Column(name="bandId")
	private int bandId;
	
	@Column(name="name")
	private String userName;
	
	@Column(name="bandName")
	private String bandName;
	
	@Column(name="designation")
	private String designation;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "desgId", referencedColumnName = "desgId")
	private EmployeeDesignationsModel employeeDesignationsModels;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBandName() {
		return bandName;
	}
	public void setBandName(String bandName) {
		this.bandName = bandName;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getBandId() {
		return bandId;
	}
	public void setBandId(int bandId) {
		this.bandId = bandId;
	}
	public EmployeeDesignationsModel getEmployeeDesignationsModels() {
		return employeeDesignationsModels;
	}
	public void setEmployeeDesignationsModels(EmployeeDesignationsModel employeeDesignationsModels) {
		this.employeeDesignationsModels = employeeDesignationsModels;
	}
	
	
}
