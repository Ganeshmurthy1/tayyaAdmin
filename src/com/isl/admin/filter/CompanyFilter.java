package com.isl.admin.filter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CompanyFilter extends Filter implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<String> companyRoleQueue;
	private List<String> employeeRoleQueue;
	private List<String> bookingTypeQueue;
	private List<String> transactionTypeQueue;
	private List<String> destinationTypeQueue;
	private List<String> dealTypeQueue;
	private List<String> categoryTypeQueue;
	private DateFilterOption dateFilterCreated;
	private DateFilterOption dateFilterModified;
	private AmountFilterOption amountRangeBooking;
	private String  airportName;
	private String  lastName ;
	private String firstName ;
	private String actionType ;
	private int companyId;
	private String companyName;	
	private String createdByCompanyName;
	private String orderId;	
	private String panNumber;	
	private String userName;
	private int userId;
	private String countryName;	
	private String address;		
	private String email;	
	private String city;	
	private String mobile;	
	private String companyType;
	private String destinationType;
	private String companyRoleType;
	private String phone;
	private Boolean isStatus;	
	private Boolean isLocked;	
	private String companyUserId;		
	private String parentCompanyUserId;	
	private String approveType;
	private String   configName;
	private String   minPrice;
	private String   maxPrice;
	private String   Startdate;
	private String   Enddate;
	private String companyConfigType;
	private List<String> companyConfigQueue;

	@Override
	public String toString() {
		return "CompanyListFilter [companyRoleQueue=" + companyRoleQueue + ", bookingTypeQueue=" + bookingTypeQueue
				+ ", dateFilterCreated=" + (dateFilterCreated == null ? "":dateFilterCreated.toString()) + ", dateFilterModified=" + (dateFilterModified == null ? "":dateFilterModified.toString())
				+ ", companyId=" + companyId + ", companyName=" + companyName + ", userName=" + userName
				+ ", countryName=" + countryName + ", address=" + address + ", email=" + email + ", city=" + city
				+ ", phone=" + phone + ", isStatus=" + isStatus + ", isLocked=" + isLocked + ", companyUserId="
				+ companyUserId + ", parentCompanyUserId=" + parentCompanyUserId +"]";
	}
	public List<String> getCompanyRoleQueue() {
		return companyRoleQueue;
	}
	public void setCompanyRoleQueue(List<String> companyRoleQueue) {
		this.companyRoleQueue = companyRoleQueue;
	}
	public List<String> getBookingTypeQueue() {
		return bookingTypeQueue;
	}
	public void setBookingTypeQueue(List<String> bookingTypeQueue) {
		this.bookingTypeQueue = bookingTypeQueue;
	}
	public DateFilterOption getDateFilterCreated() {
		return dateFilterCreated;
	}
	public void setDateFilterCreated(DateFilterOption dateFilterCreated) {
		this.dateFilterCreated = dateFilterCreated;
	}
	public DateFilterOption getDateFilterModified() {
		return dateFilterModified;
	}
	public void setDateFilterModified(DateFilterOption dateFilterModified) {
		this.dateFilterModified = dateFilterModified;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
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
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Boolean getIsStatus() {
		return isStatus;
	}
	public void setIsStatus(Boolean isStatus) {
		this.isStatus = isStatus;
	}
	public Boolean getIsLocked() {
		return isLocked;
	}
	public void setIsLocked(Boolean isLocked) {
		this.isLocked = isLocked;
	}
	public String getCompanyUserId() {
		return companyUserId;
	}
	public void setCompanyUserId(String companyUserId) {
		this.companyUserId = companyUserId;
	}
	public String getParentCompanyUserId() {
		return parentCompanyUserId;
	}
	public void setParentCompanyUserId(String parentCompanyUserId) {
		this.parentCompanyUserId = parentCompanyUserId;
	}
	public CompanyFilter() {
		super();
		this.dateFilterCreated = new DateFilterOption();
		this.dateFilterModified = new DateFilterOption();	
		this.setAmountRangeBooking(new AmountFilterOption());
		this.companyId =0;		
		this.companyName = null;
		this.createdByCompanyName = null;
		this.panNumber=null;
		this.userName = null;
		this.countryName = null;	
		this.address = null;		
		this.email = null;
		this.minPrice = null;
		this.maxPrice = null;
		this.Startdate= null;
		this.Enddate=null;
		this.setAirportName(null);
		this.setLastName(null);
		this.setFirstName(null);
		this.orderId=null;
		this.destinationType=null;
		this.mobile=null;
		this.companyRoleType=null;
		this.city = null;	
		this.phone = null;
		this.userId=0;
		this.isStatus = null;	
		this.isLocked = null;	
		this.companyUserId = null;		
		this.parentCompanyUserId = null;	
		this.companyRoleQueue = new ArrayList<>();
		this.bookingTypeQueue = new ArrayList<>();	
		this.employeeRoleQueue=new ArrayList<>();
		this.transactionTypeQueue=new ArrayList<>();
		this.destinationTypeQueue=new ArrayList<>();
		this.categoryTypeQueue=new ArrayList<>();
		 
		
		
		this.dealTypeQueue=new ArrayList<>();
		
		this.employeeRoleQueue.add("ADMIN");
		this.employeeRoleQueue.add("ORDER");
		this.employeeRoleQueue.add("REPORT");
		this.employeeRoleQueue.add("CMS");
		this.employeeRoleQueue.add("TECH_HEAD");
		this.employeeRoleQueue.add("TECH_SUPPORT");
		this.employeeRoleQueue.add("TRAVEL_DESK");

		this.companyRoleQueue.add("Agent");
		this.companyRoleQueue.add("Distributor");
		this.companyRoleQueue.add("Corporate");

		this.bookingTypeQueue.add("IBE");
		this.bookingTypeQueue.add("API");

		this.transactionTypeQueue.add("Credit");
		this.transactionTypeQueue.add("Debit");
		this.transactionTypeQueue.add("Deposit");

		this.destinationTypeQueue.add("Domestic");
		this.destinationTypeQueue.add("International");

		this.dealTypeQueue.add("flight");
		this.dealTypeQueue.add("hotel");
		this.dealTypeQueue.add("general");
		this.dealTypeQueue.add("package"); 
		
		 

	}

	public String getCompanyType() {
		return companyType;
	}
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public String getPanNumber() {
		return panNumber;
	}
	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public String getCompanyRoleType() {
		return companyRoleType;
	}
	public void setCompanyRoleType(String companyRoleType) {
		this.companyRoleType = companyRoleType;
	}

	public List<String> getEmployeeRoleQueue() {
		return employeeRoleQueue;
	}
	public void setEmployeeRoleQueue(List<String> employeeRoleQueue) {
		this.employeeRoleQueue = employeeRoleQueue;
	}

	public String getApproveType() {
		return approveType;
	}
	public void setApproveType(String approveType) {
		this.approveType = approveType;
	}

	public String getConfigName() {
		return configName;
	}
	public void setConfigName(String configName) {
		this.configName = configName;
	}

	public List<String> getTransactionTypeQueue() {
		return transactionTypeQueue;
	}
	public void setTransactionTypeQueue(List<String> transactionTypeQueue) {
		this.transactionTypeQueue = transactionTypeQueue;
	}

	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}



	public List<String> getDestinationTypeQueue() {
		return destinationTypeQueue;
	}
	public void setDestinationTypeQueue(List<String> destinationTypeQueue) {
		this.destinationTypeQueue = destinationTypeQueue;
	}



	public String getDestinationType() {
		return destinationType;
	}
	public void setDestinationType(String destinationType) {
		this.destinationType = destinationType;
	}



	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}



	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public List<String> getDealTypeQueue() {
		return dealTypeQueue;
	}
	public void setDealTypeQueue(List<String> dealTypeQueue) {
		this.dealTypeQueue = dealTypeQueue;
	}
	public AmountFilterOption getAmountRangeBooking() {
		return amountRangeBooking;
	}
	public void setAmountRangeBooking(AmountFilterOption amountRangeBooking) {
		this.amountRangeBooking = amountRangeBooking;
	}
	public String getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(String maxPrice) {
		this.maxPrice = maxPrice;
	}
	public String getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(String minPrice) {
		this.minPrice = minPrice;
	}
	public String getStartdate() {
		return Startdate;
	}
	public void setStartdate(String startdate) {
		Startdate = startdate;
	}
	public String getEnddate() {
		return Enddate;
	}
	public void setEnddate(String enddate) {
		Enddate = enddate;
	}
	public List<String> getCategoryTypeQueue() {
		return categoryTypeQueue;
	}
	public void setCategoryTypeQueue(List<String> categoryTypeQueue) {
		this.categoryTypeQueue = categoryTypeQueue;
	}
	public String getAirportName() {
		return airportName;
	}
	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	 
	public String getCreatedByCompanyName() {
		return createdByCompanyName;
	}
	public void setCreatedByCompanyName(String createdByCompanyName) {
		this.createdByCompanyName = createdByCompanyName;
	}
	public String getCompanyConfigType() {
		return companyConfigType;
	}
	public void setCompanyConfigType(String companyConfigType) {
		this.companyConfigType = companyConfigType;
	}
	public List<String> getCompanyConfigQueue() {
		return companyConfigQueue;
	}
	public void setCompanyConfigQueue(List<String> companyConfigQueue) {
		this.companyConfigQueue = companyConfigQueue;
	}
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	 
}
