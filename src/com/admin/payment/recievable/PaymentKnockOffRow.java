package com.admin.payment.recievable;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="payment_knock_off_row")
public class PaymentKnockOffRow  implements Serializable{
	/**
	 * @author  Shaik Raham
	 */
	private static final long serialVersionUID = 1L;
	@Transient
	private BigDecimal  balanceAmount;//invoiceAmount-paidAmount=balanceAmount
	@Transient
	private BigDecimal  knockedOffAmount;//paidAmount
	@Transient
	private BigDecimal  amount;//pay amount
	@Id
	@GeneratedValue
	private Long id;
	@Column(name="booking_ref")
	private String  bookingRef;
	@Column(name="booking_date")
	private String  bookingDate;
	@Column(name="bill_date")
	private String  billDate;//invoice Date
	@Column(name="bill_no")
	private String  billNo;//invoice number
	@Column(name="bill_amount")
	private BigDecimal  billAmount;//invoice amount
	@Column(name="booking_type")
	private String  bookingType;
	@Column(name="gds_lcc")
	private String  GDSorLCC; 
	@Column(name="company_id")
	private Integer companyId;
	@Column(name="booking_ref")
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "paymentKnockOffRow")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<PaymentKnockOffRowTx> paymentKnockOffRowTxs;
	public Long getId() {
		return id;
	}
	public List<PaymentKnockOffRowTx> getPaymentKnockOffRowTxs() {
		return paymentKnockOffRowTxs;
	}
	public void setPaymentKnockOffRowTxs(List<PaymentKnockOffRowTx> paymentKnockOffRowTxs) {
		this.paymentKnockOffRowTxs = paymentKnockOffRowTxs;
	}
	public String getBookingRef() {
		return bookingRef;
	}
	public String getBookingDate() {
		return bookingDate;
	}
	public String getBillDate() {
		return billDate;
	}
	public String getBillNo() {
		return billNo;
	}
	public BigDecimal getBillAmount() {
		return billAmount;
	}
	public BigDecimal getKnockedOffAmount() {
		return knockedOffAmount;
	}
	public BigDecimal getBalanceAmount() {
		return balanceAmount;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	 
	public String getBookingType() {
		return bookingType;
	}
	public String getGDSorLCC() {
		return GDSorLCC;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setBookingRef(String bookingRef) {
		this.bookingRef = bookingRef;
	}
	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}
	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public void setBillAmount(BigDecimal billAmount) {
		this.billAmount = billAmount;
	}
	public void setKnockedOffAmount(BigDecimal knockedOffAmount) {
		this.knockedOffAmount = knockedOffAmount;
	}
	public void setBalanceAmount(BigDecimal balanceAmount) {
		this.balanceAmount = balanceAmount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	 
	public void setBookingType(String bookingType) {
		this.bookingType = bookingType;
	}
	public void setGDSorLCC(String gDSorLCC) {
		GDSorLCC = gDSorLCC;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

}
