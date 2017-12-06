package com.isl.admin.filter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lintas.admin.model.Company;
import com.lintas.admin.model.CompanyConfig;
import com.lintas.admin.model.User;

public class FlightInvoiceFilter extends Filter implements Serializable {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public FlightInvoiceFilter() {
		super();
		this.dateFilterBooking = new DateFilterOption();
		this.dateFilterArrival = new DateFilterOption();
		this.dateFilterDeparture = new DateFilterOption();
		this.amountRangeBooking = new AmountFilterOption();
		this.companyId = -1;
		this.userId = -1;
		this.companyName = null;
		this.showtype = null;
		this.userName = null;
		this.paymentStatus = null;
		this.bookingStatus = null;
		this.origin = null;
		this.destination = null;
		this.airlineName = null;
		this.bookingStatusQueue = new ArrayList<>();
		this.paymentStatusQueue=new ArrayList<>();
		 
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
		
		
	
	}
	
	@Override
	public String toString() {
		return "FlightInvoiceFilter [bookingStatusQueue=" + bookingStatusQueue + ", paymentStatusQueue="
				+ paymentStatusQueue + ", dateFilterBooking=" + dateFilterBooking + ", dateFilterArrival="
				+ dateFilterArrival + ", dateFilterDeparture=" + dateFilterDeparture + ", amountRangeBooking="
				+ amountRangeBooking + ", companyId=" + companyId + ", userId=" + userId + ", companyName="
				+ companyName + ", userName=" + userName + ", paymentStatus=" + paymentStatus + ", bookingStatus="
				+ bookingStatus + ", origin=" + origin + ", destination=" + destination + ", airlineName=" + airlineName
				+ ", showtype=" + showtype + "]";
	}

	public List<String> getBookingStatusQueue() {
		return bookingStatusQueue;
	}

	public void setBookingStatusQueue(List<String> bookingStatusQueue) {
		this.bookingStatusQueue = bookingStatusQueue;
	}

	public FlightInvoiceFilter(CompanyConfig loginCompanyConfig, Company loginCompany, User loginUser, int reportType) {
		super(loginCompanyConfig, loginCompany, loginUser, reportType);
		// TODO Auto-generated constructor stub
	}
	public FlightInvoiceFilter(CompanyConfig loginCompanyConfig, Company loginCompany, User loginUser) {
		super(loginCompanyConfig, loginCompany, loginUser);
		// TODO Auto-generated constructor stub
	}
	
	
	private List<String> bookingStatusQueue;
	private List<String> paymentStatusQueue;
	
	
	private DateFilterOption  dateFilterBooking;
	private DateFilterOption  dateFilterArrival;
	private DateFilterOption  dateFilterDeparture;
	private AmountFilterOption  amountRangeBooking;
	public AmountFilterOption getAmountRangeBooking() {
		return amountRangeBooking;
	}

	public void setAmountRangeBooking(AmountFilterOption amountRangeBooking) {
		this.amountRangeBooking = amountRangeBooking;
	}


	private int companyId;
	private int userId;
	private String companyName;
	private String userName;
	private String paymentStatus;
	private String bookingStatus;
	private String origin;
	private String destination;
	private String airlineName;
	private String invoiceNo;
	private String email;
	private String  confirmNo;
	private String showtype;
	
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

	 
	public String getConfirmNo() {
		return confirmNo;
	}

	public void setConfirmNo(String confirmNo) {
		this.confirmNo = confirmNo;
	}
}
