package com.admin.dashboard.analysis.json.vo;

import java.util.List;

public class MiscellaneousAnalysisVO {
	private int totalCount;
	private List<MiscellaneousAnalysisDataVO> miscellaneousList=null;
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
	public List<MiscellaneousAnalysisDataVO> getMiscellaneousList() {
		return miscellaneousList;
	}
	public void setMiscellaneousList(List<MiscellaneousAnalysisDataVO> miscellaneousList) {
		this.miscellaneousList = miscellaneousList;
	}
}
