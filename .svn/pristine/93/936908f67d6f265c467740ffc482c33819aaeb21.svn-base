package com.lintas.admin.flight.model;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

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

/**
 * author:Basha
 * created:24-05-2017
 * for saving flight cancellation api response coming from api provider
 * 
 */

@Entity
@Table(name="Flight_Order_Row_Cancellation_API_Response")
public class FlightOrderRowCancellationAPIResponse  implements Serializable  {
	
	
	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue
	Long id;
	@Column(name = "created_at",insertable=false,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL")
	Timestamp createdAt;
	private Integer ResponseStatus;
	private String TraceId;
	private Integer ChangeRequestId;
	private String TicketId;
	private BigDecimal RefundedAmount;
	private BigDecimal CancellationCharge;
	private BigDecimal ServiceTaxOnRAF;
	private Integer ChangeRequestStatus;
	private BigDecimal SwachhBharatCess;
	private String CreditNoteNo;
	private Double KrishiKalyanCess;
	private String CreditNoteCreatedOn;
	
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = FlightOrderRowCancellation.class)
	@JoinColumn(name = "FlightOrderRowCancellation_id", referencedColumnName = "id")
	@LazyCollection(LazyCollectionOption.FALSE)
	private FlightOrderRowCancellation flightOrderRowCancellation;
	
	public Integer getResponseStatus() {
		return ResponseStatus;
	}
	public void setResponseStatus(Integer responseStatus) {
		ResponseStatus = responseStatus;
	}
	public String getTraceId() {
		return TraceId;
	}
	public void setTraceId(String traceId) {
		TraceId = traceId;
	}
	public Integer getChangeRequestId() {
		return ChangeRequestId;
	}
	public void setChangeRequestId(Integer changeRequestId) {
		ChangeRequestId = changeRequestId;
	}
	public String getTicketId() {
		return TicketId;
	}
	public void setTicketId(String ticketId) {
		TicketId = ticketId;
	}
	public BigDecimal getRefundedAmount() {
		return RefundedAmount;
	}
	public void setRefundedAmount(BigDecimal refundedAmount) {
		RefundedAmount = refundedAmount;
	}
	public BigDecimal getCancellationCharge() {
		return CancellationCharge;
	}
	public void setCancellationCharge(BigDecimal cancellationCharge) {
		CancellationCharge = cancellationCharge;
	}
	public BigDecimal getServiceTaxOnRAF() {
		return ServiceTaxOnRAF;
	}
	public void setServiceTaxOnRAF(BigDecimal serviceTaxOnRAF) {
		ServiceTaxOnRAF = serviceTaxOnRAF;
	}
	public Integer getChangeRequestStatus() {
		return ChangeRequestStatus;
	}
	public void setChangeRequestStatus(Integer changeRequestStatus) {
		ChangeRequestStatus = changeRequestStatus;
	}
	public BigDecimal getSwachhBharatCess() {
		return SwachhBharatCess;
	}
	public void setSwachhBharatCess(BigDecimal swachhBharatCess) {
		SwachhBharatCess = swachhBharatCess;
	}
	public String getCreditNoteNo() {
		return CreditNoteNo;
	}
	public void setCreditNoteNo(String creditNoteNo) {
		CreditNoteNo = creditNoteNo;
	}
	public Double getKrishiKalyanCess() {
		return KrishiKalyanCess;
	}
	public void setKrishiKalyanCess(Double krishiKalyanCess) {
		KrishiKalyanCess = krishiKalyanCess;
	}
	public String getCreditNoteCreatedOn() {
		return CreditNoteCreatedOn;
	}
	public void setCreditNoteCreatedOn(String creditNoteCreatedOn) {
		CreditNoteCreatedOn = creditNoteCreatedOn;
	}
	public FlightOrderRowCancellation getFlightOrderRowCancellation() {
		return flightOrderRowCancellation;
	}
	public void setFlightOrderRowCancellation(FlightOrderRowCancellation flightOrderRowCancellation) {
		this.flightOrderRowCancellation = flightOrderRowCancellation;
	}
	
}
