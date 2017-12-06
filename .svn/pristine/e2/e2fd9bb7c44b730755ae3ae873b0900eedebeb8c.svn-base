/**
 * 
 */
package com.admin.designationband;
import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.lintas.admin.flight.model.FlightOrderCustomer;

@Entity
@Table(name="employee_designation_band")
public class EmployeeBandModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue
	private int bandId;
	private String bandName;
	private String bandCode;
	@Column(name="companyid")
	private int companyid;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeBandModel")
	private List<EmployeeDesignationsModel> employeeDesignationsModels;
	
	public int getBandId() {
		return bandId;
	}
	public void setBandId(int bandId) {
		this.bandId = bandId;
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
	public List<EmployeeDesignationsModel> getEmployeeDesignationsModels() {
		return employeeDesignationsModels;
	}
	public void setEmployeeDesignationsModels(List<EmployeeDesignationsModel> employeeDesignationsModels) {
		this.employeeDesignationsModels = employeeDesignationsModels;
	}
	public int getCompanyid() {
		return companyid;
	}
	public void setCompanyid(int companyid) {
		this.companyid = companyid;
	}
	
}
