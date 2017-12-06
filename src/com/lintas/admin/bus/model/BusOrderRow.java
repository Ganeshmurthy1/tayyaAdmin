package com.lintas.admin.bus.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.lintas.admin.common.model.OrderCustomer;
import com.lintas.admin.common.model.Timestampable;
import com.lintas.admin.model.Company;
import com.tayyarah.admin.orderrow.rm.structure.BusOrderRowRmConfigStruct;
import com.tayyarah.bus.model.BusOrderCustomerDetail;
import com.tayyarah.bus.model.BusOrderRowCommission;
import com.tayyarah.bus.model.BusOrderRowMarkup;

@Entity
@Table(name = "bus_order_row")
public class BusOrderRow extends Timestampable {

	private static final long serialVersionUID = 1L;

	@Transient
	private String travelDateTemp;

	@Transient
	private String  busBookingDate;


	@Transient
	private Company company;

	@Column(name = "booking_date")
	@Temporal(TemporalType.DATE)
	private Date bookingDate;

	@Column(name = "company_Entity_Id")
	private Long companyEntityId;
	@Column(name = "bus_type")
	private String busType;
	@Column(name = "emp_name")
	private String empName;

	@Column(name = "confirmation_number")
	private String confirmationNumber;
	private String remarks;
	@Column(name = "location")
	private String location;
	@Column(name = "pickup")
	private String pickUp;
	@Column(name = "dropoff")
	private String dropOff;
	@Column(name = "travel_date")
	@Temporal(TemporalType.DATE)
	private Date travelDate;

