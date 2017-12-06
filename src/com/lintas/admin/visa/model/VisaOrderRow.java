package com.lintas.admin.visa.model;

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

import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.common.model.OrderCustomer;
import com.lintas.admin.common.model.Timestampable;
import com.lintas.admin.model.Company;
import com.tayyarah.admin.orderrow.rm.structure.TrainOrderRowRmConfigStruct;
import com.tayyarah.admin.orderrow.rm.structure.VisaOrderRowRmConfigStruct;
import com.tayyarah.train.model.TrainOrderCustomer;
import com.tayyarah.visa.model.VisaOrderCustomer;
@Entity
@Table(name = "visa_order_row")
public class VisaOrderRow extends Timestampable{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	
	@Transient
	private Company company;
	
	@Transient
	private String visaBookingDate;
	
	@Column(name = "company_Entity_Id")
	private Long companyEntityId;
	
	@Column(name="booking_date")
	@Temporal(TemporalType.DATE)
	private Date  bookingDate;
 
	@Column(name="travelDate")
	@Temporal(TemporalType.DATE)
	private Date travelDate;

	@Column(name = "remarks")
	private String remarks;
	@Column(name = "emp_name")
	private String empNmae;
	@Column(name = "user_comments")
	private String userComments;
	
	@Column(name = "confirmation_number")
	private String confirmationNumber;
	
	@Column(name = "vfs_charges", columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal vfsCharges;
	@Column(name = "courier_charges", columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal courierCharges;
	@Column(name = "supplier_Name")
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
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = VisaOrderRowServiceTax.class)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "visaOrderRow_serviceTax_id", referencedColumnName = "id")
	private VisaOrderRowServiceTax visaOrderRowServiceTax ;
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
	 
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = VisaOrderRowGstTax.class)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "visaOrderRow_gstTax_id", referencedColumnName = "id")
	private VisaOrderRowGstTax visaOrderRowGstTax ;
	
	@Column(name = "total_gst_tax",columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal TotalGstTax;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "visaOrderRow",targetEntity = VisaOrderCustomer.class)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<VisaOrderCustomer> visaOrderCustomerList;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "dynamic_rm_config_id", referencedColumnName = "id")
	private  VisaOrderRowRmConfigStruct visaOrderRowRmConfigStruct;
	public VisaOrderRowRmConfigStruct getVisaOrderRowRmConfigStruct() {
		return visaOrderRowRmConfigStruct;
	}
	public void setVisaOrderRowRmConfigStruct(VisaOrderRowRmConfigStruct visaOrderRowRmConfigStruct) {
		this.visaOrderRowRmConfigStruct = visaOrderRowRmConfigStruct;
	}
	public BigDecimal getRecievedAmount() {
		return recievedAmount;
	}
	public List<VisaOrderCustomer> getVisaOrderCustomerList() {
		return visaOrderCustomerList;
	}
	public void setVisaOrderCustomerList(List<VisaOrderCustomer> visaOrderCustomerList) {
		this.visaOrderCustomerList = visaOrderCustomerList;
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
	
	
	public String getCancelMode() {
		return cancelMode;
	}
	public void setCancelMode(String cancelMode) {
		this.cancelMode = cancelMode;
	}
	public String getConfirmationNumber() {
		return confirmationNumber;
	}
	public void setConfirmationNumber(String confirmationNumber) {
		this.confirmationNumber = confirmationNumber;
	}
	public BigDecimal getVfsCharges() {
		return vfsCharges;
	}
	public void setVfsCharges(BigDecimal vfsCharges) {
		this.vfsCharges = vfsCharges;
	}
	public BigDecimal getCourierCharges() {
		return courierCharges;
	}


	public void setCourierCharges(BigDecimal courierCharges) {
		this.courierCharges = courierCharges;
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


	public VisaOrderRowServiceTax getVisaOrderRowServiceTax() {
		return visaOrderRowServiceTax;
	}


	public void setVisaOrderRowServiceTax(VisaOrderRowServiceTax visaOrderRowServiceTax) {
		this.visaOrderRowServiceTax = visaOrderRowServiceTax;
	}


	/*public String getBookingDate() {
		if(getCreatedAt()!=null) 
			bookingDate=DateConversion.convertDateToStringDatewirhDDMonthYear(getCreatedAt());
		return bookingDate;
	}

	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}*/

	public Company getCompany() {
		if(getCompanyId()!=null) 
			company=new CompanyDAO().getCompanyProfile(Integer.parseInt(getCompanyId()));
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}


	public Date getTravelDate() {
		return travelDate;
	}


	public void setTravelDate(Date travelDate) {
		this.travelDate = travelDate;
	}


	public String getRemarks() {
		return remarks;
	}


	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	public String getEmpNmae() {
		return empNmae;
	}


	public void setEmpNmae(String empNmae) {
		this.empNmae = empNmae;
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


	
	public String getUserComments() {
		return userComments;
	}
	public void setUserComments(String userComments) {
		this.userComments = userComments;
	}
	public VisaOrderRowGstTax getVisaOrderRowGstTax() {
		return visaOrderRowGstTax;
	}
	public void setVisaOrderRowGstTax(VisaOrderRowGstTax visaOrderRowGstTax) {
		this.visaOrderRowGstTax = visaOrderRowGstTax;
	}
	public Long getCompanyEntityId() {
		return companyEntityId;
	}
	public void setCompanyEntityId(Long companyEntityId) {
		this.companyEntityId = companyEntityId;
	}
	public BigDecimal getTotalGstTax() {
		return TotalGstTax;
	}
	public void setTotalGstTax(BigDecimal totalGstTax) {
		TotalGstTax = totalGstTax;
	}
	public String getVisaBookingDate() {
		return visaBookingDate;
	}
	public void setVisaBookingDate(String visaBookingDate) {
		this.visaBookingDate = visaBookingDate;
	}
	public Date getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}
	
}
