package com.isl.admin.filter;

import java.io.Serializable;

import com.lintas.admin.model.Company;
import com.lintas.admin.model.CompanyConfig;
import com.lintas.admin.model.User;

public class Filter implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int REPORTS_ALL =0;
	public static final int REPORTS_MINE =1;
	public static final int REPORTS_MY_AFFILIATES =2;
	public static final int REPORTS_MY_DISTRIBUTORS =3;
	public static final int REPORTS_MY_AGENCIES =4;	
	public static final int REPORTS_ALL_AFFILIATES =5;
	public static final int REPORTS_ALL_DISTRIBUTORS =6;
	public static final int REPORTS_ALL_AGENCIES =7;
	public static final int REPORTS_MY_CORPORATES =8;	
	public static final int ORDERS_ALL =10;	
	public static final int AGENT_INVOICES_ALL =11;	

	private Company loginCompany;
	private CompanyConfig loginCompanyConfig;
	private User loginUser;	
	private int reportType;

	public void setReportType(int reportType) {
		this.reportType = reportType;
	}
	public Filter() {
		super();
		this.reportType = REPORTS_MINE;		
	}
	@Override
	public String toString() {
		return "Filter [loginCompany=" + loginCompany + ", loginCompanyConfig=" + loginCompanyConfig + ", loginUser="
				+ loginUser + "]";
	}
	public Filter(CompanyConfig loginCompanyConfig, Company loginCompany, User loginUser) {
		super();
		this.reportType = REPORTS_MINE;
		this.loginCompanyConfig = loginCompanyConfig;
		this.loginCompany = loginCompany;
		this.loginUser = loginUser;
	}	
	public Filter(CompanyConfig loginCompanyConfig, Company loginCompany, User loginUser, int reportType) {
		super();
		this.reportType = reportType;
		this.loginCompanyConfig = loginCompanyConfig;
		this.loginCompany = loginCompany;
		this.loginUser = loginUser;
	}	
	public Company getLoginCompany() {
		return loginCompany;
	}
	public void setLoginCompany(Company loginCompany) {
		this.loginCompany = loginCompany;
	}
	public User getLoginUser() {
		return loginUser;
	}
	public void setLoginUser(User loginUser) {
		this.loginUser = loginUser;
	}

	public int getReportType() {
		return reportType;
	}




}
