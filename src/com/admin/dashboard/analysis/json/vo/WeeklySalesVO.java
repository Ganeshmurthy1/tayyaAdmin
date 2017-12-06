package com.admin.dashboard.analysis.json.vo;

import java.util.List;
import java.util.Map;

public class  WeeklySalesVO {
	private Map<String,List<WeeklySalesDataVO>> weekDayMap;
	private Map<String, WeeklySalesDataVO> comparisonMap;
	 
	public ErrorMsg getError() {
		return error;
	}
	 
	public void setError(ErrorMsg error) {
		this.error = error;
	}
	public Map<String,List<WeeklySalesDataVO>> getWeekDayMap() {
		return weekDayMap;
	}

	public void setWeekDayMap(Map<String,List<WeeklySalesDataVO>> weekDayMap) {
		this.weekDayMap = weekDayMap;
	}
	 
	public Map<String, WeeklySalesDataVO> getComparisonMap() {
		return comparisonMap;
	}

	public void setComparisonMap(Map<String, WeeklySalesDataVO> comparisonMap) {
		this.comparisonMap = comparisonMap;
	}
	private  ErrorMsg error;
}
