package com.admin.dashboardsearch.VO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import com.isl.admin.filter.FlightReportFilter;
import com.isl.admin.page.Page;

public class DashBoardSearchCommonVirtualObject extends Page implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String companyId; 
	private String userId;
	private String invoiceNo;
	private String pnr;
	private String orderId;
	private String confirmationNo;
	private String orderRef;
	private String route;
	private Timestamp createdAt;
	private String firstName;
	private String lastName;
	private String airline;
	private String origin;
	private String destination;
	private String bookingDate;
	private String departureDate;
	private BigDecimal netAmnt;
	private BigDecimal markUp;
	private BigDecimal finalPrice;
	private String status;
	private String paymentStatus;
	private String createdBy;
	private String salesPersonName;
	private String apiResponseMessage;
	private String apiProvider;
	private BigDecimal basePrice;
	private String curCode;
	private boolean isOrderRequested;
	private boolean isCreditNoteIssued;
	private BigDecimal total;
	private String checkInDate;
	private String checkOutDate;
	private String hotelName;
	private String statusAction;
	private String filterCompanyType;
	private String invoiceDate;
	private String title;
	private String travelDate;
	private BigDecimal supplierPrice;
	private BigDecimal serviceTax;
	private String description;
	private String supplierName;
	private String ticketType;
	private String servicetype;
	private boolean isOrderUpdated;
	private int page = 1;
	private int recordsPerPage = 20;
	private int selectedRowIndex;
	
	public DashBoardSearchCommonVirtualObject(boolean isPagination, boolean isFilterEnabled, int maxItems, int currentPageIndex,
			int availablePages, int availableItems, FlightReportFilter flightReportFilter) {
		super(isPagination, isFilterEnabled, maxItems, currentPageIndex, availablePages, availableItems);
		
		// TODO Auto-generated constructor stub
	}
	public DashBoardSearchCommonVirtualObject(){
		super();	
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public String getPnr() {
		return pnr;
	}
	public void setPnr(String pnr) {
		this.pnr = pnr;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getConfirmationNo() {
		return confirmationNo;
	}
	public void setConfirmationNo(String confirmationNo) {
		this.confirmationNo = confirmationNo;
	}
	public String getOrderRef() {
		return orderRef;
	}
	public void setOrderRef(String orderRef) {
		this.orderRef = orderRef;
	}
	public String getRoute() {
		return route;
	}
	public void setRoute(String route) {
		this.route = route;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAirline() {
		return airline;
	}
	public void setAirline(String airline) {
		this.airline = airline;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}
	public String getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}
	public BigDecimal getNetAmnt() {
		return netAmnt;
	}
	public void setNetAmnt(BigDecimal netAmnt) {
		this.netAmnt = netAmnt;
	}
	public BigDecimal getMarkUp() {
		return markUp;
	}
	public void setMarkUp(BigDecimal markUp) {
		this.markUp = markUp;
	}
	public BigDecimal getFinalPrice() {
		return finalPrice;
	}
	public void setFinalPrice(BigDecimal finalPrice) {
		this.finalPrice = finalPrice;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getSalesPersonName() {
		return salesPersonName;
	}
	public void setSalesPersonName(String salesPersonName) {
		this.salesPersonName = salesPersonName;
	}
	public String getApiResponseMessage() {
		return apiResponseMessage;
	}
	public void setApiResponseMessage(String apiResponseMessage) {
		this.apiResponseMessage = apiResponseMessage;
	}
	public String getApiProvider() {
		return apiProvider;
	}
	public void setApiProvider(String apiProvider) {
		this.apiProvider = apiProvider;
	}
	public BigDecimal getBasePrice() {
		return basePrice;
	}
	public void setBasePrice(BigDecimal basePrice) {
		this.basePrice = basePrice;
	}
	public String getCurCode() {
		return curCode;
	}
	public void setCurCode(String curCode) {
		this.curCode = curCode;
	}
	public boolean isOrderRequested() {
		return isOrderRequested;
	}
	public void setOrderRequested(boolean isOrderRequested) {
		this.isOrderRequested = isOrderRequested;
	}
	public boolean isCreditNoteIssued() {
		return isCreditNoteIssued;
	}
	public void setCreditNoteIssued(boolean isCreditNoteIssued) {
		this.isCreditNoteIssued = isCreditNoteIssued;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public String getCheckInDate() {
		return checkInDate;
	}
	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}
	public String getCheckOutDate() {
		return checkOutDate;
	}
	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getStatusAction() {
		return statusAction;
	}
	public void setStatusAction(String statusAction) {
		this.statusAction = statusAction;
	}
	public String getFilterCompanyType() {
		return filterCompanyType;
	}
	public void setFilterCompanyType(String filterCompanyType) {
		this.filterCompanyType = filterCompanyType;
	}
	public String getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTravelDate() {
		return travelDate;
	}
	public void setTravelDate(String travelDate) {
		this.travelDate = travelDate;
	}
	public BigDecimal getSupplierPrice() {
		return supplierPrice;
	}
	public void setSupplierPrice(BigDecimal supplierPrice) {
		this.supplierPrice = supplierPrice;
	}
	public BigDecimal getServiceTax() {
		return serviceTax;
	}
	public void setServiceTax(BigDecimal serviceTax) {
		this.serviceTax = serviceTax;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getTicketType() {
		return ticketType;
	}
	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}
	public String getServicetype() {
		return servicetype;
	}
	public void setServicetype(String servicetype) {
		this.servicetype = servicetype;
	}
	public boolean isOrderUpdated() {
		return isOrderUpdated;
	}
	public void setOrderUpdated(boolean isOrderUpdated) {
		this.isOrderUpdated = isOrderUpdated;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRecordsPerPage() {
		return recordsPerPage;
	}
	public void setRecordsPerPage(int recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}
	public int getSelectedRowIndex() {
		return selectedRowIndex;
	}
	public void setSelectedRowIndex(int selectedRowIndex) {
		this.selectedRowIndex = selectedRowIndex;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
