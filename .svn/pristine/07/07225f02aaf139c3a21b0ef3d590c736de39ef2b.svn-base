package com.lintas.admin.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.lintas.admin.common.model.PaymentTransaction;
import com.lintas.admin.flight.model.FlightOrderCustomer;
import com.lintas.admin.flight.model.FlightOrderCustomerPriceBreakup;
import com.lintas.admin.flight.model.FlightOrderRow;
import com.lintas.admin.flight.model.FlightOrderTripDetail;

public class InvoiceData implements Serializable {

	
	
	
	private static final long serialVersionUID = 1L;
	private String agentName;
	private String firstName;
	private String passengerType;
	private BigDecimal totalMYamount;
	private BigDecimal MYamount;
	private String lastName;
	private String cusAddress;
	private String invNo;
	private String ActCode;
	private String consultant;
	private String bookNo;
	private String yourRef;
	private Date date;
	private String fax;
	private String tel;
	private String mobile;
	private String attn;
	private String gstType;
	private List<String> particulars;
	private String gender;
	private int qty;
	private BigDecimal price;
	private BigDecimal totPrice;
	private BigDecimal totalTax;

	private String currency;
	private String GstSummary;
	private BigDecimal tax;
	private BigDecimal GSTOnMArkup;
	private BigDecimal GSTOnFlights;
	private BigDecimal GSTOnFlightsTotMarkup;
	private BigDecimal totBeforeGst;
	private BigDecimal totGst;
	private BigDecimal totWithGst;
	private BigDecimal totAgentComm;
	private BigDecimal totAmountSpent;
	private BigDecimal totAmount;
	private BigDecimal eachOrderTotComm;
	private BigDecimal orderFinalPrice;
	private BigDecimal totMarkup;
	private String userId;
	private String paymentStatus;
	private String status;
	private String bookedDate;
	private Long orderRowId;
	private String companyId;
	private String orderId;
	private String companysGstOn;
	private BigDecimal GSTOnTotMarkup;
	
	private List<FlightOrderTripDetail>  tripParticulars;
	private List<FlightOrderCustomerPriceBreakup>  priceBreakupParticulars;
	private List<FlightOrderCustomer> orderCustomerParticulars;
	private List<InvoiceData> orderCustomerInvoiceList;
	private List<FlightOrderRow>  flightOrderRowList;
	private List<PaymentTransaction>  txDetails;
	private List<WalletAmountTranferHistory> agentWalletTxDetails;
	private List<User>  userDetails;
	private List<InvoiceData> agentCommissionInvoiceLIst;
	private Company companyAddress;
	private BigDecimal totalTDS;
	private BigDecimal finalCommWithTdsDeduct;
	private FlightOrderRow flightOrderRow;
	 
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
	
	public void setInvoiceCompanies(Company invoiceCompany, Company parentCompany, int invoiceType) {
		this.invoiceCompany = invoiceCompany;
		this.parentCompany = parentCompany;		
		this.invoiceType = invoiceType;
	}
	
	/**@author info
	 * created date 17-10-2015
	 */
	
	private Company parentCompany;
	private Company invoiceCompany;
	
	private int invoiceType;
	
	
	private BigDecimal finalAmount;
	
	private BigDecimal payAmount;
	private BigDecimal urProfit;
	private BigDecimal urMarkup;
	private BigDecimal urCommission;
	private BigDecimal commissionShared;
	private BigDecimal masterMarkup;
	private BigDecimal chlidMarkup;
	private BigDecimal TDS;
	
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

