package com.lintas.admin.common.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "payment_card_info")
public class PaymentCardInfo extends Timestampable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name="type_name")
	private String typeName;
	@Column(name="name")
	private String  name="";
	@Column(name="first_name")
	private String  firstName="";
	@Column(name="middle_name")
	private String  middleName="";
	@Column(name="last_name")
	private String  lastName="";
	@Column(name="card_number")
	private String  cardNumber;
	@Column(name="cvv")
	private String cvv;
	@Column(name="type_code")
	private String typeCode;
	@Column(name="exp_month")
	private String expiryMonth;
	@Column(name="exp_year")
	private String expiryYear;


	@OneToOne()
	@JoinColumn(name = "payment_transaction_detail_id")
	private PaymentTransactionDetail paymentTransactionDetail;
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getExpiryMonth() {
		return expiryMonth;
	}

	public void setExpiryMonth(String expiryMonth) {
		this.expiryMonth = expiryMonth;
	}

	public String getExpiryYear() {
		return expiryYear;
	}

	public void setExpiryYear(String expiryYear) {
		this.expiryYear = expiryYear;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PaymentTransactionDetail getPaymentTransactionDetail() {
		return paymentTransactionDetail;
	}

	public void setPaymentTransactionDetail(
			PaymentTransactionDetail paymentTransactionDetail) {
		this.paymentTransactionDetail = paymentTransactionDetail;
	}

}