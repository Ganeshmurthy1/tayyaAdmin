package com.isl.admin.page;

import java.io.Serializable;
import java.util.List;

import com.isl.admin.filter.HotelReportFilter;
import com.lintas.admin.flight.model.FlightCommissionReport;
import com.lintas.admin.flight.model.ReportData;
import com.lintas.admin.hotel.model.HotelReport;

public class HotelReportPage extends Page implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public HotelReportPage(HotelReportFilter hotelReportFilter) {
		super();
		this.hotelReportFilter = hotelReportFilter;
		// TODO Auto-generated constructor stub
	}
	public void setHotelReportFilter(HotelReportFilter hotelReportFilter) {
		this.hotelReportFilter = hotelReportFilter;
	}
	public HotelReportPage() {
		super();
		this.hotelReportFilter = new HotelReportFilter();
		// TODO Auto-generated constructor stub
	}
	public HotelReportPage(boolean isPagination, boolean isFilterEnabled, int maxItems, int currentPageIndex,
			int availablePages, int availableItems, HotelReportFilter hotelReportFilter) {
		super(isPagination, isFilterEnabled, maxItems, currentPageIndex, availablePages, availableItems);
		this.hotelReportFilter = hotelReportFilter;
		// TODO Auto-generated constructor stub
	}
	public HotelReportPage(boolean isPagination, boolean isFilterEnabled, int maxItems, HotelReportFilter hotelReportFilter) {
		super(isPagination, isFilterEnabled, maxItems);
		this.hotelReportFilter = hotelReportFilter;
		// TODO Auto-generated constructor stub
	}
	
	private int selectedRowIndex;
	private long id;
	private String orderId;
	private String invoiceNo;

	
	private List<ReportData> items1;
	private List<FlightCommissionReport> invoiceitems;
	private ReportData CurrentReportdata;
	
	private List<HotelReport> items;
	private  HotelReportFilter hotelReportFilter;
	
	public int getSelectedRowIndex() {
		return selectedRowIndex;
	}
	public void setSelectedRowIndex(int selectedRowIndex) {
		this.selectedRowIndex = selectedRowIndex;
	}
	public List<HotelReport> getItems() {
		return items;
	}
	public void setItems(List<HotelReport> items) {
		this.items = items;
	}
	public HotelReportFilter getHotelReportFilter() {
		return hotelReportFilter;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public List<ReportData> getItems1() {
		return items1;
	}
	public void setItems1(List<ReportData> items1) {
		this.items1 = items1;
	}
	public List<FlightCommissionReport> getInvoiceitems() {
		return invoiceitems;
	}
	public void setInvoiceitems(List<FlightCommissionReport> invoiceitems) {
		this.invoiceitems = invoiceitems;
	}
	public ReportData getCurrentReportdata() {
		return CurrentReportdata;
	}
	public void setCurrentReportdata(ReportData currentReportdata) {
		CurrentReportdata = currentReportdata;
	}
	 


}
