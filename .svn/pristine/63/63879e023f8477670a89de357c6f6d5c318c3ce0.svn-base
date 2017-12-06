package com.isl.admin.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Page implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int DEFAULT_MAX_ITEMS =10;	
	public static final int ALL_ITEMS = -1; 
	private boolean isPagination;
	private boolean isFilterEnabled;
	private int maxItems;
	private int currentPageIndex;
	private int availablePages;
	private int availableItems;
	private List<Integer> pageSizeQueue;
	
	public Page() {
		super();
		this.currentPageIndex = 1;
		this.isPagination = true;
		this.isFilterEnabled = true;
		this.maxItems = DEFAULT_MAX_ITEMS;
		this.pageSizeQueue = new ArrayList<Integer>();	
		this.pageSizeQueue.add(ALL_ITEMS); 
		this.pageSizeQueue.add(10);
		this.pageSizeQueue.add(20);
		this.pageSizeQueue.add(50);
		this.pageSizeQueue.add(100);
		this.pageSizeQueue.add(500);

	}
	public Page(boolean isPagination, boolean isFilterEnabled, int maxItems) {
		super();
		this.isPagination = isPagination;
		this.isFilterEnabled = isFilterEnabled;
		this.maxItems = maxItems;
	}
	public Page(boolean isPagination, boolean isFilterEnabled, int maxItems, int currentPageIndex, int availablePages,
			int availableItems){
		super();
		this.isPagination = isPagination;
		this.isFilterEnabled = isFilterEnabled;
		this.maxItems = maxItems;
		this.currentPageIndex = currentPageIndex;
		this.availablePages = availablePages;
		this.availableItems = availableItems;
	}	
	public void setMaxItems(int maxItems) {
		this.maxItems = maxItems;
	}
	public List<Integer> getPageSizeQueue() {
		return pageSizeQueue;
	}
	public void setPageSizeQueue(List<Integer> pageSizeQueue) {
		this.pageSizeQueue = pageSizeQueue;
	}
	public boolean isPagination() {
		return isPagination;
	}	
	public boolean isFilterEnabled() {
		return isFilterEnabled;
	}	
	public int getMaxItems() {
		return maxItems;
	}

	public int getCurrentPageIndex() {
		return currentPageIndex;
	}
	public void setCurrentPageIndex(int currentPageIndex) {
		this.currentPageIndex = currentPageIndex;
	}
	public int getAvailablePages() {
		return availablePages;
	}
	public void setAvailablePages(int availablePages) {
		this.availablePages = availablePages;
	}
	public int getAvailableItems() {
		return availableItems;
	}
	public void setAvailableItems(int availableItems) {
		this.availableItems = availableItems;
	}
	@Override
	public String toString() {
		return "Page [isPagination=" + isPagination + ", isFilterEnabled=" + isFilterEnabled + ", maxItems=" + maxItems
				+ ", currentPageIndex=" + currentPageIndex + ", availablePages=" + availablePages + ", availableItems="
				+ availableItems + "]";
	}

}