	@Column(name = "supplier_name")
	private String supplierName;
	@Column(name = "supplier_price", columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal supplierPrice;
	@Column(name = "base_price", columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal basePrice;
	@Column(name = "other_taxes", columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal otherTaxes;
	@Column(name = "management_fee", columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal managementFee;
	@Column(name = "convenience_fee", columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal convenienceFee;
	@Column(name = "service_tax", columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal serviceTax;

	@Column(name = "total_amount", columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal totalAmount;

	@Column(name = "invoice_no")
	private String invoiceNo;
	@Column(name = "created_by")
	private String createdBy;
	@Column(name = "updated_by")
	private String updatedBy;

	@Column(name = "status_action")
	private String statusAction;

	@Column(name = "payment_status")
	private String paymentStatus;
	@Column(name = "orderId")
	private String orderId;
	@Column(name = "transaction_key")
	private String transactionKey;
	@Column(name = "user_id")
	private String userId;
	@Column(name = "api_comments")
	private String apiComments;

	@Column(name = "user_comments")
	private String userComments;

	@Column(name = "company_id")
	private String companyId;
	@Column(name = "busCompanyName")
	private String busCompanyName;
	@Column(name = "confid_id")
	private String configId;
	@Column(name = "paid_by")
	private String paidBy;
	@Column(name = "booking_currency")
	private String bookingCurrency;
	@Column(name = "processing_fees", columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal processingFees;
	@Column(name = "mark_up", columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal markUp;
	@Column(name = "taxes",columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal taxes;

	@Column(name = "is_creditnote_issues",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean creditNoteIssued;
	@Column(name = "is_order_updated",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean orderUpdated;
	@Column(name = "is_order_requested",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean orderRequested;
	@Column(name = "base_to_booking_exchange_rate",columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal baseToBookingExchangeRate;

	@Column(name = "api_to_base_exchange_rate" ,columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal apiToBaseExchangeRate;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id", referencedColumnName = "id")
	private OrderCustomer orderCustomer;

	@ManyToOne(cascade = CascadeType.ALL, targetEntity = BusOrderRowServiceTax.class)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "busOrderRow_serviceTax_id", referencedColumnName = "id")
	private BusOrderRowServiceTax busOrderRowServiceTax ;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "busOrderRow",targetEntity = BusOrderCustomerDetail.class)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<BusOrderCustomerDetail> BusOrderCustomerDetails;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "busOrderRow",targetEntity = BusOrderRowMarkup.class)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<BusOrderRowMarkup> busOrderRowMarkupList;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "busOrderRow",targetEntity = BusOrderRowCommission.class)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<BusOrderRowCommission> busOrderRowCommissionList;
	@Column(name = "booking_mode")
	private String bookingMode;

	@Column(name = "origin")
	private String origin;
	@Column(name = "destination")
	private String destination;

	@Column(name = "cancel_mode")
	private String cancelMode;

	@Column(name = "cancellation_policy")
	private String cancellationPolicy;

	@Column(name = "operator_pnr")
	private String operatorPnr;

	@Column(name = "api_tripcode")
	private String apiTripCode;

	@Column(name = "departure_time")
	private String departureTime;
	@Column(name = "arrival_time")
	private String arrivalTime;

	@Column(name="recieved_amount",columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal recievedAmount;

	@Column(name="tot_invoice_amount",columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal totInvoiceAmount;

	@Column(name="is_knock_off",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean knockOff;

	@Transient
	private String appkey;

	@Transient
	private String searchkey;


	@ManyToOne(cascade = CascadeType.ALL, targetEntity = BusOrderRowGstTax.class)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "busOrderRow_gstTax_id", referencedColumnName = "id")
	private BusOrderRowGstTax busOrderRowGstTax ;

	@Column(name = "total_gst_tax", columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal totalGstTax;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "dynamic_rm_config_id", referencedColumnName = "id")
	private  BusOrderRowRmConfigStruct busOrderRowRmConfigStruct;
	 
	public BusOrderRowRmConfigStruct getBusOrderRowRmConfigStruct() {
		return busOrderRowRmConfigStruct;
	}
	public void setBusOrderRowRmConfigStruct(BusOrderRowRmConfigStruct busOrderRowRmConfigStruct) {
		this.busOrderRowRmConfigStruct = busOrderRowRmConfigStruct;
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


	public String getDepartureTime() {
		return departureTime;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getCancellationPolicy() {
		return cancellationPolicy;
	}

	public String getOperatorPnr() {
		return operatorPnr;
	}

	public String getApiTripCode() {
		return apiTripCode;
	}

	public void setCancellationPolicy(String cancellationPolicy) {
		this.cancellationPolicy = cancellationPolicy;
	}

	public void setOperatorPnr(String operatorPnr) {
		this.operatorPnr = operatorPnr;
	}

	public void setApiTripCode(String apiTripCode) {
		this.apiTripCode = apiTripCode;
	}


	public String getCancelMode() {
		return cancelMode;
	}

	public void setCancelMode(String cancelMode) {
		this.cancelMode = cancelMode;
	}

	public String getOrigin() {
		return origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getBusBookingDate() {
		return busBookingDate;
	}

	public void setBusBookingDate(String busBookingDate) {
		this.busBookingDate = busBookingDate;
	}

	public String getTravelDateTemp() {
		return travelDateTemp;
	}

	public void setTravelDateTemp(String travelDateTemp) {
		this.travelDateTemp = travelDateTemp;
	}

	public String getBusType() {
		return busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

	public String getConfirmationNumber() {
		return confirmationNumber;
	}

	public void setConfirmationNumber(String confirmationNumber) {
		this.confirmationNumber = confirmationNumber;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPickUp() {
		return pickUp;
	}

	public void setPickUp(String pickUp) {
		this.pickUp = pickUp;
	}

	public String getDropOff() {
		return dropOff;
	}

	public void setDropOff(String dropOff) {
		this.dropOff = dropOff;
	}

	public Date getTravelDate() {
		return travelDate;
	}

	public void setTravelDate(Date travelDate) {
		this.travelDate = travelDate;
	}

	public BigDecimal getSupplierPrice() {
		return supplierPrice;
	}

	public void setSupplierPrice(BigDecimal supplierPrice) {
		this.supplierPrice = supplierPrice;
	}

	public BigDecimal getBasePrice() {
		return basePrice;
	}


	public void setBasePrice(BigDecimal basePrice) {
		this.basePrice = basePrice;
	}


	public BigDecimal getOtherTaxes() {
		return otherTaxes;
	}


	public void setOtherTaxes(BigDecimal otherTaxes) {
		this.otherTaxes = otherTaxes;
	}


	public BigDecimal getManagementFee() {
		return managementFee;
	}


	public void setManagementFee(BigDecimal managementFee) {
		this.managementFee = managementFee;
	}


	public BigDecimal getConvenienceFee() {
		return convenienceFee;
	}


	public void setConvenienceFee(BigDecimal convenienceFee) {
		this.convenienceFee = convenienceFee;
	}


	public BigDecimal getServiceTax() {
		return serviceTax;
	}


	public void setServiceTax(BigDecimal serviceTax) {
		this.serviceTax = serviceTax;
	}


	public BigDecimal getTotalAmount() {
		return totalAmount;
	}


	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}


	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}


	public String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	public String getUpdatedBy() {
		return updatedBy;
	}


	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
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


	public String getOrderId() {
		return orderId;
	}


	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


	public String getTransactionKey() {
		return transactionKey;
	}


	public void setTransactionKey(String transactionKey) {
		this.transactionKey = transactionKey;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getApiComments() {
		return apiComments;
	}


	public void setApiComments(String apiComments) {
		this.apiComments = apiComments;
	}


	public String getUserComments() {
		return userComments;
	}


	public void setUserComments(String userComments) {
		this.userComments = userComments;
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


	public String getPaidBy() {
		return paidBy;
	}


	public void setPaidBy(String paidBy) {
		this.paidBy = paidBy;
	}


	public String getBookingCurrency() {
		return bookingCurrency;
	}


	public void setBookingCurrency(String bookingCurrency) {
		this.bookingCurrency = bookingCurrency;
	}


	public BigDecimal getProcessingFees() {
		return processingFees;
	}


	public void setProcessingFees(BigDecimal processingFees) {
		this.processingFees = processingFees;
	}


	public BigDecimal getMarkUp() {
		return markUp;
	}


	public void setMarkUp(BigDecimal markUp) {
		this.markUp = markUp;
	}


	public BigDecimal getTaxes() {
		return taxes;
	}


	public void setTaxes(BigDecimal taxes) {
		this.taxes = taxes;
	}


	public boolean isCreditNoteIssued() {
		return creditNoteIssued;
	}


	public void setCreditNoteIssued(boolean creditNoteIssued) {
		this.creditNoteIssued = creditNoteIssued;
	}


	public boolean isOrderUpdated() {
		return orderUpdated;
	}


	public void setOrderUpdated(boolean orderUpdated) {
		this.orderUpdated = orderUpdated;
	}


	public boolean isOrderRequested() {
		return orderRequested;
	}


	public void setOrderRequested(boolean orderRequested) {
		this.orderRequested = orderRequested;
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


	public OrderCustomer getOrderCustomer() {
		return orderCustomer;
	}


	public void setOrderCustomer(OrderCustomer orderCustomer) {
		this.orderCustomer = orderCustomer;
	}


	public BusOrderRowServiceTax getBusOrderRowServiceTax() {
		return busOrderRowServiceTax;
	}

	public void setBusOrderRowServiceTax(BusOrderRowServiceTax busOrderRowServiceTax) {
		this.busOrderRowServiceTax = busOrderRowServiceTax;
	}

	public String getBusCompanyName() {
		return busCompanyName;
	}

	public void setBusCompanyName(String busCompanyName) {
		this.busCompanyName = busCompanyName;
	}


	public Company getCompany() {

		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getBookingMode() {
		return bookingMode;
	}

	public void setBookingMode(String bookingMode) {
		this.bookingMode = bookingMode;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public List<BusOrderRowMarkup> getBusOrderRowMarkupList() {
		return busOrderRowMarkupList;
	}

	public void setBusOrderRowMarkupList(List<BusOrderRowMarkup> busOrderRowMarkupList) {
		this.busOrderRowMarkupList = busOrderRowMarkupList;
	}

	public List<BusOrderRowCommission> getBusOrderRowCommissionList() {
		return busOrderRowCommissionList;
	}

	public void setBusOrderRowCommissionList(List<BusOrderRowCommission> busOrderRowCommissionList) {
		this.busOrderRowCommissionList = busOrderRowCommissionList;
	}

	public List<BusOrderCustomerDetail> getBusOrderCustomerDetails() {
		return BusOrderCustomerDetails;
	}

	public void setBusOrderCustomerDetails(List<BusOrderCustomerDetail> busOrderCustomerDetails) {
		BusOrderCustomerDetails = busOrderCustomerDetails;
	}
	public String getAppkey() {
		return appkey;
	}
	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}
	public String getSearchkey() {
		return searchkey;
	}
	public void setSearchkey(String searchkey) {
		this.searchkey = searchkey;
	}
	public BusOrderRowGstTax getBusOrderRowGstTax() {
		return busOrderRowGstTax;
	}
	public void setBusOrderRowGstTax(BusOrderRowGstTax busOrderRowGstTax) {
		this.busOrderRowGstTax = busOrderRowGstTax;
	}
	public Long getCompanyEntityId() {
		return companyEntityId;
	}
	public void setCompanyEntityId(Long companyEntityId) {
		this.companyEntityId = companyEntityId;
	}
	public BigDecimal getTotalGstTax() {
		return totalGstTax;
	}
	public void setTotalGstTax(BigDecimal totalGstTax) {
		this.totalGstTax = totalGstTax;
	}
	public Date getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

}
