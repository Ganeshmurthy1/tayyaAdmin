package com.admin.api.analytics.pojo;

import java.util.List;

import com.admin.dashboard.analysis.json.vo.ErrorMsg;

public class MonthlyAndWeeklyJsonCommonPojo {
	private String Date;
	private int count;
	
	
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
	

}
