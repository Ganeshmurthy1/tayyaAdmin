package com.lintas.admin.common.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.admin.api.provider.model.ApiProviderPaymentTransaction;
@Entity
@Table(name="flight_payment_tx_detail_history")
public class FlightPaymentTxDetailHistory implements Serializable{

	/**
	 * 
	 */
	@Id
	@GeneratedValue
	private Long id;
	private static final long serialVersionUID = 1L;
	@Column(name = "created_at",insertable = false,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL")
	private Timestamp createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="from_date")
	private Date fromDate ;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="to_date")
	private Date toDate;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="due_date")
	private Date dueDate;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="paid_date")
	private Date paidDate;
	
	@Column(name="comments",columnDefinition="LONGTEXT")
	private String  comments;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "payment_tx_id", referencedColumnName = "id")
	private PaymentTransaction  paymentTransaction;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "api_provider_payment_tx_id", referencedColumnName = "id")
	private ApiProviderPaymentTransaction  apiProviderPaymentTransaction;
	  
	public ApiProviderPaymentTransaction getApiProviderPaymentTransaction() {
		return apiProviderPaymentTransaction;
	}

	public void setApiProviderPaymentTransaction(ApiProviderPaymentTransaction apiProviderPaymentTransaction) {
		this.apiProviderPaymentTransaction = apiProviderPaymentTransaction;
	}

	public PaymentTransaction getPaymentTransaction() {
		return paymentTransaction;
	}

	public void setPaymentTransaction(PaymentTransaction paymentTransaction) {
		this.paymentTransaction = paymentTransaction;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
	 
}
