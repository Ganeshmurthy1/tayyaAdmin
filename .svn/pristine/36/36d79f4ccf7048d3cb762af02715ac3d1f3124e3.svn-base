package com.lintas.admin.common.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.admin.payment.card.details.PaymentCardDetailsConfig;

@Entity
@Table(name = "payment_transaction_APG")
public class PaymentTransaction extends Timestampable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Transient
	private String convertDate;
	@Transient
	private String paidByClient;
	@Transient
	private String paidBySupplier;
	@Transient
	private String clientPaymentInfo;
	@Transient
	private String supplierPaymentInfo;
	
	@Transient
	private BigDecimal balance;
	@Transient
	private boolean isCustomerPaidFullAmount;
	@Transient
	private Long supplierCardHolderId;
	
	@Transient
	private Long clientCardHolderId;
	
	@Transient
	private BigDecimal apiProviderAmount ;
	@Transient
	private BigDecimal clientAmount ;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="payment_card_detail_id",referencedColumnName="id")
	private PaymentCardDetailsConfig paymentCardDetailsConfig;
	 
	@Column(name = "amount", columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal amount;
	
	@Column(name = "client_name", columnDefinition="varchar(255) default '0'")
	private String  clientName;
	 


	@Column(name = "is_payment_success")
	private Boolean isPaymentSuccess;

	@Column(name = "currency")
	private String	currency;

	@Column(name = "response_message")
	private	String	response_message;

	@Column(name = "transaction_id")
	private	String	transactionId;

	@Column(name = "refno")
	private	String	refno;

	@Column(name = "authorization_code")
	private	String	authorizationCode;

	@Column(name = "response_code")
	private	String	responseCode;

	@Column(name = "expy")
	private	String	expy;

	@Column(name="payment_status")
	private String payment_status;

	@Column(name="payment_method")
	private String payment_method;

	@Column(name="payment_information")
	private String payment_information;

	@Column(name="payment_system")
	private String payment_system;
	@Column(name = "api_transaction_id")
	private String api_transaction_id; 
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "paymentTransaction")
	private List<PaymentTransactionDetail> paymentTransactionDetails;


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

	public String getResponse_message()
	{
		return response_message;
	}

	public void setResponse_message(String response_message)
	{
		this.response_message = response_message;
	}

	public String getRefno()
	{
		return refno;
	}

	public void setRefno(String refno)
	{
		this.refno = refno;
	}


	public String getAuthorizationCode()
	{
		return authorizationCode;
	}

	public void setAuthorizationCode(String authorizationCode)
	{
		this.authorizationCode = authorizationCode;
	}
	public String getResponseCode()
	{
		return responseCode;
	}

	public void setResponseCode(String responseCode)
	{
		this.responseCode = responseCode;
	}

	public String getExpy()
	{
		return expy;
	}

	public void setExpy(String expy)
	{
		this.expy = expy;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getPayment_status() {
		return payment_status;
	}

	public void setPayment_status(String payment_status) {
		this.payment_status = payment_status;
	}

	public String getPayment_method() {
		return payment_method;
	}

	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}

	public String getPayment_information() {
		return payment_information;
	}

	public void setPayment_information(String payment_information) {
		this.payment_information = payment_information;
	}

	public String getPayment_system() {
		return payment_system;
	}

	public void setPayment_system(String payment_system) {
		this.payment_system = payment_system;
	}


	public String getApi_transaction_id() {
		return api_transaction_id;
	}

	public void setApi_transaction_id(String api_transaction_id) {
		this.api_transaction_id = api_transaction_id;
	}

	public String getConvertDate() {
		return convertDate;
	}

	public List<PaymentTransactionDetail> getPaymentTransactionDetails() {
		return paymentTransactionDetails;
	}

	public void setPaymentTransactionDetails(
			List<PaymentTransactionDetail> paymentTransactionDetails) {
		this.paymentTransactionDetails = paymentTransactionDetails;
	}

	public void setConvertDate(String convertDate) {
		this.convertDate = convertDate;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public boolean isCustomerPaidFullAmount() {
		return isCustomerPaidFullAmount;
	}

	public void setCustomerPaidFullAmount(boolean isCustomerPaidFullAmount) {
		this.isCustomerPaidFullAmount = isCustomerPaidFullAmount;
	} 
	public PaymentCardDetailsConfig getPaymentCardDetailsConfig() {
		return paymentCardDetailsConfig;
	}

	public void setPaymentCardDetailsConfig(PaymentCardDetailsConfig paymentCardDetailsConfig) {
		this.paymentCardDetailsConfig = paymentCardDetailsConfig;
	}
	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public Long getClientCardHolderId() {
		return clientCardHolderId;
	}

	public void setClientCardHolderId(Long clientCardHolderId) {
		this.clientCardHolderId = clientCardHolderId;
	}

	public Long getSupplierCardHolderId() {
		return supplierCardHolderId;
	}

	public void setSupplierCardHolderId(Long supplierCardHolderId) {
		this.supplierCardHolderId = supplierCardHolderId;
	}

	public BigDecimal getApiProviderAmount() {
		return apiProviderAmount;
	}

	public void setApiProviderAmount(BigDecimal apiProviderAmount) {
		this.apiProviderAmount = apiProviderAmount;
	}

	public BigDecimal getClientAmount() {
		return clientAmount;
	}

	public void setClientAmount(BigDecimal clientAmount) {
		this.clientAmount = clientAmount;
	}
	public String getPaidByClient() {
		return paidByClient;
	}

	public void setPaidByClient(String paidByClient) {
		this.paidByClient = paidByClient;
	}

	public String getPaidBySupplier() {
		return paidBySupplier;
	}

	public void setPaidBySupplier(String paidBySupplier) {
		this.paidBySupplier = paidBySupplier;
	}


	public String getClientPaymentInfo() {
		return clientPaymentInfo;
	}

	public void setClientPaymentInfo(String clientPaymentInfo) {
		this.clientPaymentInfo = clientPaymentInfo;
	}

	public String getSupplierPaymentInfo() {
		return supplierPaymentInfo;
	}

	public void setSupplierPaymentInfo(String supplierPaymentInfo) {
		this.supplierPaymentInfo = supplierPaymentInfo;
	}

}
