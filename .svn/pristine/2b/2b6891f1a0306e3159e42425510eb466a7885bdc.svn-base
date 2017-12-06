/**
 * 
 */
package com.admin.miscellaneous.model;

import java.io.Serializable;
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
import com.tayyarah.admin.orderrow.rm.structure.MiscellaneousOrderRowRmConfigStruct;
import com.tayyarah.miscellaneous.model.MiscellaneousOrderCustomer;

/**
 * @author Basha
 *
 */

@Entity
@Table(name = "miscellaneous_order_row")
public class MiscellaneousOrderRow extends Timestampable implements Serializable{

	private static final long serialVersionUID = 1L;

	//	@Transient
	//	private String bookingDate;
	@Transient
	private Company company;

	@Transient
	private String  miscBookingDate;


	@Column(name = "company_id" ,columnDefinition="INT(11) default 0")
	private int companyId;

	@Column(name = "user_id",columnDefinition="INT(11) default 0")
	private int userId;

	@Column(name = "confirmation_number")
	private String confirmationNumber;

	@Column(name = "supplier_Name")
	private String supplierName;

	@Column(name = "status_id")
	private int statusId;

	@Column(name="created_by")
	private String createdBy;

	@Column(name="updated_by")
	private String updatedBy;

	@Column(name="status_action")
	private String statusAction;

	@Column(name="payment_status")
	private String paymentStatus;

	@Column(name="booking_mode")
	private String bookingMode;

	@Column(name = "booking_date")
	@Temporal(TemporalType.DATE)
	private Date bookingDate;

	@Column(name = "invoice_no")
	private String invoiceNo;

	@Column(name = "order_id")
	private String orderId;

	@Column(name = "booking_currency")
	private String bookingCurrency;

