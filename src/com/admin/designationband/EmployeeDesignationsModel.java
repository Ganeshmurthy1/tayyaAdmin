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

import com.lintas.admin.common.model.OrderCustomer;
import com.lintas.admin.model.User;

/**
 * @author MANISH
 *
 */

	@Entity
	@Table(name = "employees_designation")
	public class EmployeeDesignationsModel {

		@Id
		@GeneratedValue
		@Column(name="desgId")
		private int desgId;
		
		@Column(name="companyid")
		private int companyid;
		
		@Column(name="desgCode")
		private String desgCode;
		
		@Column(name="desgName")
		private String desgName;
		
		@ManyToOne(cascade = CascadeType.ALL)
		@JoinColumn(name = "bandId", referencedColumnName = "bandId")
		private EmployeeBandModel employeeBandModel;
		
		@OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeDesignationsModels")
		private List<EmployeeDesignationModel> employeeDesignationModels;
		
		@OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeDesignationsModels")
		private List<User> user;
		
		public int getDesgId() {
			return desgId;
		}
		public void setDesgId(int desgId) {
			this.desgId = desgId;
		}
		public String getDesgCode() {
			return desgCode;
		}
		public void setDesgCode(String desgCode) {
			this.desgCode = desgCode;
		}
		public String getDesgName() {
			return desgName;
		}
		public void setDesgName(String desgName) {
			this.desgName = desgName;
		}
		public EmployeeBandModel getEmployeeBandModel() {
			return employeeBandModel;
		}
		public void setEmployeeBandModel(EmployeeBandModel employeeBandModel) {
			this.employeeBandModel = employeeBandModel;
		}
		public List<EmployeeDesignationModel> getEmployeeDesignationModels() {
			return employeeDesignationModels;
		}
		public void setEmployeeDesignationModels(List<EmployeeDesignationModel> employeeDesignationModels) {
			this.employeeDesignationModels = employeeDesignationModels;
		}
		public List<User> getUser() {
			return user;
		}
		public void setUser(List<User> user) {
			this.user = user;
		}
		public int getCompanyid() {
			return companyid;
		}
		public void setCompanyid(int companyid) {
			this.companyid = companyid;
		}
		
		
	}

