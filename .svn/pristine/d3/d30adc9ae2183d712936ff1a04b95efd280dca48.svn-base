package com.lintas.admin.common.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.lintas.admin.hotel.model.HotelOrderRow;
import com.lintas.utility.DateConversion;
@Entity
@Table(name="hotel_order_row_cancellation")
public class HotelOrderRowCancellation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Transient
	@Column(name="createdDate")
	private String createdDate;
	@Transient
	@Column(name="updatedDate")
	private String updatedDate;
	
	
	@Id
	@GeneratedValue
	private Long id;
	@Column(name="order_id",columnDefinition="VARCHAR(30) default '0'")
	private String orderId;
	@Column(name="status_code",columnDefinition="VARCHAR(2) default '0'")
	private String statusCode;
	@Column(name="status_message",columnDefinition="VARCHAR(255) default '0'")
	private String statusMessage;
	@Column(name="confirmation_number",columnDefinition="VARCHAR(30) default '0'")
	private String confirmationNumber;
	@Column(name="payment_type",columnDefinition="VARCHAR(10) default '0'")
	private String paymentType;
	@Column(name="charge_type",columnDefinition="VARCHAR(15) default '0'")
	private String chargeType;
	@Column(name="api_status_code",columnDefinition="VARCHAR(2) default '0'")
	private String APIStatusCode;
	@Column(name="api_status_message",columnDefinition="VARCHAR(255) default '0'")
	private String APIStatusMessage;
	@Column(name="api_confirmation_number",columnDefinition="VARCHAR(30) default '0'")
	private String APIConfirmationNumber;
	@Column(name="api_reference",columnDefinition="VARCHAR(30) default '0'")
	private String APIReference;
	@Column(name="api_payment_type",columnDefinition="VARCHAR(10) default '0'")
	private String APIPaymentType;
	@Column(name="api_charge_type",columnDefinition="VARCHAR(15) default '0'")
	private String APIChargeType;
	@Column(name="api_currency",columnDefinition="VARCHAR(15) default '0'")
	private String APICurrency;
	@Column(name = "api_charge_amount", columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal APIChargeAmount;
	@Column(name = "api_refund_amount", columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal APIRefundAmount;
	@Column(name = "api_booking_amount", columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal APIBookingAmount;
	@Column(name="credreditNote_no",columnDefinition="VARCHAR(30) default '0'")
	private String CreditNoteNo;
	@Column(name="credreditNote_created_on",columnDefinition="VARCHAR(255) default '0'")
	private String CreditNoteCreatedOn;
	@Column(name="ordered_At")
	private Timestamp  createdAt;
	@Column(name="updated_At")
	private Timestamp updatedAt;
	@OneToOne(cascade = CascadeType.ALL, targetEntity = HotelOrderRow.class)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "hotelOrderRow_id", referencedColumnName = "id")
	private HotelOrderRow hotelOrderRow ;
	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getConfirmationNumber() {
		return confirmationNumber;
	}

	public void setConfirmationNumber(String confirmationNumber) {
		this.confirmationNumber = confirmationNumber;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getChargeType() {
		return chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}

	public String getAPIStatusCode() {
		return APIStatusCode;
	}

	public void setAPIStatusCode(String aPIStatusCode) {
		APIStatusCode = aPIStatusCode;
	}

	public String getAPIStatusMessage() {
		return APIStatusMessage;
	}

	public void setAPIStatusMessage(String aPIStatusMessage) {
		APIStatusMessage = aPIStatusMessage;
	}

	public String getAPIConfirmationNumber() {
		return APIConfirmationNumber;
	}

	public void setAPIConfirmationNumber(String aPIConfirmationNumber) {
		APIConfirmationNumber = aPIConfirmationNumber;
	}

	public String getAPIReference() {
		return APIReference;
	}

	public void setAPIReference(String aPIReference) {
		APIReference = aPIReference;
	}

	public String getAPIPaymentType() {
		return APIPaymentType;
	}

	public void setAPIPaymentType(String aPIPaymentType) {
		APIPaymentType = aPIPaymentType;
	}

	public String getAPIChargeType() {
		return APIChargeType;
	}

	public void setAPIChargeType(String aPIChargeType) {
		APIChargeType = aPIChargeType;
	}

	public String getAPICurrency() {
		return APICurrency;
	}

	public void setAPICurrency(String aPICurrency) {
		APICurrency = aPICurrency;
	}

	public BigDecimal getAPIChargeAmount() {
		return APIChargeAmount;
	}

	public void setAPIChargeAmount(BigDecimal aPIChargeAmount) {
		APIChargeAmount = aPIChargeAmount;
	}

	public BigDecimal getAPIRefundAmount() {
		return APIRefundAmount;
	}

	public void setAPIRefundAmount(BigDecimal aPIRefundAmount) {
		APIRefundAmount = aPIRefundAmount;
	}


	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public String getCreatedDate() {
		createdDate=DateConversion.convertTimestampToStringwithoutseconds(createdAt);
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedDate() {
		updatedDate=DateConversion.convertTimestampToStringwithoutseconds(createdAt);
		return updatedDate;
	 }

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public HotelOrderRow getHotelOrderRow() {
		return hotelOrderRow;
	}

	public void setHotelOrderRow(HotelOrderRow hotelOrderRow) {
		this.hotelOrderRow = hotelOrderRow;
	}

	public BigDecimal getAPIBookingAmount() {
		return APIBookingAmount;
	}

	public void setAPIBookingAmount(BigDecimal aPIBookingAmount) {
		APIBookingAmount = aPIBookingAmount;
	}

	public String getCreditNoteNo() {
		return CreditNoteNo;
	}

	public void setCreditNoteNo(String creditNoteNo) {
		CreditNoteNo = creditNoteNo;
	}

	public String getCreditNoteCreatedOn() {
		return CreditNoteCreatedOn;
	}

	public void setCreditNoteCreatedOn(String creditNoteCreatedOn) {
		CreditNoteCreatedOn = creditNoteCreatedOn;
	}




}
