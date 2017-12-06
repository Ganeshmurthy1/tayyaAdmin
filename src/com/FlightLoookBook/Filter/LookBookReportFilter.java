package com.FlightLoookBook.Filter;

import java.io.Serializable;

import com.isl.admin.filter.Filter;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.CompanyConfig;
import com.lintas.admin.model.User;

public class LookBookReportFilter extends Filter implements Serializable{

	/**
	 * @updatedBy : Manish Samrat (21/07/2017),
	 */
	private static final long serialVersionUID = 1L;
	private int companyId;
	private int userId;
	private String companyName;
	private String userName;
	private String showtype;
	private int configId;
	private String companyType;
	private String b2cType;
	
	public LookBookReportFilter() {
		super();
		this.companyId = -1;
		this.userId = -1;
		this.companyName = null;
		this.showtype = null;
		this.userName = null;
		this.companyType = null;
		this.b2cType = null;
		this.configId = -1;
	}
	public LookBookReportFilter(CompanyConfig loginCompanyConfig, Company loginCompany, User loginUser, int reportType) {
		super(loginCompanyConfig, loginCompany, loginUser, reportType);
		// TODO Auto-generated constructor stub
	}
	public LookBookReportFilter(CompanyConfig loginCompanyConfig, Company loginCompany, User loginUser) {
		super(loginCompanyConfig, loginCompany, loginUser);
		// TODO Auto-generated constructor stub
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getShowtype() {
		return showtype;
	}

	public void setShowtype(String showtype) {
		this.showtype = showtype;
	}
	public String getCompanyType() {
		return companyType;
	}
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}
	public int getConfigId() {
		return configId;
	}
	public void setConfigId(int configId) {
		this.configId = configId;
	}
	@Override
	public String toString() {
		return "LookBookReportFilter [companyId=" + companyId + ", userId=" + userId + ", companyName=" + companyName
				+ ", userName=" + userName + ", companyType=" + companyType + ", b2cType=" + b2cType + ", configId=" + configId + ", showtype=" + showtype + "]";
	}
	public String getB2cType() {
		return b2cType;
	}
	public void setB2cType(String b2cType) {
		this.b2cType = b2cType;
	}
	
}
