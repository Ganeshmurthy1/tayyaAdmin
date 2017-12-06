package com.admin.payment.card.details;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Manish kumar
 *
 */
@Entity
@Table(name="paymentcarddetails")
public class PaymentCardDetailsConfig implements Serializable{ 
	
	private static final long serialVersionUID = 1L;
	public PaymentCardDetailsConfig(Long id) {
		super();
		this.id = id;
	}
	public PaymentCardDetailsConfig() {
		super();
	}
	@Id
	@GeneratedValue
	private Long id;
	
	@Transient
	private String cardExpireMonth;
	@Transient
	private String cardExpireYear;
	@Transient
	private String transPaymentType;	

	@Column(name="name")
	private String userName;
	@Column(name="bankname")
	private String bankName;
	@Column(name="cardtype")
	private String cardType;
	@Column(name="cardnumber")
	private int cardNumber;
	@Column(name="expirydate")
	private String expiryDate;
	@Column(name="contact")
	private String contactNumber;
	@Column(name="mail")
	private String mailId;

	/**
	 * if paymentType=true  means supplier
	 * 
	 * if paymentType=false  means client
	 * 
	 *  */	

	@Column(name="ispaymenttype")
	private boolean paymentType;


	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public int getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public boolean isPaymentType() {
		return paymentType;
	}
	public void setPaymentType(boolean paymentType) {
		this.paymentType = paymentType;
	}
	public String getTransPaymentType() {
		if(isPaymentType()){
			transPaymentType="Supplier";	
		}
		else{
			transPaymentType="Client";	
		}
		return transPaymentType;
	}
	public void setTransPaymentType(String transPaymentType) {
		this.transPaymentType = transPaymentType;
	}


	public String getCardExpireMonth() {
		return cardExpireMonth;
	}
	public void setCardExpireMonth(String cardExpireMonth) {
		this.cardExpireMonth = cardExpireMonth;
	}
	public String getCardExpireYear() {
		return cardExpireYear;
	}
	public void setCardExpireYear(String cardExpireYear) {
		this.cardExpireYear = cardExpireYear;
	}
}
