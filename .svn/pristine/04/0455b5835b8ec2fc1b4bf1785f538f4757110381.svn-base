package com.FlightLoookBook.Filter;

import java.io.Serializable;
import java.util.List;


import com.isl.admin.page.Page;


public class LookBookFilterPage extends Page implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<LookBookReportData> items;
	private LookBookReportData CurrentLookBookReportData;
	private LookBookReportFilter lookBookReportFilter;
	
	public void setLookBookReportFilter(LookBookReportFilter lookBookReportFilter) {
		this.lookBookReportFilter = lookBookReportFilter;
	}
	public LookBookFilterPage(LookBookReportFilter lookBookReportFilter) {
		super();
		this.lookBookReportFilter = lookBookReportFilter;
		// TODO Auto-generated constructor stub
	}
	public LookBookFilterPage() {
		super();
		this.lookBookReportFilter = new LookBookReportFilter();
		 // TODO Auto-generated constructor stub
	}
	public LookBookFilterPage(boolean isPagination, boolean isFilterEnabled, int maxItems, int currentPageIndex,
			int availablePages, int availableItems, LookBookReportFilter lookBookReportFilter) {
		super(isPagination, isFilterEnabled, maxItems, currentPageIndex, availablePages, availableItems);
		this.lookBookReportFilter = lookBookReportFilter;
		// TODO Auto-generated constructor stub
	}
	public LookBookFilterPage(boolean isPagination, boolean isFilterEnabled, int maxItems, LookBookReportFilter lookBookReportFilter) {
		super(isPagination, isFilterEnabled, maxItems);
		this.lookBookReportFilter = lookBookReportFilter;
		// TODO Auto-generated constructor stub
	}
	public List<LookBookReportData> getItems() {
		return items;
	}
	public void setItems(List<LookBookReportData> items) {
		this.items = items;
	}
	public LookBookReportData getCurrentLookBookReportData() {
		return CurrentLookBookReportData;
	}
	public void setCurrentLookBookReportData(LookBookReportData currentLookBookReportData) {
		CurrentLookBookReportData = currentLookBookReportData;
	}
	public LookBookReportFilter getLookBookReportFilter() {
		return lookBookReportFilter;
	}
	

}
