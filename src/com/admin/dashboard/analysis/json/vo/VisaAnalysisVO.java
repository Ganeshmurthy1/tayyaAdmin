package com.admin.dashboard.analysis.json.vo;

import java.util.List;

public class VisaAnalysisVO {
	private int totalCount;
	private List<VisaAnalysisDataVO> visaList=null;
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
	 
	 
	public List<VisaAnalysisDataVO> getVisaList() {
		return visaList;
	}
	public void setVisaList(List<VisaAnalysisDataVO> visaList) {
		this.visaList = visaList;
	}
}
