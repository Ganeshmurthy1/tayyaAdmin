package com.lintas.utility;

public class InvoiceFilter {
	private String fromDate;
	private String toDate;
	private String period;
 	private String filterCompanyType;
 	private String filterDealType;
 	private String configFilterDealType;
 	
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getFilterCompanyType() {
		return filterCompanyType;
	}
	public void setFilterCompanyType(String filterCompanyType) {
		this.filterCompanyType = filterCompanyType;
	}
	public String getFilterDealType() {
		return filterDealType;
	}
	public void setFilterDealType(String filterDealType) {
		this.filterDealType = filterDealType;
	}
	public String getConfigFilterDealType() {
		return configFilterDealType;
	}
	public void setConfigFilterDealType(String configFilterDealType) {
		this.configFilterDealType = configFilterDealType;
	};
	 
 }
