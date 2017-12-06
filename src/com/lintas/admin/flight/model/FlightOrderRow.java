package com.lintas.admin.flight.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.lintas.admin.common.model.OrderCustomer;
import com.lintas.admin.common.model.Timestampable;
import com.lintas.admin.model.FlightOrderRowCommission;
import com.lintas.admin.model.FlightOrderRowMarkup;
import com.lintas.admin.model.RmConfigTripDetailsModel;
import com.tayyarah.admin.orderrow.rm.structure.FlightOrderRowRmConfigStruct;

@Entity
@Table(name = "flight_order_row")
public class FlightOrderRow extends Timestampable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Transient
	private String weekType;
	@Transient
	private BigDecimal tempManagementFees;
	@Transient
	private BigDecimal managementFeesdummy;
	@Column(name = "price", columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal price;
	@Column(name = "gst_on_markup", columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal gst_on_markup;
	@Column(name = "gst_on_flights", columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal gstOnFlights;

	@Column(name = "final_price", columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal finalPrice;

	@Column(name = "provider_API")
	private String providerAPI;

	@Column(name = "processing_fees", columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal processingFees;
	@Column(name = "mark_up", columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal markUp;
	@Column(name = "taxes",columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal taxes;
	

	private boolean isCreditNoteIssued;
	private boolean isOrderUpdated;
	@Column(name = "is_order_requested",columnDefinition="BIT(1) default 0")
	private boolean isOrderRequested;
	/*@Column(name = "is_international_destination",columnDefinition="BIT(1) default 0")
	private boolean internationalDestination;
	@Column(name = "booking_class")
	private String bookingClass;*/
	@Column(name = "base_to_booking_exchange_rate",columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal baseToBookingExchangeRate;
	
	@Column(name = "api_to_base_exchange_rate" ,columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal apiToBaseExchangeRate;

	@Column(name = "origin")
	private String origin;
	 
	@Column(name = "destination")
	private String destination;

	@Column(name = "departure_date")
	private String departureDate;

	@Column(name = "arrival_date")
	private String arrivalDate;

	@Column(name = "trip_type")
	private String tripType;

	@Column(name = "passenger_count")
	private int passengerCount;

	@Column(name = "invoice_no")
	private String invoiceNo;
	
	

	@Column(name = "pnr")
	private String pnr;

	@Column(name = "information")
	private String information;

	@Column(name = "created_by")
	private String createdBy;
	@Column(name = "updated_by")
	private String updatedBy;

	@Column(name = "status_action")
	private String statusAction;

	@Column(name = "payment_status")
	private String paymentStatus;

	@Column(name = "bookingDate")
	private String bookingDate;

	@Column(name = "orderId")
	private String orderId;
	@Column(name = "transaction_key")
	private String transaction_key;
	@Column(name = "user_id")
	private String userId;

	@Column(name = "airline")
	private String airline;
	
	/* @Column(name = "agencyUserName")
	private String agencyUserName; */

	@Column(name = "api_comments")
	private String api_commit; 

	@Column(name = "user_comments")
	private String user_commit; 

	@Column(name = "company_id")
	private String companyId; 
	@Column(name = "confid_id")
	private String configId; 
	@Column(name = "paid_by")
	private String paidBy; 
	@Column(name = "booking_currency")
	private String bookingCurrency;
	@Column(name = "lastTicketingDate")
	private String lastTicketingDate;
	 @Column(name = "api_traceid")
	 private String apitraceid;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id", referencedColumnName = "id")
	private OrderCustomer flightCustomer;
	/* @ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "credit_note_id", referencedColumnName = "id")
	private CreditNote creditNote; */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "flightOrderRow")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<FlightOrderCustomer> flightOrderCustomers;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "flightOrderRow",targetEntity = FlightOrderCustomerSSR.class)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<FlightOrderCustomerSSR> flightOrderCustomerSSR;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "flightOrderRow")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<FlightOrderCustomerPriceBreakup> flightOrderCustomerPriceBreakups;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "flightOrderRow")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<FlightOrderTripDetail> flightOrderTripDetails;
 
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "flightOrderRow",targetEntity = FlightOrderRowMarkup.class)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<FlightOrderRowMarkup> flightOrderRowMarkupList;
	 
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "flightOrderRow",targetEntity = FlightOrderRowCommission.class)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<FlightOrderRowCommission> flightOrderRowCommissionList;
	

	/*@OneToMany(cascade = CascadeType.ALL, mappedBy = "flightOrderRow",targetEntity = OrderModifiedInfo.class)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<OrderModifiedInfo> flightOrderModifyList;
	 */
	/*	@OneToMany(cascade = CascadeType.ALL, mappedBy = "flightOrderRow",targetEntity = CreditNote.class)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<CreditNote> creditNoteList;
	 */
	
	
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = FlightOrderRowServiceTax.class)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "flightOrderRow_serviceTax_id", referencedColumnName = "id")
	private FlightOrderRowServiceTax flightOrderRowServiceTax ;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "dynamic_rm_config_id", referencedColumnName = "id")
	private  FlightOrderRowRmConfigStruct flightOrderRowRmConfigStruct;
	public FlightOrderRowRmConfigStruct getFlightOrderRowRmConfigStruct() {
		return flightOrderRowRmConfigStruct;
	}
	public void setFlightOrderRowRmConfigStruct(FlightOrderRowRmConfigStruct flightOrderRowRmConfigStruct) {
		this.flightOrderRowRmConfigStruct = flightOrderRowRmConfigStruct;
	}
	@Column(name = "booking_mode")
	private String bookingMode; 
	
	@Column(name = "cancelation_mode")
	private String cancelationMode;
	
	
	@Column(name = "service_tax",columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal serviceTax; 	
	
	@Column(name="supplier_commission",columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal supplierCommission;
	
	@Column(name="supplier_tds",columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal supplierTds;
	
	@Column(name="supplier_price",columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal supplierPrice;
	
	@Column(name="recieved_amount",columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal recievedAmount;
	
	@Column(name="tot_invoice_amount",columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal totInvoiceAmount;
	
	@Column(name="is_knock_off",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean knockOff;
	
	@Column(name = "company_Entity_Id")
	private Long companyEntityId;
	
	@Column(name = "isinsurance_added",columnDefinition = "BOOLEAN DEFAULT false")
	private Boolean isInsuranceAdded;
	
	@Column(name = "insurance_order_rowid")
	private Long insuranceOrderRowId;

	
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = FlightOrderRowGstTax.class)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "flightOrderRow_gstTax_id", referencedColumnName = "id")
	private FlightOrderRowGstTax flightOrderRowGstTax ;
	
	@Column(name = "gds_pnr")
	private String gdsPnr;
	
	public FlightOrderRowGstTax getFlightOrderRowGstTax() {
		return flightOrderRowGstTax;
	}
	public void setFlightOrderRowGstTax(FlightOrderRowGstTax flightOrderRowGstTax) {
		this.flightOrderRowGstTax = flightOrderRowGstTax;
	}
	public BigDecimal getRecievedAmount() {
		return recievedAmount;
	}
	public BigDecimal getTotInvoiceAmount() {
		return totInvoiceAmount;
	}
	public boolean isKnockOff() {
		return knockOff;
	}
	public void setRecievedAmount(BigDecimal recievedAmount) {
		this.recievedAmount = recievedAmount;
	}
	public void setTotInvoiceAmount(BigDecimal totInvoiceAmount) {
		this.totInvoiceAmount = totInvoiceAmount;
	}
	public void setKnockOff(boolean knockOff) {
		this.knockOff = knockOff;
	}
	 
	public BigDecimal getSupplierCommission() {
		return supplierCommission;
	}
	public BigDecimal getSupplierTds() {
		return supplierTds;
	}
	public BigDecimal getSupplierPrice() {
		return supplierPrice;
	}
	public void setSupplierCommission(BigDecimal supplierCommission) {
		this.supplierCommission = supplierCommission;
	}
	public void setSupplierTds(BigDecimal supplierTds) {
		this.supplierTds = supplierTds;
	}
	public void setSupplierPrice(BigDecimal supplierPrice) {
		this.supplierPrice = supplierPrice;
	}
	
	
	public List<FlightOrderRowMarkup> getFlightOrderRowMarkupList() {
		return flightOrderRowMarkupList;
	}

	public void setFlightOrderRowMarkupList(
			List<FlightOrderRowMarkup> flightOrderRowMarkupList) {
		this.flightOrderRowMarkupList = flightOrderRowMarkupList;
	}

	public List<FlightOrderRowCommission> getFlightOrderRowCommissionList() {
		return flightOrderRowCommissionList;
	}

	public void setFlightOrderRowCommissionList(
			List<FlightOrderRowCommission> flightOrderRowCommissionList) {
		this.flightOrderRowCommissionList = flightOrderRowCommissionList;
	}



	 


	public String getTransaction_key() {
		return transaction_key;
	}

	public void setTransaction_key(String transaction_key) {
		this.transaction_key = transaction_key;
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

	public String getApi_commit() {
		return api_commit;
	}

	public void setApi_commit(String api_commit) {
		this.api_commit = api_commit;
	}

	public String getUser_commit() {
		return user_commit;
	}

	public void setUser_commit(String user_commit) {
		this.user_commit = user_commit;
	}

	public String getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	/*public String getAgencyUserName() {
		return agencyUserName;
	}

	public void setAgencyUserName(String agencyUserName) {
		this.agencyUserName = agencyUserName;
	}
	 */


	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public void setLastTicketingDate(String lastTicketingDate) {
		this.lastTicketingDate = lastTicketingDate;
	}

	public OrderCustomer getCustomer() {
		return flightCustomer;
	}

	public void setCustomer(OrderCustomer flightCustomer) {
		this.flightCustomer = flightCustomer;
	}



	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getStatusAction() {
		return statusAction;
	}

	public void setStatusAction(String statusAction) {
		this.statusAction = statusAction;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public OrderCustomer getFlightCustomer() {
		return flightCustomer;
	}

	public void setFlightCustomer(OrderCustomer flightCustomer) {
		this.flightCustomer = flightCustomer;
	}

	public String getLastTicketingDate() {
		return lastTicketingDate;
	}

	public String getPnr() {
		return pnr;
	}

	public void setPnr(String pnr) {
		this.pnr = pnr;
	}

	public String getProviderAPI() {
		return providerAPI;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void setProviderAPI(String providerAPI) {
		this.providerAPI = providerAPI;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public List<FlightOrderCustomer> getFlightOrderCustomers() {
		return flightOrderCustomers;
	}

	public void setFlightOrderCustomers(
			List<FlightOrderCustomer> flightOrderCustomers) {
		this.flightOrderCustomers = flightOrderCustomers;
	}

	public List<FlightOrderCustomerPriceBreakup> getFlightOrderCustomerPriceBreakups() {
		return flightOrderCustomerPriceBreakups;
	}

	public void setFlightOrderCustomerPriceBreakups(
			List<FlightOrderCustomerPriceBreakup> flightOrderCustomerPriceBreakups) {
		this.flightOrderCustomerPriceBreakups = flightOrderCustomerPriceBreakups;
	}

	public List<FlightOrderTripDetail> getFlightOrderTripDetails() {
		return flightOrderTripDetails;
	}

	public void setFlightOrderTripDetails(
			List<FlightOrderTripDetail> flightOrderTripDetails) {
		this.flightOrderTripDetails = flightOrderTripDetails;
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

	public String getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}

	public String getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public String getTripType() {
		return tripType;
	}

	public void setTripType(String tripType) {
		this.tripType = tripType;
	}

	public int getPassengerCount() {
		return passengerCount;
	}

	public BigDecimal getProcessingFees() {
		return processingFees;
	}

	public void setProcessingFees(BigDecimal processingFees) {
		this.processingFees = processingFees;
	}

	public void setPassengerCount(int passengerCount) {
		this.passengerCount = passengerCount;
	}

	public BigDecimal getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(BigDecimal finalPrice) {
		this.finalPrice = finalPrice;
	}

	/**
	 * @return the paidBy
	 */
	public String getPaidBy() {
		return paidBy;
	}

	/**
	 * @param paidBy the paidBy to set
	 */
	public void setPaidBy(String paidBy) {
		this.paidBy = paidBy;
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

	/**
	 * @return the markUp
	 */
	public BigDecimal getMarkUp() {
		return markUp;
	}

	/**
	 * @param markUp the markUp to set
	 */
	public void setMarkUp(BigDecimal markUp) {
		this.markUp = markUp;
	}

	public BigDecimal getTaxes() {
		return taxes;
	}

	public void setTaxes(BigDecimal taxes) {
		this.taxes = taxes;
	}

	public BigDecimal getGst_on_markup() {
		return gst_on_markup;
	}

	public void setGst_on_markup(BigDecimal gst_on_markup) {
		this.gst_on_markup = gst_on_markup;
	}
	/**
	 * @return the creditNote
	 *//*
	public CreditNote getCreditNote() {
		return creditNote;
	}

	  *//**
	  * @param creditNote the creditNote to set
	  *//*
	public void setCreditNote(CreditNote creditNote) {
		this.creditNote = creditNote;
	}
	   */
	public boolean isCreditNoteIssued() {
		return isCreditNoteIssued;
	}

	public void setCreditNoteIssued(boolean isCreditNoteIssued) {
		this.isCreditNoteIssued = isCreditNoteIssued;
	}

	public boolean isOrderUpdated() {
		return isOrderUpdated;
	}

	public void setOrderUpdated(boolean isOrderUpdated) {
		this.isOrderUpdated = isOrderUpdated;
	}

	public BigDecimal getGstOnFlights() {
		return gstOnFlights;
	}

	public void setGstOnFlights(BigDecimal gstOnFlights) {
		this.gstOnFlights = gstOnFlights;
	}

	public boolean isOrderRequested() {
		return isOrderRequested;
	}

	public void setOrderRequested(boolean isOrderRequested) {
		this.isOrderRequested = isOrderRequested;
	}

	
	public String getApiCurrency() {
		return apiCurrency;
	}

	public void setApiCurrency(String apiCurrency) {
		this.apiCurrency = apiCurrency;
	}

	public String getBaseCurrency() {
		return baseCurrency;
	}

	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

	public BigDecimal getBaseToBookingExchangeRate() {
		return baseToBookingExchangeRate;
	}

	public void setBaseToBookingExchangeRate(BigDecimal baseToBookingExchangeRate) {
		this.baseToBookingExchangeRate = baseToBookingExchangeRate;
	}

	public BigDecimal getApiToBaseExchangeRate() {
		return apiToBaseExchangeRate;
	}

	public void setApiToBaseExchangeRate(BigDecimal apiToBaseExchangeRate) {
		this.apiToBaseExchangeRate = apiToBaseExchangeRate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Column(name = "api_currency")
	private String apiCurrency;


	@Column(name = "base_currency")
	private String baseCurrency;
 
	@Column(name = "extra_mealprice", columnDefinition="decimal(20,4) default '0.0'")
	private BigDecimal extramealprice;
	
	@Column(name = "extra_baggageprice", columnDefinition="decimal(20,4) default '0.0'")
	private BigDecimal extrabaggageprice;
	
	public BigDecimal getExtramealprice() {
		return extramealprice;
	}

	public void setExtramealprice(BigDecimal extramealprice) {
		this.extramealprice = extramealprice;
	}

	public BigDecimal getExtrabaggageprice() {
		return extrabaggageprice;
	}

	public void setExtrabaggageprice(BigDecimal extrabaggageprice) {
		this.extrabaggageprice = extrabaggageprice;
	}

	@Column(name = "supplier_discount", columnDefinition="decimal(20,4) default '0.0'")
	private BigDecimal supplierDiscount;
	
	@Column(name = "system_discount", columnDefinition="decimal(20,4) default '0.0'")
	private BigDecimal systemDiscount;
	
	@Column(name = "published_discount", columnDefinition="decimal(20,4) default '0.0'")
	private BigDecimal publishedDiscount;
	
	public BigDecimal getSupplierDiscount() {
		return supplierDiscount;
	}
	public void setSupplierDiscount(BigDecimal supplierDiscount) {
		this.supplierDiscount = supplierDiscount;
	}
	public BigDecimal getSystemDiscount() {
		return systemDiscount;
	}
	public void setSystemDiscount(BigDecimal systemDiscount) {
		this.systemDiscount = systemDiscount;
	}
	public BigDecimal getPublishedDiscount() {
		return publishedDiscount;
	}
	public void setPublishedDiscount(BigDecimal publishedDiscount) {
		this.publishedDiscount = publishedDiscount;
	}

	public List<FlightOrderCustomerSSR> getFlightOrderCustomerSSR() {
		return flightOrderCustomerSSR;
	}

	public void setFlightOrderCustomerSSR(List<FlightOrderCustomerSSR> flightOrderCustomerSSR) {
		this.flightOrderCustomerSSR = flightOrderCustomerSSR;
	}
	public String getApitraceid() {
		return apitraceid;
	}

	public void setApitraceid(String apitraceid) {
		this.apitraceid = apitraceid;
	}

	public String getBookingCurrency() {
		return bookingCurrency;
	}

	public void setBookingCurrency(String bookingCurrency) {
		this.bookingCurrency = bookingCurrency;
	}

	public FlightOrderRowServiceTax getFlightOrderRowServiceTax() {
		return flightOrderRowServiceTax;
	}

	public void setFlightOrderRowServiceTax(FlightOrderRowServiceTax flightOrderRowServiceTax) {
		this.flightOrderRowServiceTax = flightOrderRowServiceTax;
	}

	public String getBookingMode() {
		return bookingMode;
	}

	public void setBookingMode(String bookingMode) {
		this.bookingMode = bookingMode;
	}

	
	public BigDecimal getServiceTax() {
		return serviceTax;
	}

	public void setServiceTax(BigDecimal serviceTax) {
		this.serviceTax = serviceTax;
	}

	public BigDecimal getTempManagementFees() {
		return tempManagementFees;
	}

	public void setTempManagementFees(BigDecimal tempManagementFees) {
		this.tempManagementFees = tempManagementFees;
	}
	
	public String getCancelationMode() {
		return cancelationMode;
	}
	public void setCancelationMode(String cancelationMode) {
		this.cancelationMode = cancelationMode;
	}
	public Long getCompanyEntityId() {
		return companyEntityId;
	}
	public void setCompanyEntityId(Long companyEntityId) {
		this.companyEntityId = companyEntityId;
	}
	public BigDecimal getManagementFeesdummy() {
		return managementFeesdummy;
	}
	public void setManagementFeesdummy(BigDecimal managementFeesdummy) {
		this.managementFeesdummy = managementFeesdummy;
	}
	public String getGdsPnr() {
		return gdsPnr;
	}
	public void setGdsPnr(String gdsPnr) {
		this.gdsPnr = gdsPnr;
	}
	public Boolean getIsInsuranceAdded() {
		return isInsuranceAdded;
	}
	public void setIsInsuranceAdded(Boolean isInsuranceAdded) {
		this.isInsuranceAdded = isInsuranceAdded;
	}
	public Long getInsuranceOrderRowId() {
		return insuranceOrderRowId;
	}
	public void setInsuranceOrderRowId(Long insuranceOrderRowId) {
		this.insuranceOrderRowId = insuranceOrderRowId;
	}
	public String getWeekType() {
		return weekType;
	}
	public void setWeekType(String weekType) {
		this.weekType = weekType;
	}

	 
}
