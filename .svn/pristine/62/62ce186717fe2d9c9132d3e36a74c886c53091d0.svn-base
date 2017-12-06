package com.lintas.admin.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.lintas.admin.common.model.PaymentTransaction;
import com.lintas.admin.hotel.model.HotelOrderGuest;
import com.lintas.admin.hotel.model.HotelOrderRoomInfo;

public class HotelInvoiceData implements Serializable{

	/**
	 * @author info raham
	 * created date : 23-10-2015
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal totalTDS;
	private BigDecimal finalCommWithTdsDeduct;
	private String agentName;
	private String firstName;
	private String lastName;
	private String cusAddress;
	private String invNo;
	private String ActCode;
	private String consultant;
	private String bookNo;
	private String yourRef;
	private String date;
	private String fax;
	private String tel;
	private String mobile;
	private String attn;
	private String gstType;
	private String gender;
	private int qty;
	private BigDecimal price;
	private BigDecimal totPrice;
	private String currency;
	private String GstSummary;
	private BigDecimal tax;
	private BigDecimal totBeforeGst;
	private BigDecimal totGst;
	private BigDecimal totWithGst;
	private BigDecimal totAgentComm;
	private BigDecimal totAmountSpent;
	private BigDecimal totAmount;
	private BigDecimal eachOrderTotComm;
	private BigDecimal orderFinalPrice;
	private String paymentStatus;
	private String status;
	private String bookedDate;
	private Long orderRowId;
	private String companyId;
	private String orderId;
	private String checkInDate;
	private String checkOutDate;
	private List<HotelOrderRoomInfo> hotelOrderRoomInfo;
	private List<HotelOrderGuest>  hotelOrderGuest;
	private List<PaymentTransaction>  txDetails;
	private List<WalletAmountTranferHistory> agentWalletTxDetails;
	private List<User> userDetails;
	private Company  companyAddress;
	private String companysGstOn;
	private  BigDecimal DiscountAmount;
	
	
	
	
	private BigDecimal finalAmount;		
	
	private BigDecimal payAmount;
	private BigDecimal urProfit;	
	private BigDecimal urMarkup;
	private BigDecimal urCommission;	
	private BigDecimal commissionShared;
	private BigDecimal masterMarkup; 
	private BigDecimal chlidMarkup;
	private BigDecimal TDS;
	
	
	
	private Company parentCompany;
	public Company getParentCompany() {
		return parentCompany;
	}


	public void setParentCompany(Company parentCompany) {
		this.parentCompany = parentCompany;
	}


	public Company getInvoiceCompany() {
		return invoiceCompany;
	}


	public void setInvoiceCompany(Company invoiceCompany) {
		this.invoiceCompany = invoiceCompany;
	}


	public int getInvoiceType() {
		return invoiceType;
	}


	public void setInvoiceType(int invoiceType) {
		this.invoiceType = invoiceType;
	}
	private Company invoiceCompany;
	
	private int invoiceType;
	public void setInvoiceCompanies(Company invoiceCompany, Company parentCompany, int invoiceType) {
		this.invoiceCompany = invoiceCompany;
		this.parentCompany = parentCompany;		
		this.invoiceType = invoiceType;
	}
	
	
	public BigDecimal getFinalAmount() {
		return finalAmount;
	}
	public void setFinalAmount(BigDecimal finalAmount) {
		this.finalAmount = finalAmount;
	}
	public BigDecimal getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}
	public BigDecimal getUrProfit() {
		return urProfit;
	}
	public void setUrProfit(BigDecimal urProfit) {
		this.urProfit = urProfit;
	}
	public BigDecimal getUrMarkup() {
		return urMarkup;
	}
	public void setUrMarkup(BigDecimal urMarkup) {
		this.urMarkup = urMarkup;
	}
	public BigDecimal getUrCommission() {
		return urCommission;
	}
	public void setUrCommission(BigDecimal urCommission) {
		this.urCommission = urCommission;
	}
	public BigDecimal getCommissionShared() {
		return commissionShared;
	}
	public void setCommissionShared(BigDecimal commissionShared) {
		this.commissionShared = commissionShared;
	}
	public BigDecimal getMasterMarkup() {
		return masterMarkup;
	}
	public void setMasterMarkup(BigDecimal masterMarkup) {
		this.masterMarkup = masterMarkup;
	}
	public BigDecimal getChlidMarkup() {
		return chlidMarkup;
	}
	public void setChlidMarkup(BigDecimal chlidMarkup) {
		this.chlidMarkup = chlidMarkup;
	}
	public BigDecimal getTDS() {
		return TDS;
	}
	public void setTDS(BigDecimal tDS) {
		TDS = tDS;
	}
	
	
	
	
	
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
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
	public String getCusAddress() {
		return cusAddress;
	}
	public void setCusAddress(String cusAddress) {
		this.cusAddress = cusAddress;
	}
	public String getInvNo() {
		return invNo;
	}
	public void setInvNo(String invNo) {
		this.invNo = invNo;
	}
	public String getActCode() {
		return ActCode;
	}
	public void setActCode(String actCode) {
		ActCode = actCode;
	}
	public String getConsultant() {
		return consultant;
	}
	public void setConsultant(String consultant) {
		this.consultant = consultant;
	}
	public String getBookNo() {
		return bookNo;
	}
	public void setBookNo(String bookNo) {
		this.bookNo = bookNo;
	}
	public String getYourRef() {
		return yourRef;
	}
	public void setYourRef(String yourRef) {
		this.yourRef = yourRef;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAttn() {
		return attn;
	}
	public void setAttn(String attn) {
		this.attn = attn;
	}
	public String getGstType() {
		return gstType;
	}
	public void setGstType(String gstType) {
		this.gstType = gstType;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getTotPrice() {
		return totPrice;
	}
	public void setTotPrice(BigDecimal totPrice) {
		this.totPrice = totPrice;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getGstSummary() {
		return GstSummary;
	}
	public void setGstSummary(String gstSummary) {
		GstSummary = gstSummary;
	}
	public BigDecimal getTax() {
		return tax;
	}
	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}
	public BigDecimal getTotBeforeGst() {
		return totBeforeGst;
	}
	public void setTotBeforeGst(BigDecimal totBeforeGst) {
		this.totBeforeGst = totBeforeGst;
	}
	public BigDecimal getTotGst() {
		return totGst;
	}
	public void setTotGst(BigDecimal totGst) {
		this.totGst = totGst;
	}
	public BigDecimal getTotWithGst() {
		return totWithGst;
	}
	public void setTotWithGst(BigDecimal totWithGst) {
		this.totWithGst = totWithGst;
	}
	public BigDecimal getTotAgentComm() {
		return totAgentComm;
	}
	public void setTotAgentComm(BigDecimal totAgentComm) {
		this.totAgentComm = totAgentComm;
	}
	public BigDecimal getTotAmountSpent() {
		return totAmountSpent;
	}
	public void setTotAmountSpent(BigDecimal totAmountSpent) {
		this.totAmountSpent = totAmountSpent;
	}
	public BigDecimal getTotAmount() {
		return totAmount;
	}
	public void setTotAmount(BigDecimal totAmount) {
		this.totAmount = totAmount;
	}
	public BigDecimal getEachOrderTotComm() {
		return eachOrderTotComm;
	}
	public void setEachOrderTotComm(BigDecimal eachOrderTotComm) {
		this.eachOrderTotComm = eachOrderTotComm;
	}
	public BigDecimal getOrderFinalPrice() {
		return orderFinalPrice;
	}
	public void setOrderFinalPrice(BigDecimal orderFinalPrice) {
		this.orderFinalPrice = orderFinalPrice;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBookedDate() {
		return bookedDate;
	}
	public void setBookedDate(String bookedDate) {
		this.bookedDate = bookedDate;
	}
	public Long getOrderRowId() {
		return orderRowId;
	}
	public void setOrderRowId(Long orderRowId) {
		this.orderRowId = orderRowId;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	/**
	 * @return the hotelOrderRoomInfo
	 */
	public List<HotelOrderRoomInfo> getHotelOrderRoomInfo() {
		return hotelOrderRoomInfo;
	}
	/**
	 * @param hotelOrderRoomInfo the hotelOrderRoomInfo to set
	 */
	public void setHotelOrderRoomInfo(List<HotelOrderRoomInfo> hotelOrderRoomInfo) {
		this.hotelOrderRoomInfo = hotelOrderRoomInfo;
	}
	/**
	 * @return the hotelOrderGuest
	 */
	public List<HotelOrderGuest> getHotelOrderGuest() {
		return hotelOrderGuest;
	}
	/**
	 * @param hotelOrderGuest the hotelOrderGuest to set
	 */
	public void setHotelOrderGuest(List<HotelOrderGuest> hotelOrderGuest) {
		this.hotelOrderGuest = hotelOrderGuest;
	}
	/**
	 * @return the checkInDate
	 */
	public String getCheckInDate() {
		return checkInDate;
	}
	/**
	 * @param checkInDate the checkInDate to set
	 */
	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}
	/**
	 * @return the checkOutDate
	 */
	public String getCheckOutDate() {
		return checkOutDate;
	}
	/**
	 * @param checkOutDate the checkOutDate to set
	 */
	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	/**
	 * @return the txDetails
	 */
	public List<PaymentTransaction> getTxDetails() {
		return txDetails;
	}
	/**
	 * @param txDetails the txDetails to set
	 */
	public void setTxDetails(List<PaymentTransaction> txDetails) {
		this.txDetails = txDetails;
	}
	/**
	 * @return the agentWalletTxDetails
	 */
	public List<WalletAmountTranferHistory> getAgentWalletTxDetails() {
		return agentWalletTxDetails;
	}
	/**
	 * @param agentWalletTxDetails the agentWalletTxDetails to set
	 */
	public void setAgentWalletTxDetails(List<WalletAmountTranferHistory> agentWalletTxDetails) {
		this.agentWalletTxDetails = agentWalletTxDetails;
	}
	/**
	 * @return the userDetails
	 */
	public List<User> getUserDetails() {
		return userDetails;
	}
	/**
	 * @param userDetails the userDetails to set
	 */
	public void setUserDetails(List<User> userDetails) {
		this.userDetails = userDetails;
	}
	public Company getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(Company companyAddress) {
		this.companyAddress = companyAddress;
	}
	public BigDecimal getTotalTDS() {
		return totalTDS;
	}
	public void setTotalTDS(BigDecimal totalTDS) {
		this.totalTDS = totalTDS;
	}
	public BigDecimal getFinalCommWithTdsDeduct() {
		return finalCommWithTdsDeduct;
	}
	public void setFinalCommWithTdsDeduct(BigDecimal finalCommWithTdsDeduct) {
		this.finalCommWithTdsDeduct = finalCommWithTdsDeduct;
	}
	public String getCompanysGstOn() {
		return companysGstOn;
	}
	public void setCompanysGstOn(String companysGstOn) {
		this.companysGstOn = companysGstOn;
	}
	 
	public BigDecimal getDiscountAmount() {
		return DiscountAmount;
	}
	public void setDiscountAmount(BigDecimal discountAmount) {
		DiscountAmount = discountAmount;
	}
	
	 
	
 }
