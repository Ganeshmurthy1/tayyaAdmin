package com.lintas.admin.common.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.admin.payment.card.details.PaymentCardDetailsConfig;


@Entity
@Table(name = "payment_transaction_detail")
public class PaymentTransactionDetail extends Timestampable
{
	private static final long serialVersionUID = 1L;

	@Transient
	private String createdDate;
	@Transient
	private BigDecimal balance;
	@Transient
	private Long clientCardHolderId;
	@Column(name = "amount", columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal amount;

	@Column(name = "is_payment_success")
	private Boolean isPaymentSuccess;

	@Column(name = "currency")
	private String	currency;

	@Column(name = "response_message")
	private	String	responseMessage;

	@Column(name = "transaction_id")
	private	String	transactionId;

	// success, failed, pending,refund
	@Column(name="payment_status")
	private String paymentStatus;

	//partial, complete
	@Column(name="payment_collection_type")
	private String paymentCollectionType;

	//card, cash, credit,cheque
	@Column(name="payment_method")
	private String paymentMethod;

	//some info
	@Column(name="payment_information")
	private String paymentInformation;

	//C
	@Column(name="paymentPaidBy")
	private String paymentPaidBy;


	@Column(name = "api_transaction_id")
	private String apiTransactionId; 

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "client_payment_card_info_id",referencedColumnName="id")
	private PaymentCardDetailsConfig clientPaymentCardDetailsConfig;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "payment_transaction_id", referencedColumnName = "id")
	private PaymentTransaction paymentTransaction;

	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "company_id")
	private int companyId;

	public PaymentCardDetailsConfig getClientPaymentCardDetailsConfig() {
		return clientPaymentCardDetailsConfig;
	}

	public void setClientPaymentCardDetailsConfig(PaymentCardDetailsConfig clientPaymentCardDetailsConfig) {
		this.clientPaymentCardDetailsConfig = clientPaymentCardDetailsConfig;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getPaymentCollectionType() {
		return paymentCollectionType;
	}

	public void setPaymentCollectionType(String paymentCollectionType) {
		this.paymentCollectionType = paymentCollectionType;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getPaymentInformation() {
		return paymentInformation;
	}

	public void setPaymentInformation(String paymentInformation) {
		this.paymentInformation = paymentInformation;
	}

	public String getPaymentPaidBy() {
		return paymentPaidBy;
	}

	public void setPaymentPaidBy(String paymentPaidBy) {
		this.paymentPaidBy = paymentPaidBy;
	}

	public String getApiTransactionId() {
		return apiTransactionId;
	}

	public void setApiTransactionId(String apiTransactionId) {
		this.apiTransactionId = apiTransactionId;
	}

	 

	public PaymentTransaction getPaymentTransaction() {
		return paymentTransaction;
	}

	public void setPaymentTransaction(PaymentTransaction paymentTransaction) {
		this.paymentTransaction = paymentTransaction;
	}

	public Boolean getIsPaymentSuccess()
	{
		return isPaymentSuccess;
	}

	public void setIsPaymentSuccess(Boolean isPaymentSuccess)
	{
		this.isPaymentSuccess = isPaymentSuccess;
	}
	public String getCurrency()
	{
		return currency;
	}

	public void setCurrency(String currency)
	{
		this.currency = currency;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	 

	public Long getClientCardHolderId() {
		return clientCardHolderId;
	}

	public void setClientCardHolderId(Long clientCardHolderId) {
		this.clientCardHolderId = clientCardHolderId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}


}
