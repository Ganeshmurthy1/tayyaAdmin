/**
 * 
 */
package com.admin.miscellaneous.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Manish Samrat
 *
 */
@Entity
@Table(name="miscellaneous_order_row_cancellation")
public class MiscellaneousOrderRowCancellation implements Serializable{

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
	@Column(name="ordered_At")
	private Timestamp  createdAt;
	@Column(name="updated_At")
	private Timestamp updatedAt;
	public String getCreatedDate() {
		return createdDate;
	}
	public String getUpdatedDate() {
		return updatedDate;
	}
	public Long getId() {
		return id;
	}
	public String getOrderId() {
		return orderId;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public String getStatusMessage() {
		return statusMessage;
	}
	public String getConfirmationNumber() {
		return confirmationNumber;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public String getChargeType() {
		return chargeType;
	}
	public String getAPIStatusCode() {
		return APIStatusCode;
	}
	public String getAPIStatusMessage() {
		return APIStatusMessage;
	}
	public String getAPIConfirmationNumber() {
		return APIConfirmationNumber;
	}
	public String getAPIReference() {
		return APIReference;
	}
	public String getAPIPaymentType() {
		return APIPaymentType;
	}
	public String getAPIChargeType() {
		return APIChargeType;
	}
	public String getAPICurrency() {
		return APICurrency;
	}
	public BigDecimal getAPIChargeAmount() {
		return APIChargeAmount;
	}
	public BigDecimal getAPIRefundAmount() {
		return APIRefundAmount;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	public void setConfirmationNumber(String confirmationNumber) {
		this.confirmationNumber = confirmationNumber;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}
	public void setAPIStatusCode(String aPIStatusCode) {
		APIStatusCode = aPIStatusCode;
	}
	public void setAPIStatusMessage(String aPIStatusMessage) {
		APIStatusMessage = aPIStatusMessage;
	}
	public void setAPIConfirmationNumber(String aPIConfirmationNumber) {
		APIConfirmationNumber = aPIConfirmationNumber;
	}
	public void setAPIReference(String aPIReference) {
		APIReference = aPIReference;
	}
	public void setAPIPaymentType(String aPIPaymentType) {
		APIPaymentType = aPIPaymentType;
	}
	public void setAPIChargeType(String aPIChargeType) {
		APIChargeType = aPIChargeType;
	}
	public void setAPICurrency(String aPICurrency) {
		APICurrency = aPICurrency;
	}
	public void setAPIChargeAmount(BigDecimal aPIChargeAmount) {
		APIChargeAmount = aPIChargeAmount;
	}
	public void setAPIRefundAmount(BigDecimal aPIRefundAmount) {
		APIRefundAmount = aPIRefundAmount;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
}
