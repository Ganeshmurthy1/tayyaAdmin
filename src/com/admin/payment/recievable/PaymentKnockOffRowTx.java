package com.admin.payment.recievable;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="payment_knock_off_row_tx")
public class PaymentKnockOffRowTx  implements Serializable{
	/**
	 * @author  Shaik Raham
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	private BigDecimal  amount;//partial amounts or full amounts
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "payment_knock_off_row_id", referencedColumnName = "id")
	private PaymentKnockOffRow paymentKnockOffRow;
	@Column(name="brv_crv")
	private String  BRVorCRV; 
	public Long getId() {
		return id;
	}
	public PaymentKnockOffRow getPaymentKnockOffRow() {
		return paymentKnockOffRow;
	}
	public void setPaymentKnockOffRow(PaymentKnockOffRow paymentKnockOffRow) {
		this.paymentKnockOffRow = paymentKnockOffRow;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getBRVorCRV() {
		return BRVorCRV;
	}
	public void setBRVorCRV(String bRVorCRV) {
		BRVorCRV = bRVorCRV;
	}

}
