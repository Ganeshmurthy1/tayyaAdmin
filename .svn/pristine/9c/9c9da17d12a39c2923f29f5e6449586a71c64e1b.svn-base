package com.admin.api.provider.model;

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
import com.lintas.admin.common.model.Timestampable;

@Entity
@Table(name = "api_provider_payment_transaction_APG")
public class ApiProviderPaymentTransaction extends Timestampable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Transient
	private String convertDate;
	@Transient
	private BigDecimal balance;
	@Transient
	private boolean isApiproviderPaidFullAmount;
	

	@Column(name = "amount", columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal amount;

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
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "apiProviderPaymentTransaction")
	private List<ApiProviderPaymentTransactionDetail> apiProviderPaymentTransactionDetails;

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="payment_card_detail_id",referencedColumnName="id")
	private PaymentCardDetailsConfig paymentCardDetailsConfig;
	
	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "company_id")
	private int companyId;
	
	public Boolean getIsPaymentSuccess()
	{
		return isPaymentSuccess;
	}

	public PaymentCardDetailsConfig getPaymentCardDetailsConfig() {
		return paymentCardDetailsConfig;
	}

	public void setPaymentCardDetailsConfig(PaymentCardDetailsConfig paymentCardDetailsConfig) {
		this.paymentCardDetailsConfig = paymentCardDetailsConfig;
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

	

	public List<ApiProviderPaymentTransactionDetail> getApiPaymentTransactionDetails() {
		return apiProviderPaymentTransactionDetails;
	}

	public void setApiPaymentTransactionDetails(
			List<ApiProviderPaymentTransactionDetail> apiProviderPaymentTransactionDetails) {
		this.apiProviderPaymentTransactionDetails = apiProviderPaymentTransactionDetails;
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

	
	public boolean isApiproviderPaidFullAmount() {
		return isApiproviderPaidFullAmount;
	}

	public void setApiproviderPaidFullAmount(boolean isApiproviderPaidFullAmount) {
		this.isApiproviderPaidFullAmount = isApiproviderPaidFullAmount;
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

	public List<ApiProviderPaymentTransactionDetail> getApiProviderPaymentTransactionDetails() {
		return apiProviderPaymentTransactionDetails;
	}

	public void setApiProviderPaymentTransactionDetails(
			List<ApiProviderPaymentTransactionDetail> apiProviderPaymentTransactionDetails) {
		this.apiProviderPaymentTransactionDetails = apiProviderPaymentTransactionDetails;
	}

}
