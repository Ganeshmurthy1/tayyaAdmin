package com.isl.admin.page;

import java.io.Serializable;
import java.util.List;

import com.isl.admin.filter.BugReportFilter;
import com.lintas.admin.model.BugReportData;

public class BugReportPage extends Page implements Serializable {	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int selectedRowIndex;
	private Long id;
	private List<BugReportData> items;
	private BugReportFilter bugReportFilter;
	
	public void setBugReportFilter(BugReportFilter BugReportFilter) {
		this.bugReportFilter = BugReportFilter;
	}
	public BugReportPage(BugReportFilter BugReportFilter) {
		super();
		this.bugReportFilter = BugReportFilter;
		// TODO Auto-generated constructor stub
	}
	public BugReportPage() {
		super();
		this.bugReportFilter = new BugReportFilter();
		 // TODO Auto-generated constructor stub
	}
	public BugReportPage(boolean isPagination, boolean isFilterEnabled, int maxItems, int currentPageIndex,
			int availablePages, int availableItems, BugReportFilter BugReportFilter) {
		super(isPagination, isFilterEnabled, maxItems, currentPageIndex, availablePages, availableItems);
		this.bugReportFilter = BugReportFilter;
		// TODO Auto-generated constructor stub
	}
	public BugReportPage(boolean isPagination, boolean isFilterEnabled, int maxItems, BugReportFilter BugReportFilter) {
		super(isPagination, isFilterEnabled, maxItems);
		this.bugReportFilter = BugReportFilter;
		// TODO Auto-generated constructor stub
	}
	
	public int getSelectedRowIndex() {
		return selectedRowIndex;
	}
	public void setSelectedRowIndex(int selectedRowIndex) {
		this.selectedRowIndex = selectedRowIndex;
	}

	
	public List<BugReportData> getItems() {
		return items;
	}
	public void setItems(List<BugReportData> items) {
		this.items = items;
	}
	public BugReportFilter getBugReportFilter() {
		return bugReportFilter;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
