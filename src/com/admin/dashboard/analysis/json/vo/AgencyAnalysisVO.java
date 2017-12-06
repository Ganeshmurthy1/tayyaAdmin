package com.admin.dashboard.analysis.json.vo;

import java.math.BigDecimal;
import java.util.List;

public class AgencyAnalysisVO {
	private BigDecimal totalAmount=null;
	private List<AgencyAnalysisDataVO>  corporateList=null;
	private List<AgencyAnalysisDataVO>  distributorList=null;
	private List<AgencyAnalysisDataVO> agencyList=null;
	private  ErrorMsg error;
	public List<AgencyAnalysisDataVO> getCorporateList() {
		return corporateList;
	}
	public ErrorMsg getError() {
		return error;
	}
	public void setCorporateList(List<AgencyAnalysisDataVO> corporateList) {
		this.corporateList = corporateList;
	}
	public void setError(ErrorMsg error) {
		this.error = error;
	}
	public List<AgencyAnalysisDataVO> getAgencyList() {
		return agencyList;
	}
	public void setAgencyList(List<AgencyAnalysisDataVO> agencyList) {
		this.agencyList = agencyList;
	}
	 
	public List<AgencyAnalysisDataVO> getDistributorList() {
		return distributorList;
	}
	public void setDistributorList(List<AgencyAnalysisDataVO> distributorList) {
		this.distributorList = distributorList;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	
}
