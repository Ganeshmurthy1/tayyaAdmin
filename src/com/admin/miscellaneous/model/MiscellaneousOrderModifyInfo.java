/**
 * 
 */
package com.admin.miscellaneous.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Manish Samrat
 *
 */
@Entity
@Table(name = "miscellaneous_order_modified_info")
public class MiscellaneousOrderModifyInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long  Id;
 	@Column(name="row_id")
	private int insuranceOrderRowId;
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
	public Long getId() {
		return Id;
	}
	public int getInsuranceOrderRowId() {
		return insuranceOrderRowId;
	}
	public String getUserId() {
		return userId;
	}
	public String getStatusAction() {
		return statusAction;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public String getUserComments() {
		return userComments;
	}
	public Timestamp getOrderedAt() {
		return orderedAt;
	}
	public String getActionType() {
		return actionType;
	}
	public void setId(Long id) {
		Id = id;
	}
	public void setInsuranceOrderRowId(int insuranceOrderRowId) {
		this.insuranceOrderRowId = insuranceOrderRowId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setStatusAction(String statusAction) {
		this.statusAction = statusAction;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public void setUserComments(String userComments) {
		this.userComments = userComments;
	}
	public void setOrderedAt(Timestamp orderedAt) {
		this.orderedAt = orderedAt;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

}
