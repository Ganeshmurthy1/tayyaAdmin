package com.lintas.admin.insurance.model;
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

import com.admin.insurance.model.InsuranceOrderCustomerDetail;
import com.admin.insurance.model.InsuranceOrderRowCommission;
import com.admin.insurance.model.InsuranceOrderRowMarkup;
import com.lintas.admin.common.model.OrderCustomer;
import com.lintas.admin.common.model.Timestampable;
import com.lintas.admin.model.Company;
import com.lintas.utility.DateConversion;
import com.tayyarah.admin.orderrow.rm.structure.InsuranceOrderRowRmConfigStruct;

@Entity
@Table(name = "insurance_order_row")
public class InsuranceOrderRow extends Timestampable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Transient
	private String invoiceDate;
	@Transient
	private String insuranceBookingDate;
	@Transient
	private Company company;
	@Transient
	private String travelDateTemp;

	@Column(name="travelDate")
	@Temporal(TemporalType.DATE)
	private Date travelDate;
	@Column(name = "remarks")
	private String remarks;
	@Column(name = "emp_name")
	private String empName;
	@Column(name = "confirmation_number")
	private String confirmationNumber;
	@Column(name = "country_of_travel")
	private boolean countryOfTravel;
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
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = InsuranceOrderRowServiceTax.class)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "insuranceOrderRow_serviceTax_id", referencedColumnName = "id")
	private InsuranceOrderRowServiceTax insuranceOrderRowServiceTax ;
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
	@Column(name = "confid_id")
	private String configId;
	@Column(name = "paid_by")
	private String paidBy;
	@Column(name = "booking_currency")
	private String bookingCurrency;
	@Column(name = "processing_fees", columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal processingFees;
	@Column(name = "mark_up", columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal markUpamount;
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
	@Column(name = "booking_mode")
	private String bookingMode;
	@Column(name="cancel_mode")
	private String cancelMode;
	@Column(name="recieved_amount",columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal recievedAmount;	
	@Column(name="tot_invoice_amount",columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal totInvoiceAmount;	
	@Column(name="is_knock_off",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean knockOff;	
	@Column(name="booking_date")
	@Temporal(TemporalType.DATE)
	private Date bookingDate;	
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = InsuranceOrderRowGstTax.class)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "insuranceorderrow_gsttax_id", referencedColumnName = "id")
	private InsuranceOrderRowGstTax insuranceOrderRowGstTax ;
	@Column(name = "company_entity_id")
	private Long companyEntityId;
	@Column(name = "total_gst_tax",columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal TotalGstTax;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "insuranceOrderRow",targetEntity = InsuranceOrderCustomerDetail.class)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<InsuranceOrderCustomerDetail> insuranceOrderCustomerDetails;
	

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "insuranceOrderRow",targetEntity = InsuranceOrderRowMarkup.class)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<InsuranceOrderRowMarkup> insuranceOrderRowMarkupList;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "insuranceOrderRow",targetEntity = InsuranceOrderRowCommission.class)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<InsuranceOrderRowCommission> insuranceOrderRowCommissionList;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "dynamic_rm_config_id", referencedColumnName = "id")
	private  InsuranceOrderRowRmConfigStruct insuranceOrderRowRmConfigStruct;
	public InsuranceOrderRowRmConfigStruct getInsuranceOrderRowRmConfigStruct() {
		return insuranceOrderRowRmConfigStruct;
	}
	public void setInsuranceOrderRowRmConfigStruct(InsuranceOrderRowRmConfigStruct insuranceOrderRowRmConfigStruct) {
		this.insuranceOrderRowRmConfigStruct = insuranceOrderRowRmConfigStruct;
	}
	
	@Column(name = "insured_product")
	private String insuredProduct;
	@Column(name = "insured_product_orderrowid")
	private Long insuredProductOrderRowId;
	
	public String getInsuredProduct() {
		return insuredProduct;
	}
	public Long getInsuredProductOrderRowId() {
		return insuredProductOrderRowId;
	}
	public void setInsuredProduct(String insuredProduct) {
		this.insuredProduct = insuredProduct;
	}
	public void setInsuredProductOrderRowId(Long insuredProductOrderRowId) {
		this.insuredProductOrderRowId = insuredProductOrderRowId;
	}
	
	public BigDecimal getTotalGstTax() {
		return TotalGstTax;
	}
	public void setTotalGstTax(BigDecimal totalGstTax) {
		TotalGstTax = totalGstTax;
	}
	public Long getCompanyEntityId() {
		return companyEntityId;
	}
	public void setCompanyEntityId(Long companyEntityId) {
		this.companyEntityId = companyEntityId;
	}

	public InsuranceOrderRowGstTax getInsuranceOrderRowGstTax() {
		return insuranceOrderRowGstTax;
	}

	public void setInsuranceOrderRowGstTax(InsuranceOrderRowGstTax insuranceOrderRowGstTax) {
		this.insuranceOrderRowGstTax = insuranceOrderRowGstTax;
	}

	public String getInsuranceBookingDate() {
		return insuranceBookingDate;
	}

	public void setInsuranceBookingDate(String insuranceBookingDate) {
		this.insuranceBookingDate = insuranceBookingDate;
	}

	public InsuranceOrderRowServiceTax getInsuranceOrderRowServiceTax() {
		return insuranceOrderRowServiceTax;
	}

	public void setInsuranceOrderRowServiceTax(InsuranceOrderRowServiceTax insuranceOrderRowServiceTax) {
		this.insuranceOrderRowServiceTax = insuranceOrderRowServiceTax;
	}


	public String getConfirmationNumber() {
		return confirmationNumber;
	}

	public void setConfirmationNumber(String confirmationNumber) {
		this.confirmationNumber = confirmationNumber;
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



	public boolean getCountryOfTravel() {
		return countryOfTravel;
	}

	public void setCountryOfTravel(boolean countryOfTravel) {
		this.countryOfTravel = countryOfTravel;
	}



	public BigDecimal getMarkUpamount() {
		return markUpamount;
	}

	public void setMarkUpamount(BigDecimal markUpamount) {
		this.markUpamount = markUpamount;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}



	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	public Date getTravelDate() {
		return travelDate;
	}

	public void setTravelDate(Date travelDate) {
		this.travelDate = travelDate;
	}

	public String getTravelDateTemp() {
		return travelDateTemp;
	}

	public void setTravelDateTemp(String travelDateTemp) {
		this.travelDateTemp = travelDateTemp;
	}

	public String getBookingMode() {
		return bookingMode;
	}

	public void setBookingMode(String bookingMode) {
		this.bookingMode = bookingMode;
	}
	public String getInvoiceDate() {
		if(getCreatedAt()!=null)
			invoiceDate=DateConversion.convertDateToStringDatewirhDDMonthYear(getCreatedAt());
		return invoiceDate;
	}
	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getCancelMode() {
		return cancelMode;
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
	public void setCancelMode(String cancelMode) {
		this.cancelMode = cancelMode;
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
	public List<InsuranceOrderCustomerDetail> getInsuranceOrderCustomerDetails() {
		return insuranceOrderCustomerDetails;
	}
	public void setInsuranceOrderCustomerDetails(List<InsuranceOrderCustomerDetail> insuranceOrderCustomerDetails) {
		this.insuranceOrderCustomerDetails = insuranceOrderCustomerDetails;
	}
	public List<InsuranceOrderRowCommission> getInsuranceOrderRowCommissionList() {
		return insuranceOrderRowCommissionList;
	}
	public void setInsuranceOrderRowCommissionList(List<InsuranceOrderRowCommission> insuranceOrderRowCommissionList) {
		this.insuranceOrderRowCommissionList = insuranceOrderRowCommissionList;
	}
	public List<InsuranceOrderRowMarkup> getInsuranceOrderRowMarkupList() {
		return insuranceOrderRowMarkupList;
	}
	public void setInsuranceOrderRowMarkupList(List<InsuranceOrderRowMarkup> insuranceOrderRowMarkupList) {
		this.insuranceOrderRowMarkupList = insuranceOrderRowMarkupList;
	}
	public Date getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}



}