	public static final int MY_INVOICE = 0;
	public static final int AFFILIATE_INVOICE = 1;
	public static final int CUSTOMER_INVOICE = 2;
	
	
	
	
	public Company getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(Company companyAddress) {
		this.companyAddress = companyAddress;
	}
	public BigDecimal getTotalTax() {
		return totalTax;
	}
	public void setTotalTax(BigDecimal totalTax) {
		this.totalTax = totalTax;
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
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
	public List<String> getParticulars() {
		return particulars;
	}
	public void setParticulars(List<String> particulars) {
		this.particulars = particulars;
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
	/**
	 * @return the tripParticulars
	 */
	public List<FlightOrderTripDetail> getTripParticulars() {
		return tripParticulars;
	}
	/**
	 * @param tripParticulars the tripParticulars to set
	 */
	public void setTripParticulars(List<FlightOrderTripDetail> tripParticulars) {
		this.tripParticulars = tripParticulars;
	}
	/**
	 * @return the priceBreakupParticulars
	 */
	public List<FlightOrderCustomerPriceBreakup> getPriceBreakupParticulars() {
		return priceBreakupParticulars;
	}
	/**
	 * @param priceBreakupParticulars the priceBreakupParticulars to set
	 */
	public void setPriceBreakupParticulars(List<FlightOrderCustomerPriceBreakup> priceBreakupParticulars) {
		this.priceBreakupParticulars = priceBreakupParticulars;
	}
	/**
	 * @return the orderCustomerParticulars
	 */
	public List<FlightOrderCustomer> getOrderCustomerParticulars() {
		return orderCustomerParticulars;
	}
	/**
	 * @param orderCustomerParticulars the orderCustomerParticulars to set
	 */
	public void setOrderCustomerParticulars(List<FlightOrderCustomer> orderCustomerParticulars) {
		this.orderCustomerParticulars = orderCustomerParticulars;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the orderCustomerList
	 */
	public List<InvoiceData> getOrderCustomerList() {
		return orderCustomerInvoiceList;
	}
	/**
	 * @param orderCustomerList the orderCustomerList to set
	 */
	public void setOrderCustomerList(List<InvoiceData> orderCustomerList) {
		this.orderCustomerInvoiceList = orderCustomerList;
	}
	/**
	 * @return the flightOrderRowList
	 */
	public List<FlightOrderRow> getFlightOrderRowList() {
		return flightOrderRowList;
	}
	/**
	 * @param flightOrderRowList the flightOrderRowList to set
	 */
	public void setFlightOrderRowList(List<FlightOrderRow> flightOrderRowList) {
		this.flightOrderRowList = flightOrderRowList;
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
	 * @return the agentName
	 */
	public String getAgentName() {
		return agentName;
	}
	/**
	 * @param agentName the agentName to set
	 */
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	/**
	 * @return the userDetails
	 */
	public List<User>  getUserDetails() {
		return userDetails;
	}
	/**
	 * @param userDetails the userDetails to set
	 */
	public void setUserDetails( List<User> userDetails) {
		this.userDetails = userDetails;
	}
	/**
	 * @return the totAgentComm
	 */
	public BigDecimal getTotAgentComm() {
		return totAgentComm;
	}
	/**
	 * @param totAgentComm the totAgentComm to set
	 */
	public void setTotAgentComm(BigDecimal totAgentComm) {
		this.totAgentComm = totAgentComm;
	}
	/**
	 * @return the orderId
	 */
	public String getOrderId() {
		return orderId;
	}
	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	/**
	 * @return the eachOrderTotComm
	 */
	public BigDecimal getEachOrderTotComm() {
		return eachOrderTotComm;
	}
	/**
	 * @param eachOrderTotComm the eachOrderTotComm to set
	 */
	public void setEachOrderTotComm(BigDecimal eachOrderTotComm) {
		this.eachOrderTotComm = eachOrderTotComm;
	}
	/**
	 * @return the totAmountSpent
	 */
	public BigDecimal getTotAmountSpent() {
		return totAmountSpent;
	}
	/**
	 * @param totAmountSpent the totAmountSpent to set
	 */
	public void setTotAmountSpent(BigDecimal totAmountSpent) {
		this.totAmountSpent = totAmountSpent;
	}
	/**
	 * @return the totAmount
	 */
	public BigDecimal getTotAmount() {
		return totAmount;
	}
	/**
	 * @param totAmount the totAmount to set
	 */
	public void setTotAmount(BigDecimal totAmount) {
		this.totAmount = totAmount;
	}
	/**
	 * @return the orderFinalPrice
	 */
	public BigDecimal getOrderFinalPrice() {
		return orderFinalPrice;
	}
	/**
	 * @param orderFinalPrice the orderFinalPrice to set
	 */
	public void setOrderFinalPrice(BigDecimal orderFinalPrice) {
		this.orderFinalPrice = orderFinalPrice;
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
	/**
	 * @return the paymentStatus
	 */
	public String getPaymentStatus() {
		return paymentStatus;
	}
	/**
	 * @param paymentStatus the paymentStatus to set
	 */
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	/**
	 * @return the bookedDate
	 */
	public String getBookedDate() {
		return bookedDate;
	}
	/**
	 * @param bookedDate the bookedDate to set
	 */
	public void setBookedDate(String bookedDate) {
		this.bookedDate = bookedDate;
	}
	/**
	 * @return the agentCommissionInvoiceLIst
	 */
	public List<InvoiceData> getAgentCommissionInvoiceLIst() {
		return agentCommissionInvoiceLIst;
	}
	/**
	 * @param agentCommissionInvoiceLIst the agentCommissionInvoiceLIst to set
	 */
	public void setAgentCommissionInvoiceLIst(
			List<InvoiceData> agentCommissionInvoiceLIst) {
		this.agentCommissionInvoiceLIst = agentCommissionInvoiceLIst;
	}
	/**
	 * @return the orderRowId
	 */
	public Long getOrderRowId() {
		return orderRowId;
	}
	/**
	 * @param orderRowId the orderRowId to set
	 */
	public void setOrderRowId(Long orderRowId) {
		this.orderRowId = orderRowId;
	}
	/**
	 * @return the companyId
	 */
	public String getCompanyId() {
		return companyId;
	}
	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	/**
	 * @return the totMarkup
	 */
	public BigDecimal getTotMarkup() {
		return totMarkup;
	}
	/**
	 * @param totMarkup the totMarkup to set
	 */
	public void setTotMarkup(BigDecimal totMarkup) {
		this.totMarkup = totMarkup;
	}
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public BigDecimal getGSTOnMArkup() {
		return GSTOnMArkup;
	}
	public void setGSTOnMArkup(BigDecimal gSTOnMArkup) {
		GSTOnMArkup = gSTOnMArkup;
	}
	public String getPassengerType() {
		return passengerType;
	}
	public void setPassengerType(String setPassengerType) {
		this.passengerType = setPassengerType;
	}
	public BigDecimal getTotalMYamount() {
		return totalMYamount;
	}
	public void setTotalMYamount(BigDecimal totalMYamount) {
		this.totalMYamount = totalMYamount;
	}
	public BigDecimal getMYamount() {
		return MYamount;
	}
	public void setMYamount(BigDecimal mYamount) {
		MYamount = mYamount;
	}
	  
	public String getCompanysGstOn() {
		return companysGstOn;
	}
	public void setCompanysGstOn(String companysGstOn) {
		this.companysGstOn = companysGstOn;
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
	public BigDecimal getGSTOnFlights() {
		return GSTOnFlights;
	}
	public void setGSTOnFlights(BigDecimal gSTOnFlights) {
		GSTOnFlights = gSTOnFlights;
	}
	public BigDecimal getGSTOnFlightsTotMarkup() {
		return GSTOnFlightsTotMarkup;
	}
	public void setGSTOnFlightsTotMarkup(BigDecimal gSTOnFlightsTotMarkup) {
		GSTOnFlightsTotMarkup = gSTOnFlightsTotMarkup;
	}
	public BigDecimal getGSTOnTotMarkup() {
		return GSTOnTotMarkup;
	}
	public void setGSTOnTotMarkup(BigDecimal gSTOnTotMarkup) {
		GSTOnTotMarkup = gSTOnTotMarkup;
	}
	public FlightOrderRow getFlightOrderRow() {
		return flightOrderRow;
	}
	public void setFlightOrderRow(FlightOrderRow flightOrderRow) {
		this.flightOrderRow = flightOrderRow;
	}
	
	 
}
