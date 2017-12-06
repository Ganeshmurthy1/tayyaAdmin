package com.isl.admin.filter;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import com.lintas.admin.model.Company;
import com.lintas.admin.model.CompanyConfig;
import com.lintas.admin.model.User;

public class FlightReportFilter extends Filter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<String> bookingStatusQueue;
	private List<String> paymentStatusQueue;
	private String invoiceNo;
	private DateFilterOption  dateFilterBooking;
	private DateFilterOption  dateFilterArrival;
	private DateFilterOption  dateFilterDeparture;
	private AmountFilterOption  amountRangeBooking;
	private int companyId;
	private int userId;
	private String companyUserId;
	private String fromDate;
	private String toDate;
	private String companyName;
	private String userName;
	private String paymentStatus;
	private String bookingStatus;
	private String origin;
	private String destination;
	private String airlineName;
	private String showtype;
	private String pnr;
	private String paxName;
	private String orderId;
	private String email;
	private String confirmationNo;
	private Long customerId;
	private List<String> travelTypeList;
	private String travelType;
	
	private DateFilterOption  dateFilterInvoice;
	private String firstName;
	private String lastName;
	private String customerMobileNo;
	private String location;
	private String fromLocation;
	private String toLocation;

	
	private String mobile;
    private String supplierName;
	private DateFilterOption  dateFilterTravel;
	
	
	
	
	
	
	public FlightReportFilter() {
		super();
		this.dateFilterBooking = new DateFilterOption();
		this.dateFilterArrival = new DateFilterOption();
		this.dateFilterDeparture = new DateFilterOption();
		this.amountRangeBooking = new AmountFilterOption();
		this.dateFilterInvoice = new DateFilterOption();
		
		this.firstName = null;
		  this.lastName = null;
		  this.mobile = null;
		  this.supplierName = null;
		  this.dateFilterTravel = new DateFilterOption();
		  this.location = null;
		  this.toLocation= null;
		  this.fromLocation= null;
		  this.customerMobileNo= null;
		  
		this.companyId = -1;
		this.userId = -1;
		this.travelType ="Flight";
		this.companyName = null;
		this.showtype = null;
		this.fromDate = "";
		this.toDate = "";
		this.userName = null;
		this.companyUserId=null;
		this.pnr = null;
		this.paxName = null;
		this.paymentStatus = null;
		this.bookingStatus = null;
		this.origin = null;
		this.destination = null;
		this.airlineName = null;
		this.confirmationNo = null;
		this.customerId = null;
		this.bookingStatusQueue = new LinkedList<>();
		this.paymentStatusQueue=new LinkedList<>();
		this.travelTypeList=new LinkedList<>();
		/*<option value="booked">booked</option>
		<option value="ticketed">ticketed</option>
		<option value="cancelled">cancelled</option>
		<option value="expired">expired</option>
		<option value="reserved">reserved</option>
		<option value="pending">pending</option>
		<option value="confirmed">Confirmed</option>
		<option value="failed">Failed</option>*/
		this.bookingStatusQueue.add("Booked");
		this.bookingStatusQueue.add("Ticketed");
		this.bookingStatusQueue.add("Cancelled");
		this.bookingStatusQueue.add("Expired");
		this.bookingStatusQueue.add("Reserved");
		this.bookingStatusQueue.add("Pending");
		this.bookingStatusQueue.add("Confirmed");
		this.bookingStatusQueue.add("Failed");
		this.paymentStatusQueue.add("Success");
		this.paymentStatusQueue.add("Failed");
		this.paymentStatusQueue.add("Pending");
		this.paymentStatusQueue.add("Refund");
		this.paymentStatusQueue.add("Paid");

		this.travelTypeList.add("Flight");
		this.travelTypeList.add("Hotel");
		this.travelTypeList.add("Bus");
		this.travelTypeList.add("Car");
		this.travelTypeList.add("Train");
		this.travelTypeList.add("Visa");
		this.travelTypeList.add("Insurance");

	}
	public FlightReportFilter(CompanyConfig loginCompanyConfig, Company loginCompany, User loginUser, int reportType) {
		super(loginCompanyConfig, loginCompany, loginUser, reportType);
		// TODO Auto-generated constructor stub
	}
	public FlightReportFilter(CompanyConfig loginCompanyConfig, Company loginCompany, User loginUser) {
		super(loginCompanyConfig, loginCompany, loginUser);
		// TODO Auto-generated constructor stub
	}

	public String getFromDate() {
		return fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getAirlineName() {
		return airlineName;
	}

	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}

	public DateFilterOption getDateFilterBooking() {
		return dateFilterBooking;
	}
	public void setDateFilterBooking(DateFilterOption dateFilterBooking) {
		this.dateFilterBooking = dateFilterBooking;
	}
	public DateFilterOption getDateFilterArrival() {
		return dateFilterArrival;
	}
	public void setDateFilterArrival(DateFilterOption dateFilterArrival) {
		this.dateFilterArrival = dateFilterArrival;
	}
	public DateFilterOption getDateFilterDeparture() {
		return dateFilterDeparture;
	}
	public void setDateFilterDeparture(DateFilterOption dateFilterDeparture) {
		this.dateFilterDeparture = dateFilterDeparture;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getBookingStatus() {
		return bookingStatus;
	}
	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}

	public List<String> getPaymentStatusQueue() {
		return paymentStatusQueue;
	}

	public void setPaymentStatusQueue(List<String> paymentStatusQueue) {
		this.paymentStatusQueue = paymentStatusQueue;
	}
	public String getShowtype() {
		return showtype;
	}
	public void setShowtype(String showtype) {
		this.showtype = showtype;
	}

	public String getPnr() {
		return pnr;
	}

	public void setPnr(String pnr) {
		this.pnr = pnr;
	}

	public String getPaxName() {
		return paxName;
	}

	public void setPaxName(String paxName) {
		this.paxName = paxName;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getConfirmationNo() {
		return confirmationNo;
	}

	public void setConfirmationNo(String confirmationNo) {
		this.confirmationNo = confirmationNo;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCompanyUserId() {
		return companyUserId;
	}

	public void setCompanyUserId(String companyUserId) {
		this.companyUserId = companyUserId;
	}
	public AmountFilterOption getAmountRangeBooking() {
		return amountRangeBooking;
	}

	public void setAmountRangeBooking(AmountFilterOption amountRangeBooking) {
		this.amountRangeBooking = amountRangeBooking;
	}

	public List<String> getBookingStatusQueue() {
		return bookingStatusQueue;
	}

	public void setBookingStatusQueue(List<String> bookingStatusQueue) {
		this.bookingStatusQueue = bookingStatusQueue;
	}
	public List<String> getTravelTypeList() {
		return travelTypeList;
	}
	public void setTravelTypeList(List<String> travelTypeList) {
		this.travelTypeList = travelTypeList;
	}

	@Override
	public String toString() {
		return "FlightReportFilter [bookingStatusQueue=" + bookingStatusQueue + ", paymentStatusQueue="
				+ paymentStatusQueue + ", invoiceNo=" + invoiceNo + ", dateFilterBooking=" + dateFilterBooking
				+ ", dateFilterArrival=" + dateFilterArrival + ", dateFilterDeparture=" + dateFilterDeparture
				+ ", amountRangeBooking=" + amountRangeBooking + ", companyId=" + companyId + ", userId=" + userId
				+ ", companyUserId=" + companyUserId + ", fromDate=" + fromDate + ", toDate=" + toDate
				+ ", companyName=" + companyName + ", userName=" + userName + ", paymentStatus=" + paymentStatus
				+ ", bookingStatus=" + bookingStatus + ", origin=" + origin + ", destination=" + destination
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", location=" + location+ ", dateFilterInvoice=" + dateFilterInvoice
				+ ", mobile=" + mobile + ", supplierName=" + supplierName + ", dateFilterTravel=" + dateFilterTravel
				+ ", airlineName=" + airlineName + ", showtype=" + showtype + ", pnr=" + pnr + ", paxName=" + paxName
				+ ", orderId=" + orderId + ", email=" + email + ", confirmationNo=" + confirmationNo + ", customerId="
				+ customerId + ", travelTypeList=" + travelTypeList + "]";
	}
	
	public String getTravelType() {
		return travelType;
	}
	public void setTravelType(String travelType) {
		this.travelType = travelType;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	
	public String getCustomerMobileNo() {
		return customerMobileNo;
	}
	public String getLocation() {
		return location;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setCustomerMobileNo(String customerMobileNo) {
		this.customerMobileNo = customerMobileNo;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public DateFilterOption getDateFilterInvoice() {
		return dateFilterInvoice;
	}
	public void setDateFilterInvoice(DateFilterOption dateFilterInvoice) {
		this.dateFilterInvoice = dateFilterInvoice;
	}
	public String getFromLocation() {
		return fromLocation;
	}
	public String getToLocation() {
		return toLocation;
	}
	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
	}
	public void setToLocation(String toLocation) {
		this.toLocation = toLocation;
	}
	public String getMobile() {
		return mobile;
	}
	public DateFilterOption getDateFilterTravel() {
		return dateFilterTravel;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public void setDateFilterTravel(DateFilterOption dateFilterTravel) {
		this.dateFilterTravel = dateFilterTravel;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	
	 

}
