package com.lintas.admin.common.model;



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

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.sun.jmx.snmp.Timestamp;

@Entity
@Table(name="Hotel_Order_Row_Cancellation_API_Response")
public class HotelOrderRowCancellationAPIResponse implements Serializable{

	/**
	 * author:Basha
	 * created:01-06-2017
	 * for saving hotel cancellation api response coming from api provider
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue
	Long id;
	@Column(name = "created_at",insertable=false,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL")
	Timestamp createdAt;
	@Column(name = "updated_at",insertable=false,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL")
	Timestamp updatedAt;
	
	@Column(name = "change_request_id")
	private Long ChangeRequestId;
	
	@Column(name = "trace_id",columnDefinition="VARCHAR(255) default '0'")
	private String TraceId;
	
	@Column(name = "change_request_status")
	private Long ChangeRequestStatus;
	
	@Column(name = "response_status")
	private Long ResponseStatus;
	
	@Column(name = "error_code")
	private Integer ErrorCode ;
	
	@Column(name = "error_message",columnDefinition="VARCHAR(255) default '0'")
	private String ErrorMessage ;
	
	@Column(name = "b2b2b_status")
	private boolean B2B2BStatus;
	
	@Column(name = "api_cancellation_amount", columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal CancellationCharge;
	
	@Column(name = "api_refund_amount", columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal RefundedAmount;
	
	@Column(name = "api_total_price", columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal TotalPrice;
	
	
	@Column(name="credreditNote_no",columnDefinition="VARCHAR(30) default '0'")
	private String CreditNoteNo;
	
	@Column(name="credreditNote_created_on",columnDefinition="VARCHAR(255) default '0'")
	private String CreditNoteCreatedOn;
	
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = HotelOrderRowCancellation.class)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "hotelOrderRowCancellation_id", referencedColumnName = "id")
	private HotelOrderRowCancellation hotelOrderRowCancellation ;

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

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	

	public String getTraceId() {
		return TraceId;
	}

	public void setTraceId(String traceId) {
		TraceId = traceId;
	}

	
	

	public Integer getErrorCode() {
		return ErrorCode;
	}

	public void setErrorCode(Integer errorCode) {
		ErrorCode = errorCode;
	}

	public String getErrorMessage() {
		return ErrorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		ErrorMessage = errorMessage;
	}

	public boolean isB2B2BStatus() {
		return B2B2BStatus;
	}

	public void setB2B2BStatus(boolean b2b2bStatus) {
		B2B2BStatus = b2b2bStatus;
	}

	public BigDecimal getCancellationCharge() {
		return CancellationCharge;
	}

	public void setCancellationCharge(BigDecimal cancellationCharge) {
		CancellationCharge = cancellationCharge;
	}

	public BigDecimal getRefundedAmount() {
		return RefundedAmount;
	}

	public void setRefundedAmount(BigDecimal refundedAmount) {
		RefundedAmount = refundedAmount;
	}

	public BigDecimal getTotalPrice() {
		return TotalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		TotalPrice = totalPrice;
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

	public HotelOrderRowCancellation getHotelOrderRowCancellation() {
		return hotelOrderRowCancellation;
	}

	public void setHotelOrderRowCancellation(HotelOrderRowCancellation hotelOrderRowCancellation) {
		this.hotelOrderRowCancellation = hotelOrderRowCancellation;
	}

	public Long getChangeRequestId() {
		return ChangeRequestId;
	}

	public Long getResponseStatus() {
		return ResponseStatus;
	}

	public void setChangeRequestId(Long changeRequestId) {
		ChangeRequestId = changeRequestId;
	}

	public void setResponseStatus(Long responseStatus) {
		ResponseStatus = responseStatus;
	}

	public Long getChangeRequestStatus() {
		return ChangeRequestStatus;
	}

	public void setChangeRequestStatus(Long changeRequestStatus) {
		ChangeRequestStatus = changeRequestStatus;
	}
}