	@Column(name = "base_to_booking_exchange_rate",columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal baseToBookingExchangeRate;

	@Column(name = "api_to_base_exchange_rate" ,columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal apiToBaseExchangeRate;

	@Column(name = "total_amount", columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal totalAmount;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id", referencedColumnName = "id")
	private OrderCustomer orderCustomer;

	@Column(name = "is_creditnote_issues",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean creditNoteIssued;

	@Column(name = "is_order_updated",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean orderUpdated;

	@Column(name = "is_order_requested",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean orderRequested;
	@Column(name = "total_gst_tax",columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal totalGstTax;

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
	@Column(name = "remarks")
	private String remarks;
	@Column(name = "mark_up", columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal markUpamount;	
	@Column(name = "misc_details1")
	private String details1;
	@Column(name = "misc_details2")
	private String details2;

	@ManyToOne(cascade = CascadeType.ALL, targetEntity = MiscellaneousOrderRowGstTax.class)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "miscellaneousOrderRow_gstTax_id", referencedColumnName = "id")
	private MiscellaneousOrderRowGstTax miscellaneousOrderRowGstTax ;
	@Column(name = "company_entity_id")
	private Long companyEntityId;
	@Column(name="recieved_amount",columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal recievedAmount;

	@Column(name="tot_invoice_amount",columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal totInvoiceAmount;

	@Column(name="is_knock_off",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean knockOff;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "miscellaneousOrderRow",targetEntity = MiscellaneousOrderCustomer.class)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<MiscellaneousOrderCustomer> miscellaneousOrderCustomerList;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "dynamic_rm_config_id", referencedColumnName = "id")
	private  MiscellaneousOrderRowRmConfigStruct miscellaneousOrderRowRmConfigStruct;
	public MiscellaneousOrderRowRmConfigStruct getMiscellaneousOrderRowRmConfigStruct() {
		return miscellaneousOrderRowRmConfigStruct;
	}

	public void setMiscellaneousOrderRowRmConfigStruct(
			MiscellaneousOrderRowRmConfigStruct miscellaneousOrderRowRmConfigStruct) {
		this.miscellaneousOrderRowRmConfigStruct = miscellaneousOrderRowRmConfigStruct;
	}

	public BigDecimal getRecievedAmount() {
		return recievedAmount;
	}

	public List<MiscellaneousOrderCustomer> getMiscellaneousOrderCustomerList() {
		return miscellaneousOrderCustomerList;
	}

	public void setMiscellaneousOrderCustomerList(List<MiscellaneousOrderCustomer> miscellaneousOrderCustomerList) {
		this.miscellaneousOrderCustomerList = miscellaneousOrderCustomerList;
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

	public Long getCompanyEntityId() {
		return companyEntityId;
	}

	public void setCompanyEntityId(Long companyEntityId) {
		this.companyEntityId = companyEntityId;
	}


	public BigDecimal getSupplierPrice() {
		return supplierPrice;
	}

	public BigDecimal getBasePrice() {
		return basePrice;
	}

	public BigDecimal getOtherTaxes() {
		return otherTaxes;
	}

	public BigDecimal getManagementFee() {
		return managementFee;
	}

	public BigDecimal getConvenienceFee() {
		return convenienceFee;
	}

	public String getRemarks() {
		return remarks;
	}

	public BigDecimal getMarkUpamount() {
		return markUpamount;
	}

	public void setSupplierPrice(BigDecimal supplierPrice) {
		this.supplierPrice = supplierPrice;
	}

	public void setBasePrice(BigDecimal basePrice) {
		this.basePrice = basePrice;
	}

	public void setOtherTaxes(BigDecimal otherTaxes) {
		this.otherTaxes = otherTaxes;
	}

	public void setManagementFee(BigDecimal managementFee) {
		this.managementFee = managementFee;
	}

	public void setConvenienceFee(BigDecimal convenienceFee) {
		this.convenienceFee = convenienceFee;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setMarkUpamount(BigDecimal markUpamount) {
		this.markUpamount = markUpamount;
	}

	public boolean isOrderRequested() {
		return orderRequested;
	}

	public void setOrderRequested(boolean orderRequested) {
		this.orderRequested = orderRequested;
	}

	public boolean isOrderUpdated() {
		return orderUpdated;
	}

	public void setOrderUpdated(boolean orderUpdated) {
		this.orderUpdated = orderUpdated;
	}

	public boolean isCreditNoteIssued() {
		return creditNoteIssued;
	}

	public void setCreditNoteIssued(boolean creditNoteIssued) {
		this.creditNoteIssued = creditNoteIssued;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getBookingCurrency() {
		return bookingCurrency;
	}

	public BigDecimal getBaseToBookingExchangeRate() {
		return baseToBookingExchangeRate;
	}

	public BigDecimal getApiToBaseExchangeRate() {
		return apiToBaseExchangeRate;
	}

	public void setBookingCurrency(String bookingCurrency) {
		this.bookingCurrency = bookingCurrency;
	}

	public void setBaseToBookingExchangeRate(BigDecimal baseToBookingExchangeRate) {
		this.baseToBookingExchangeRate = baseToBookingExchangeRate;
	}

	public void setApiToBaseExchangeRate(BigDecimal apiToBaseExchangeRate) {
		this.apiToBaseExchangeRate = apiToBaseExchangeRate;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public OrderCustomer getOrderCustomer() {
		return orderCustomer;
	}

	public void setOrderCustomer(OrderCustomer orderCustomer) {
		this.orderCustomer = orderCustomer;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public String getStatusAction() {
		return statusAction;
	}

	public String getBookingMode() {
		return bookingMode;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public void setStatusAction(String statusAction) {
		this.statusAction = statusAction;
	}

	public void setBookingMode(String bookingMode) {
		this.bookingMode = bookingMode;
	}

	public int getCompanyId() {
		return companyId;
	}

	public int getUserId() {
		return userId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public Company getCompany() {
		return company;
	}

	public String getConfirmationNumber() {
		return confirmationNumber;
	}



	public String getSupplierName() {
		return supplierName;
	}



	public void setCompany(Company company) {
		this.company = company;
	}

	public void setConfirmationNumber(String confirmationNumber) {
		this.confirmationNumber = confirmationNumber;
	}


	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public BigDecimal getTotalGstTax() {
		return totalGstTax;
	}

	public void setTotalGstTax(BigDecimal totalGstTax) {
		this.totalGstTax = totalGstTax;
	}

	public MiscellaneousOrderRowGstTax getMiscellaneousOrderRowGstTax() {
		return miscellaneousOrderRowGstTax;
	}

	public void setMiscellaneousOrderRowGstTax(MiscellaneousOrderRowGstTax miscellaneousOrderRowGstTax) {
		this.miscellaneousOrderRowGstTax = miscellaneousOrderRowGstTax;
	}

	public String getMiscBookingDate() {
		return miscBookingDate;
	}

	public void setMiscBookingDate(String miscBookingDate) {
		this.miscBookingDate = miscBookingDate;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}
	public String getDetails1() {
		return details1;
	}

	public String getDetails2() {
		return details2;
	}

	public void setDetails1(String details1) {
		this.details1 = details1;
	}

	public void setDetails2(String details2) {
		this.details2 = details2;
	}

}
