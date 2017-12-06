package com.email.notification;



import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lintas.admin.model.Email;



/**
 * The persistent class for the email database table.
 * 
 */
@Entity
@Table(name="email_notification")
@JsonIgnoreProperties(ignoreUnknown = true)
@Proxy(lazy = false)
public class EmailNotification implements Serializable {


	public Long getPerformerCompanyId() {
		return performerCompanyId;
	}
	public void setPerformerCompanyId(Long performerCompanyId) {
		this.performerCompanyId = performerCompanyId;
	}
	public Long getPerformerUserId() {
		return performerUserId;
	}
	public void setPerformerUserId(Long performerUserId) {
		this.performerUserId = performerUserId;
	}
	public Long getReceiverCompanyId() {
		return receiverCompanyId;
	}
	public void setReceiverCompanyId(Long receiverCompanyId) {
		this.receiverCompanyId = receiverCompanyId;
	}
	public Long getReceiverUserId() {
		return receiverUserId;
	}
	public void setReceiverUserId(Long receiverUserId) {
		this.receiverUserId = receiverUserId;
	}

	public static final int TYPE_USER_ACTION_NOTIFICATION = 1;
	public static final int TYPE_WALLET_ACTION_NOTIFICATION = 2;




	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	

	@Column(name="action_type")
	private int actionType;
	
	
	@Column(name="type")
	private int type;

	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	@Column(name="order_id")
	private String orderId;
	

	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Column(name="recipient_comp_id")
	private Long recipientCompanyId;


	@Column(name="performer_comp_id")
	private Long performerCompanyId;
	@Column(name="performer_user_id")
	private Long performerUserId;
	@Column(name="receiver_comp_id")
	private Long receiverCompanyId;
	@Column(name="receiver_user_id")
	private Long receiverUserId;



	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "email_id" )
	private Email email;		

	@Column(name="status_msg" , columnDefinition="TEXT" )
	private String statusMsg;


	@Column(name="mail_status")
	private int mailStatus;
	/*	@Column(name="emailcol")
	private int emailcol;*/



	@Column(name="attempted_count")
	private int attemptedCount;	

	@Column(name = "created_at")
	Timestamp createdAt;

	@Column(name = "updated_at",
			insertable = false,
			columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL")
	Timestamp updatedAt;

	
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



	public String getStatusMsg() {
		return statusMsg;
	}

	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}

	public int getStatus() {
		return mailStatus;
	}

	public void setStatus(int status) {
		this.mailStatus = status;
	}


	public int getActionType() {
		return actionType;
	}

	public void setActionType(int actionType) {
		this.actionType = actionType;
	}


	public Long getRecipientCompanyId() {
		return recipientCompanyId;
	}
	public void setRecipientCompanyId(Long recipientCompanyId) {
		this.recipientCompanyId = recipientCompanyId;
	}
	public Email getEmail() {
		return email;
	}
	public void setEmail(Email email) {
		this.email = email;
	}
	public int getMailStatus() {
		return mailStatus;
	}
	public void setMailStatus(int mailStatus) {
		this.mailStatus = mailStatus;
	}
	public int getAttemptedCount() {
		return attemptedCount;
	}
	public void setAttemptedCount(int attemptedCount) {
		this.attemptedCount = attemptedCount;
	}

	private static final long serialVersionUID = 1L;

	//<!-- 0 parent himself, 1 parent to child, 2 child to parent , 3 child himself -->					
	public static final int ACTION_PARENT_HIMSELF = 0;
	public static final int ACTION_PARENT_TO_CHILD = 1;
	public static final int ACTION_CHILD_TO_PARENT = 2;
	public static final int ACTION_CHILD_HIMSELF = 3;
	public static final int ACTION_CHILD_TO_GRAND_CHILD = 4;
	
	
	
	 public static final int EMAIL_TYPE_COMMISSION_SHEET_CREATION = 29;//###################  COMMISSION SHEET CREATION
	 public static final int EMAIL_TYPE_COMMISSION_SHEET_MODIFICATION = 30;//###################  COMMISSION SHEET MODIFICATION
	 public static final int EMAIL_TYPE_COMMISSION_SHEET_DELETION = 31;//###################  COMMISSION SHEET DELETION
	 public static final int EMAIL_TYPE_COMMISSION_SHEET_ACTIVATION = 32;//###################  COMMISSION SHEET ACTIVATION	
	 public static final int EMAIL_TYPE_COMMISSION_SHEET_DEACTIVATION = 33;//###################  COMMISSION SHEET ACTIVATION
		
		
	 public static final int EMAIL_TYPE_COMMISSION_BLOCK_CREATION = 34;//###################  COMMISSION BLOCK CREATION
	 public static final int EMAIL_TYPE_COMMISSION_BLOCK_MODIFICATION = 35;//###################  COMMISSION BLOCK MODIFICATION
	 public static final int EMAIL_TYPE_COMMISSION_BLOCK_DELETION = 36;//###################  COMMISSION BLOCK DELETION
	 public static final int EMAIL_TYPE_COMMISSION_BLOCK_ACTIVATION = 37;//###################  COMMISSION BLOCK ACTIVATION	
	 public static final int EMAIL_TYPE_COMMISSION_BLOCK_DEACTIVATION = 38;//###################  COMMISSION BLOCK ACTIVATION
	
	
	
	


	
	
}