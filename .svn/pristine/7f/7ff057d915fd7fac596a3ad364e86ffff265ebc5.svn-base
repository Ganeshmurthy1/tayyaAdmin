package com.isl.admin.filter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lintas.admin.model.Company;
import com.lintas.admin.model.CompanyConfig;
import com.lintas.admin.model.User;

public class HotelReportFilter extends Filter implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HotelReportFilter(CompanyConfig loginCompanyConfig, Company loginCompany, User loginUser, int reportType) {
		super(loginCompanyConfig, loginCompany, loginUser, reportType);
		// TODO Auto-generated constructor stub
	}
	
	
	public HotelReportFilter(CompanyConfig loginCompanyConfig, Company loginCompany, User loginUser) {
		super(loginCompanyConfig, loginCompany, loginUser);
		// TODO Auto-generated constructor stub
	}
	public HotelReportFilter() {
		super();
		this.bookingStatus = null;
		this.city = null;
		this.checkOutFrom=null;
		this.checkOutTo=null;
		this.setCheckInFrom(null);
		this.setCheckInTo(null);
		this.showtype = null;
		this.email = null;
		this.orderId = null;		
		this.companyId = 0;
		this.companyName = null;
		this.companyType = null;
		this.hotelName = null;
		this.paymentStatus = null;
		this.userId = 0;
		this.userName = null;
		this.dateFilterBooking = new DateFilterOption();
		this.dateFilterCheckIn = new DateFilterOption();
		this.dateFilterCheckOut = new DateFilterOption();	
		this.amountRangeBooking = new AmountFilterOption();
		
		this.invoiceNo = null;
		this.confirmationNo = null;
		this.firstName = null;
		this.lastName = null;
		this.mobile = null;
		this.location = null;
		this.supplierName = null;
		this.dateFilterInvoice = new DateFilterOption();
		
		this.bookingStatusQueue = new ArrayList<>();
		this.paymentStatusQueue=new ArrayList<>();
		
		this.bookingStatusQueue.add("Booked");		
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
	 
		
	}
	
	private List<String> bookingStatusQueue;
	private List<String> paymentStatusQueue;
	
	
	private DateFilterOption  dateFilterBooking;
	private DateFilterOption  dateFilterCheckIn;
	private DateFilterOption  dateFilterCheckOut;
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
	private String city;
	private String email;
	private String orderId;
	private String hotelName;	
	private String companyType;
	private String showtype;
	private String checkOutFrom;
	private String checkOutTo;
	private String checkInFrom;
	private String checkInTo;
	
	private String invoiceNo;	
	private String confirmationNo;
	private String firstName;
	private String lastName;
	private String mobile;
	private String location;
	private String supplierName;
	private DateFilterOption  dateFilterInvoice;
	
	public List<String> getBookingStatusQueue() {
		return bookingStatusQueue;
	}

	public void setBookingStatusQueue(List<String> bookingStatusQueue) {
		this.bookingStatusQueue = bookingStatusQueue;
	}
	
	public List<String> getPaymentStatusQueue() {
		return paymentStatusQueue;
	}

	public void setPaymentStatusQueue(List<String> paymentStatusQueue) {
		this.paymentStatusQueue = paymentStatusQueue;
	}
	
	public DateFilterOption getDateFilterBooking() {
		return dateFilterBooking;
	}
	public void setDateFilterBooking(DateFilterOption dateFilterBooking) {
		this.dateFilterBooking = dateFilterBooking;
	}
	public DateFilterOption getDateFilterCheckIn() {
		return dateFilterCheckIn;
	}
	public void setDateFilterCheckIn(DateFilterOption dateFilterCheckIn) {
		this.dateFilterCheckIn = dateFilterCheckIn;
	}
	public DateFilterOption getDateFilterCheckOut() {
		return dateFilterCheckOut;
	}
	public void setDateFilterCheckOut(DateFilterOption dateFilterCheckOut) {
		this.dateFilterCheckOut = dateFilterCheckOut;
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
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	
	public String getCompanyType() {
		return companyType;
	}
	public String getOrderId() {
		return orderId;
	}


	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}
	
	
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	public String getShowtype() {
		return showtype;
	}
	public void setShowtype(String showtype) {
		this.showtype = showtype;
	}

	@Override
	public String toString() {
		return "HotelReportFilter [dateFilterBooking=" + (dateFilterBooking == null ? "":dateFilterBooking.toString()) + ", dateFilterCheckIn=" + (dateFilterCheckIn == null ? "":dateFilterCheckIn.toString()) 
				+ ", amountRangeBooking=" + (amountRangeBooking == null ? "":amountRangeBooking.toString())
				+ ", dateFilterCheckOut=" + (dateFilterCheckOut == null ? "":dateFilterCheckOut.toString()) + ", companyId=" + companyId + ", userId=" + userId
				+ ", companyName=" + companyName + ", userName=" + userName + ", paymentStatus=" + paymentStatus
				+ ", invoiceNo=" + invoiceNo + ", confirmationNo=" + confirmationNo + ", firstName=" + firstName + ", dateFilterInvoice=" + dateFilterInvoice
				+ ", lastName=" + lastName + ", mobile=" + mobile + ", location=" + location + ", supplierName=" + supplierName
				+ ", bookingStatus=" + bookingStatus + ", city=" + city + ", hotelName=" + hotelName + "]";
	}


	public String getCheckOutFrom() {
		return checkOutFrom;
	}


	public void setCheckOutFrom(String checkOutFrom) {
		this.checkOutFrom = checkOutFrom;
	}


	public String getCheckOutTo() {
		return checkOutTo;
	}


	public void setCheckOutTo(String checkOutTo) {
		this.checkOutTo = checkOutTo;
	}


	public String getCheckInFrom() {
		return checkInFrom;
	}


	public void setCheckInFrom(String checkInFrom) {
		this.checkInFrom = checkInFrom;
	}


	public String getCheckInTo() {
		return checkInTo;
	}


	public void setCheckInTo(String checkInTo) {
		this.checkInTo = checkInTo;
	}


	public String getInvoiceNo() {
		return invoiceNo;
	}


	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}


	public String getConfirmationNo() {
		return confirmationNo;
	}


	public void setConfirmationNo(String confirmationNo) {
		this.confirmationNo = confirmationNo;
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


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public String getSupplierName() {
		return supplierName;
	}


	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}


	public DateFilterOption getDateFilterInvoice() {
		return dateFilterInvoice;
	}


	public void setDateFilterInvoice(DateFilterOption dateFilterInvoice) {
		this.dateFilterInvoice = dateFilterInvoice;
	}
	
}
