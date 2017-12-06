package com.lintas.admin.common.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
@Entity
@Table(name = "order_modified_info")
 public class OrderModifiedInfo implements Serializable{
 /**
 * 
 */
	private static final long serialVersionUID = 1L;
	@javax.persistence.Id
 	@GeneratedValue
	private Long  Id;
 	@Column(name="row_id")
	private int flightOrderRowId;
	@Column(name="userId")
	private String userId;
	@Column(name="status_action")
	private String  statusAction;
	@Column(name="payment_status")
	private String  paymentStatus;
	@Column(name="user_comments")
	private String  userComments;
	@Column(name="ordered_At")
	private Timestamp orderedAt;
	@Column(name="action_type")
	private String actionType;
	/*@Column(name="status_action")
	private String  status_action="";*/
	/*@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_row_id", referencedColumnName = "id")
	private FlightOrderRow flightOrderRow;*/
	public String getStatusAction(){
		return statusAction;
	}
	public void setStatusAction(String statusAction) {
		this.statusAction = statusAction;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	 
	public String getUserComments() {
		return userComments;
	}
	public void setUserComments(String userComments) {
		this.userComments = userComments;
	}
	/**
	 * @return the orderedAt
	 */
	public Timestamp getOrderedAt() {
		return orderedAt;
	}
	/**
	 * @param orderedAt the orderedAt to set
	 */
	public void setOrderedAt(Timestamp orderedAt) {
		this.orderedAt = orderedAt;
	}
	public int getFlightOrderRowId() {
		return flightOrderRowId;
	}
	public void setFlightOrderRowId(int flightOrderRowId) {
		this.flightOrderRowId = flightOrderRowId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/*public FlightOrderRow getFlightOrderRow() {
		return flightOrderRow;
	}
	public void setFlightOrderRow(FlightOrderRow flightOrderRow) {
		this.flightOrderRow = flightOrderRow;
	}*/
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	 
 }
