package com.admin.dashboard.analysis.json.vo;

import java.util.List;

public class InsuranceAnalysisVO {
	private int totalCount;
	private List<InsuranceAnalysisDataVO> insuranceList=null;
	private  ErrorMsg error;
	public ErrorMsg getError() {
		return error;
	}
	public void setError(ErrorMsg error) {
		this.error = error;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	 
	public List<InsuranceAnalysisDataVO> getInsuranceList() {
		return insuranceList;
	}
	public void setInsuranceList(List<InsuranceAnalysisDataVO> insuranceList) {
		this.insuranceList = insuranceList;
	}
}
