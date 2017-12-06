package com.isl.admin.page;

import java.io.Serializable;
import java.util.List;

import com.isl.admin.filter.BrowsingHistoryFilter;
import com.tayyarah.browsingHistory.model.BrowsingHistory;
import com.tayyarah.browsingHistory.model.BrowsingOptionPageEnum;

public class BrowsingHistoryPage extends Page implements Serializable {	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int selectedRowIndex;
	private BrowsingHistoryFilter browsingHistoryFilter;
	private List<BrowsingHistory> browsingHistoryList; 
	private BrowsingOptionPageEnum browsingOptionPageEnum;
	
	/*private BrowsingHistoryFilter browsingHistoryFilter;*/
	public BrowsingHistoryPage() {
		super();
		this.setBrowsingHistoryFilter(new BrowsingHistoryFilter());
		// TODO Auto-generated constructor stub
	}


	public BrowsingHistoryPage(boolean isPagination, boolean isFilterEnabled, int maxItems, int currentPageIndex,
			int availablePages, int availableItems, BrowsingHistoryFilter browsingHistoryFilter) {
		super(isPagination, isFilterEnabled, maxItems, currentPageIndex, availablePages, availableItems);
		this.browsingHistoryFilter = browsingHistoryFilter;
		// TODO Auto-generated constructor stub
	}
	public BrowsingHistoryPage(boolean isPagination, boolean isFilterEnabled, int maxItems, BrowsingHistoryFilter browsingHistoryFilter) {
		super(isPagination, isFilterEnabled, maxItems);
		this.browsingHistoryFilter = browsingHistoryFilter;
		// TODO Auto-generated constructor stub
	}
	/*	private int selectedRowIndex;*/
	public int getSelectedRowIndex() {
		return selectedRowIndex;
	}
	public void setSelectedRowIndex(int selectedRowIndex) {
		this.selectedRowIndex = selectedRowIndex;
	}

	public BrowsingHistoryFilter getBrowsingHistoryFilter() {
		return browsingHistoryFilter;
	}
	public void setBrowsingHistoryFilter(BrowsingHistoryFilter browsingHistoryFilter) {
		this.browsingHistoryFilter = browsingHistoryFilter;
	}

	
	public List<BrowsingHistory> getBrowsingHistoryList() {
		return browsingHistoryList;
	}

	public void setBrowsingHistoryList(List<BrowsingHistory> browsingHistoryList) {
		this.browsingHistoryList = browsingHistoryList;

	}


	public BrowsingOptionPageEnum getBrowsingOptionPageEnum() {
		return browsingOptionPageEnum;
	}


	public void setBrowsingOptionPageEnum(BrowsingOptionPageEnum browsingOptionPageEnum) {
		this.browsingOptionPageEnum = browsingOptionPageEnum;
	}
}



