package com.isl.admin.page;

import java.io.Serializable;
import java.util.List;

import com.isl.admin.filter.FlightInvoiceFilter;
import com.lintas.admin.flight.model.FlightOrderRow;
import com.lintas.admin.flight.model.ReportData;
import com.lintas.admin.hotel.model.HotelOrderRow;

public class FlightInvoicePage extends Page implements Serializable {	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FlightInvoiceFilter flightInvoiceFilter;

	public FlightInvoicePage() {
		super();
		
		this.setFlightInvoiceFilter(new FlightInvoiceFilter());
		 // TODO Auto-generated constructor stub
	}
	public FlightInvoicePage(boolean isPagination, boolean isFilterEnabled, int maxItems, int currentPageIndex,
			int availablePages, int availableItems, FlightInvoiceFilter flightReportFilter) {
		super(isPagination, isFilterEnabled, maxItems, currentPageIndex, availablePages, availableItems);
		this.setFlightInvoiceFilter(flightReportFilter);
		// TODO Auto-generated constructor stub
	}
	public FlightInvoicePage(boolean isPagination, boolean isFilterEnabled, int maxItems, FlightInvoiceFilter flightInvoiceFilter) {
		super(isPagination, isFilterEnabled, maxItems);
		this.setFlightInvoiceFilter(flightInvoiceFilter);
		// TODO Auto-generated constructor stub
	}
	private int selectedRowIndex;
	public int getSelectedRowIndex() {
		return selectedRowIndex;
	}
	public void setSelectedRowIndex(int selectedRowIndex) {
		this.selectedRowIndex = selectedRowIndex;
	}
	private Long id;
	private String orderId;
	private String orderReference;
	private String companyId;
	private String userId;
	private List<FlightOrderRow> items;
	private List<HotelOrderRow> hotelItems;
	
	private ReportData CurrentReportdata;
	public ReportData getCurrentReportdata() {
		return CurrentReportdata;
	}
	public void setCurrentReportdata(ReportData currentReportdata) {
		CurrentReportdata = currentReportdata;
	}

	
	public List<FlightOrderRow> getItems() {
		return items;
	}
	public void setItems(List<FlightOrderRow> items) {
		this.items = items;
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
	public FlightInvoiceFilter getFlightInvoiceFilter() {
		return flightInvoiceFilter;
	}
	public void setFlightInvoiceFilter(FlightInvoiceFilter flightInvoiceFilter) {
		this.flightInvoiceFilter = flightInvoiceFilter;
	}
	public List<HotelOrderRow> getHotelItems() {
		return hotelItems;
	}
	public void setHotelItems(List<HotelOrderRow> hotelItems) {
		this.hotelItems = hotelItems;
	}
	public String getOrderReference() {
		return orderReference;
	}
	public void setOrderReference(String orderReference) {
		this.orderReference = orderReference;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	 
	
}
