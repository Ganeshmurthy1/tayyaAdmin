package com.admin.dashboard.analysis.json.vo;

import java.util.List;

public class CarAnalysisVO {
	private int totalCount;
	private List<CarAnalysisDataVO> carProviderList=null;
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
	public List<CarAnalysisDataVO> getCarProviderList() {
		return carProviderList;
	}
	public void setCarProviderList(List<CarAnalysisDataVO> carProviderList) {
		this.carProviderList = carProviderList;
	}
}
