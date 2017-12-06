package com.isl.admin.page;

import java.io.Serializable;
import java.util.List;

import com.isl.admin.filter.FlightReportFilter;
import com.lintas.admin.flight.model.FlightCommissionReport;
import com.lintas.admin.flight.model.ReportData;

public class FlightReportPage extends Page implements Serializable {	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int selectedRowIndex;
	private Long id;
	private String orderId;
	private String invoiceNo;	
	private List<ReportData> items;
	private List<FlightCommissionReport> invoiceitems;
	private ReportData CurrentReportdata;
	private String assignTo;
	private String assignedBy;
	private FlightReportFilter flightReportFilter;
	
	public void setFlightReportFilter(FlightReportFilter flightReportFilter) {
		this.flightReportFilter = flightReportFilter;
	}
	public FlightReportPage(FlightReportFilter flightReportFilter) {
		super();
		this.flightReportFilter = flightReportFilter;
		// TODO Auto-generated constructor stub
	}
	public FlightReportPage() {
		super();
		this.flightReportFilter = new FlightReportFilter();
		 // TODO Auto-generated constructor stub
	}
	public FlightReportPage(boolean isPagination, boolean isFilterEnabled, int maxItems, int currentPageIndex,
			int availablePages, int availableItems, FlightReportFilter flightReportFilter) {
		super(isPagination, isFilterEnabled, maxItems, currentPageIndex, availablePages, availableItems);
		this.flightReportFilter = flightReportFilter;
		// TODO Auto-generated constructor stub
	}
	public FlightReportPage(boolean isPagination, boolean isFilterEnabled, int maxItems, FlightReportFilter flightReportFilter) {
		super(isPagination, isFilterEnabled, maxItems);
		this.flightReportFilter = flightReportFilter;
		// TODO Auto-generated constructor stub
	}
	
	public int getSelectedRowIndex() {
		return selectedRowIndex;
	}
	public void setSelectedRowIndex(int selectedRowIndex) {
		this.selectedRowIndex = selectedRowIndex;
	}

	public ReportData getCurrentReportdata() {
		return CurrentReportdata;
	}
	public void setCurrentReportdata(ReportData currentReportdata) {
		CurrentReportdata = currentReportdata;
	}
	public List<ReportData> getItems() {
		return items;
	}
	public void setItems(List<ReportData> items) {
		this.items = items;
	}
	public FlightReportFilter getFlightReportFilter() {
		return flightReportFilter;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<FlightCommissionReport> getInvoiceitems() {
		return invoiceitems;
	}
	public void setInvoiceitems(List<FlightCommissionReport> invoiceitems) {
		this.invoiceitems = invoiceitems;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public String getAssignTo() {
		return assignTo;
	}
	public void setAssignTo(String assignTo) {
		this.assignTo = assignTo;
	}
	public String getAssignedBy() {
		return assignedBy;
	}
	public void setAssignedBy(String assignedBy) {
		this.assignedBy = assignedBy;
	}
	 
	 
}
