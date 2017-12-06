package com.admin.dashboard.analysis.json.vo;

import java.util.List;

public class HotelAnalysisVO {
	private int totalCount;
	private List<HotelAnalysisDataVO>  hotelList=null;
	private List<HotelAnalysisDataVO> apiProviderList=null;
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
	public List<HotelAnalysisDataVO> getHotelList() {
		return hotelList;
	}
	public void setHotelList(List<HotelAnalysisDataVO> hotelList) {
		this.hotelList = hotelList;
	}
	public List<HotelAnalysisDataVO> getApiProviderList() {
		return apiProviderList;
	}
	public void setApiProviderList(List<HotelAnalysisDataVO> apiProviderList) {
		this.apiProviderList = apiProviderList;
	}
}
