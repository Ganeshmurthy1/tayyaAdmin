package com.lintas.admin.model;
 

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public class HotelCommissionReport implements Serializable{

	/**
	 * @author info raham
	 * created date:14-09-2015
	 */
	private static final long serialVersionUID = 1L;
	private String companyId;
	private String orderRef;
	private String configId;
	private String createdBy;
 
	private BigDecimal finalPrice;
	private BigDecimal base_price;
	private BigDecimal tax;
	private BigDecimal servPrice;
	private BigDecimal discount;
	private BigDecimal fee_amount;
	private BigDecimal agentCom;
	private String curCode;
 
	private String todayDate;
	private String yesterDayDate;
	 
	private String booking_status;
	private String checkInDate;
	private String checkOutDate;
	private String transactionType;
	private String email;
	private String mobile;
	private String phone;
	private int guests;
	private String gender;
	private String paymentStatus;
	private String ref_code;
	private String hotelName;
	private String hotel_loc;
	private String hotel_cat;
	private String state;
	private String country;
	private String statusAction;
	private Long Id;
	private String hotelType;
	private Timestamp createdAt;
	private String status;
	private String mealType;
	private Integer cancellationDay;
	private BigDecimal feeAmount;
	private Date startDate;
	private Date endDate;
	private String remarks;
	private String formattedFeeAmount;
	private Date fromDate;
	private String filterCompanyType;
	private BigDecimal commissionPercentage;
	private BigDecimal agentCommByRate;
	private BigDecimal totAmountSpent;
	private BigDecimal totAgentSComm;
	private BigDecimal myCommission;
	private BigDecimal sharedCommission;
	private BigDecimal finalCommission;
	private BigDecimal ticketPrice;
	private String commissionTyp;
	private BigDecimal childMarkup;
	private BigDecimal distributorMarkup;
	private BigDecimal markup;
	private BigDecimal myProfit;
	private BigDecimal totTicketPrice;
	private  BigDecimal totMyMarkup;
	private BigDecimal totWholeSalerMarkup ;
	private  BigDecimal totAgencyMarkup;
	private  BigDecimal totMyCommission;
	private BigDecimal totSharedCommission;
	private BigDecimal tdsOnCommission;
	private BigDecimal  TDS ;
	private BigDecimal myProfitwithDeductTDS;
	private String tdsType;
	 
	
	
	public BigDecimal getTotTicketPrice() {
		return totTicketPrice;
	}
	public void setTotTicketPrice(BigDecimal totTicketPrice) {
		this.totTicketPrice = totTicketPrice;
	}
	public BigDecimal getTotMyMarkup() {
		return totMyMarkup;
	}
	public void setTotMyMarkup(BigDecimal totMyMarkup) {
		this.totMyMarkup = totMyMarkup;
	}
	public BigDecimal getTotWholeSalerMarkup() {
		return totWholeSalerMarkup;
	}
	public void setTotWholeSalerMarkup(BigDecimal totWholeSalerMarkup) {
		this.totWholeSalerMarkup = totWholeSalerMarkup;
	}
	public BigDecimal getTotAgencyMarkup() {
		return totAgencyMarkup;
	}
	public void setTotAgencyMarkup(BigDecimal totAgencyMarkup) {
		this.totAgencyMarkup = totAgencyMarkup;
	}
	public BigDecimal getTotMyCommission() {
		return totMyCommission;
	}
	public void setTotMyCommission(BigDecimal totMyCommission) {
		this.totMyCommission = totMyCommission;
	}
	public BigDecimal getTotSharedCommission() {
		return totSharedCommission;
	}
	public void setTotSharedCommission(BigDecimal totSharedCommission) {
		this.totSharedCommission = totSharedCommission;
	}
	public BigDecimal getMyCommission() {
		return myCommission;
	}
	public void setMyCommission(BigDecimal myCommission) {
		this.myCommission = myCommission;
	}
	public BigDecimal getSharedCommission() {
		return sharedCommission;
	}
	public void setSharedCommission(BigDecimal sharedCommission) {
		this.sharedCommission = sharedCommission;
	}
	public BigDecimal getFinalCommission() {
		return finalCommission;
	}
	public void setFinalCommission(BigDecimal finalCommission) {
		this.finalCommission = finalCommission;
	}
	public BigDecimal getTicketPrice() {
		return ticketPrice;
	}
	public void setTicketPrice(BigDecimal ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
	public String getCommissionTyp() {
		return commissionTyp;
	}
	public void setCommissionTyp(String commissionTyp) {
		this.commissionTyp = commissionTyp;
	}
	public BigDecimal getChildMarkup() {
		return childMarkup;
	}
	public void setChildMarkup(BigDecimal childMarkup) {
		this.childMarkup = childMarkup;
	}
	public BigDecimal getDistributorMarkup() {
		return distributorMarkup;
	}
	public void setDistributorMarkup(BigDecimal distributorMarkup) {
		this.distributorMarkup = distributorMarkup;
	}
	public BigDecimal getMarkup() {
		return markup;
	}
	public void setMarkup(BigDecimal markup) {
		this.markup = markup;
	}
	public BigDecimal getMyProfit() {
		return myProfit;
	}
	public void setMyProfit(BigDecimal myProfit) {
		this.myProfit = myProfit;
	}
	public Integer getCancellationDay() {
		return cancellationDay;
	}
	public void setCancellationDay(Integer cancellationDay) {
		this.cancellationDay = cancellationDay;
	}
	public BigDecimal getFeeAmount() {
		return feeAmount;
	}
	public void setFeeAmount(BigDecimal feeAmount) {
		this.feeAmount = feeAmount;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getFormattedFeeAmount() {
		return formattedFeeAmount;
	}
	public void setFormattedFeeAmount(String formattedFeeAmount) {
		this.formattedFeeAmount = formattedFeeAmount;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	 
	public String getHotelType() {
		return hotelType;
	}
	public void setHotelType(String hotelType) {
		this.hotelType = hotelType;
	}
	 
	
	public String getHotel_loc() {
		return hotel_loc;
	}
	public void setHotel_loc(String hotel_loc) {
		this.hotel_loc = hotel_loc;
	}
	public String getHotel_cat() {
		return hotel_cat;
	}
	public void setHotel_cat(String hotel_cat) {
		this.hotel_cat = hotel_cat;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	 
	public BigDecimal getBase_price() {
		return base_price;
	}
	public void setBase_price(BigDecimal base_price) {
		this.base_price = base_price;
	}
	public BigDecimal getTax() {
		return tax;
	}
	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}
	public BigDecimal getServPrice() {
		return servPrice;
	}
	public void setServPrice(BigDecimal servPrice) {
		this.servPrice = servPrice;
	}
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	public BigDecimal getAgentCom() {
		return agentCom;
	}
	public void setAgentCom(BigDecimal bigDecimal) {
		this.agentCom = bigDecimal;
	}
	public String getCurCode() {
		return curCode;
	}
	public void setCurCode(String curCode) {
		this.curCode = curCode;
	}
	 
	public String getTodayDate() {
		return todayDate;
	}
	public void setTodayDate(String todayDate) {
		this.todayDate = todayDate;
	}
	public String getYesterDayDate() {
		return yesterDayDate;
	}
	public void setYesterDayDate(String yesterDayDate) {
		this.yesterDayDate = yesterDayDate;
	}
 
	 
	public String getBooking_status() {
		return booking_status;
	}
	public void setBooking_status(String booking_status) {
		this.booking_status = booking_status;
	}
	public String getCheckInDate() {
		return checkInDate;
	}
	public void setCheckInDate(String date) {
		this.checkInDate = date;
	}
	public String getCheckOutDate() {
		return checkOutDate;
	}
	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getGuests() {
		return guests;
	}
	public void setGuests(int guests) {
		this.guests = guests;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	/**
	 * @return the ref_code
	 */
	public String getRef_code() {
		return ref_code;
	}
	/**
	 * @param ref_code the ref_code to set
	 */
	public void setRef_code(String ref_code) {
		this.ref_code = ref_code;
	}
	/**
	 * @return the hotelName
	 */
	public String getHotelName() {
		return hotelName;
	}
	/**
	 * @param hotelName the hotelName to set
	 */
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	/**
	 * @return the fee_amount
	 */
	public BigDecimal getFee_amount() {
		return fee_amount;
	}
	/**
	 * @param fee_amount the fee_amount to set
	 */
	public void setFee_amount(BigDecimal fee_amount) {
		this.fee_amount = fee_amount;
	}
	/**
	 * @return the orderRef
	 */
	public String getOrderRef() {
		return orderRef;
	}
	/**
	 * @param orderRef the orderRef to set
	 */
	public void setOrderRef(String orderRef) {
		this.orderRef = orderRef;
	}
	/**
	 * @return the statusAction
	 */
	public String getStatusAction() {
		return statusAction;
	}
	/**
	 * @param statusAction the statusAction to set
	 */
	public void setStatusAction(String statusAction) {
		this.statusAction = statusAction;
	}
	/**
	 * @return the id
	 */
	public Long getId() {
		return Id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		Id = id;
	}
	 
	 
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	/**
	 * @return the mealType
	 */
	public String getMealType() {
		return mealType;
	}
	/**
	 * @param mealType the mealType to set
	 */
	public void setMealType(String mealType) {
		this.mealType = mealType;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getConfigId() {
		return configId;
	}
	public void setConfigId(String configId) {
		this.configId = configId;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	/**
	 * @return the apiComments
	 */
	 
	 
	public String getFilterCompanyType() {
		return filterCompanyType;
	}
	public void setFilterCompanyType(String filterCompanyType) {
		this.filterCompanyType = filterCompanyType;
	}
	public BigDecimal getCommissionPercentage() {
		return commissionPercentage;
	}
	public void setCommissionPercentage(BigDecimal commissionPercentage) {
		this.commissionPercentage = commissionPercentage;
	}
	public BigDecimal getAgentCommByRate() {
		return agentCommByRate;
	}
	public void setAgentCommByRate(BigDecimal agentCommByRate) {
		this.agentCommByRate = agentCommByRate;
	}
	public BigDecimal getTotAmountSpent() {
		return totAmountSpent;
	}
	public void setTotAmountSpent(BigDecimal totAmountSpent) {
		this.totAmountSpent = totAmountSpent;
	}
	public BigDecimal getTotAgentSComm() {
		return totAgentSComm;
	}
	public void setTotAgentSComm(BigDecimal totAgentSComm) {
		this.totAgentSComm = totAgentSComm;
	}
	public BigDecimal getFinalPrice() {
		return finalPrice;
	}
	public void setFinalPrice(BigDecimal finalPrice) {
		this.finalPrice = finalPrice;
	}
	public BigDecimal getTdsOnCommission() {
		return tdsOnCommission;
	}
	public void setTdsOnCommission(BigDecimal tdsOnCommission) {
		this.tdsOnCommission = tdsOnCommission;
	}
	public BigDecimal getTDS() {
		return TDS;
	}
	public void setTDS(BigDecimal tDS) {
		TDS = tDS;
	}
	public BigDecimal getMyProfitwithDeductTDS() {
		return myProfitwithDeductTDS;
	}
	public void setMyProfitwithDeductTDS(BigDecimal myProfitwithDeductTDS) {
		this.myProfitwithDeductTDS = myProfitwithDeductTDS;
	}
	public String getTdsType() {
		return tdsType;
	}
	public void setTdsType(String tdsType) {
		this.tdsType = tdsType;
	}
	
	
	
	
 }
