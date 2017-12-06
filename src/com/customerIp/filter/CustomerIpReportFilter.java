/**
 * 
 */
package com.customerIp.filter;

import java.io.Serializable;

import com.isl.admin.filter.Filter;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.CompanyConfig;
import com.lintas.admin.model.User;

/**
 * @author      : Manish Samrat
 * @createdAt   : 24/07/2017
 * @version
 * @updateaBy   :  
 */
public class CustomerIpReportFilter  extends Filter implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int companyId;
	private int userId;
	private int id;
	private String companyName;
	private String userName;
	private String showtype;
	private int configId;
	private String companyType;
	private String ip;
	private boolean xlsdownloadFilter;
	
	
	public CustomerIpReportFilter() {
		super();
		this.companyId = -1;
		this.userId = -1;
		this.companyName = null;
		this.showtype = null;
		this.userName = null;
		this.companyType = null;
		this.ip = null;
		this.xlsdownloadFilter = false;
		this.configId = -1;
	}
	public CustomerIpReportFilter(CompanyConfig loginCompanyConfig, Company loginCompany, User loginUser, int reportType) {
		super(loginCompanyConfig, loginCompany, loginUser, reportType);
	}
	public CustomerIpReportFilter(CompanyConfig loginCompanyConfig, Company loginCompany, User loginUser) {
		super(loginCompanyConfig, loginCompany, loginUser);
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getConfigId() {
		return configId;
	}
	public void setConfigId(int configId) {
		this.configId = configId;
	}
	@Override
	public String toString() {
		return "CustomerIpReportFilter [companyId=" + companyId + ", userId=" + userId + ", xlsdownloadFilter=" + xlsdownloadFilter + ", companyName=" + companyName
				+ ", userName=" + userName + ", configId=" + configId + ", companyType=" + companyType + ", ip=" + ip + ", showtype=" + showtype + "]";
	}
	public String getCompanyType() {
		return companyType;
	}
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public boolean isXlsdownloadFilter() {
		return xlsdownloadFilter;
	}
	public void setXlsdownloadFilter(boolean xlsdownloadFilter) {
		this.xlsdownloadFilter = xlsdownloadFilter;
	}
}
