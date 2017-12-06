package com.isl.admin.filter;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFilterOption implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String dtStart;
	private String dtEnd;
	public DateFilterOption(String dtStart, String dtEnd) {
		super();
		this.dtStart = dtStart;
		this.dtEnd = dtEnd;
	}
	public DateFilterOption() {
		super();
		this.dtStart = null;
		this.dtEnd = null;
	}	
	public String getDtStart() {
		return dtStart;
	}	
	public void setDtStart(String dtStart) {
		this.dtStart = dtStart;
	}
	
	public String getDtEnd() {
		return dtEnd;
	}	
	public void setDtEnd(String dtEnd) {
		this.dtEnd = dtEnd;
	}
	public Date getDtEndSQL() throws ParseException {		
		if(dtEnd == null || dtEnd.length() < 10)
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
		Date date = sdf.parse(dtEnd); 		
		return date; 
	}
	public Date getDtStartSQL() throws ParseException {		
		if(dtStart == null || dtStart.length() < 10)
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
		Date date = sdf.parse(dtStart); 		
		return date;
	}	
	@Override
	public String toString() {
		return "DateFilterOption [dtStart=" + dtStart + ", dtEnd=" + dtEnd + "]";
	}
}
