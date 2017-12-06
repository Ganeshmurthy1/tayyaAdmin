package com.isl.admin.filter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BrowsingHistoryFilter extends Filter implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private String pageName;
	private int pageId;
	private String companyName;
	private DateFilterOption  dateFilter;
	private List<String> companyList;


	public BrowsingHistoryFilter() {
		super();
		this.userName = null;
		this.pageName  = null;
		this.pageId=0;
		this.companyName = null;
		this.dateFilter = new DateFilterOption();
		this.companyList = new ArrayList<>();
	}
 


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public List<String> getCompanyList() {
		return companyList;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public void setCompanyList(List<String> companyList) {
		this.companyList = companyList;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public DateFilterOption getDateFilter() {
		return dateFilter;
	}

	public void setDateFilter(DateFilterOption dateFilter) {
		this.dateFilter = dateFilter;
	}

	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}
}