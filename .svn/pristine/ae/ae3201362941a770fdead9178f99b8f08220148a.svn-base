/**
 * 
 */
package com.customerIp.filter;

import java.io.Serializable;
import java.util.List;

import com.isl.admin.page.Page;

/**
 * @author      : Manish Samrat
 * @createdAt   : 24/07/2017
 * @version
 * @updateaBy   :  
 */
public class CustomerIpFilterPage  extends Page implements Serializable{

	private static final long serialVersionUID = 1L;
	private List<CustomerIpReportData> items;
	private CustomerIpReportData currentCustomerIpReportData ;
	private CustomerIpReportFilter customerIpReportFilter;
	
	public CustomerIpFilterPage(CustomerIpReportFilter lookBookReportFilter) {
		super();
		this.customerIpReportFilter = lookBookReportFilter;
		// TODO Auto-generated constructor stub
	}
	public CustomerIpFilterPage() {
		super();
		this.customerIpReportFilter = new CustomerIpReportFilter();
		 // TODO Auto-generated constructor stub
	}
	public CustomerIpFilterPage(boolean isPagination, boolean isFilterEnabled, int maxItems, int currentPageIndex,
			int availablePages, int availableItems, CustomerIpReportFilter lookBookReportFilter) {
		super(isPagination, isFilterEnabled, maxItems, currentPageIndex, availablePages, availableItems);
		this.customerIpReportFilter = lookBookReportFilter;
		// TODO Auto-generated constructor stub
	}
	public CustomerIpFilterPage(boolean isPagination, boolean isFilterEnabled, int maxItems, CustomerIpReportFilter lookBookReportFilter) {
		super(isPagination, isFilterEnabled, maxItems);
		this.customerIpReportFilter = lookBookReportFilter;
		// TODO Auto-generated constructor stub
	}
	public List<CustomerIpReportData> getItems() {
		return items;
	}
	public void setItems(List<CustomerIpReportData> items) {
		this.items = items;
	}
	public CustomerIpReportData getCurrentCustomerIpReportData() {
		return currentCustomerIpReportData;
	}
	public void setCurrentCustomerIpReportData(CustomerIpReportData currentCustomerIpReportData) {
		this.currentCustomerIpReportData = currentCustomerIpReportData;
	}
	public CustomerIpReportFilter getCustomerIpReportFilter() {
		return customerIpReportFilter;
	}
	public void setCustomerIpReportFilter(CustomerIpReportFilter customerIpReportFilter) {
		this.customerIpReportFilter = customerIpReportFilter;
	}
}
