package com.lintas.admin.model;
import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the email database table.
 * 
 */
@Entity
@Table(name="email")
 public class Email implements Serializable {
	public Email(){
		super();
	}
	public Email(String orderId, int mailStatus, String toEmailAddress, String ccEmailAddress, int type) {
		super();
		this.orderId = orderId;
		this.mailStatus = mailStatus;
		this.toEmailAddress = toEmailAddress;
		this.ccEmailAddress = ccEmailAddress;
		this.type = type;
	}

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@Column(name="order_id")
	private String orderId;

	@Column(name="status_msg")
	private String statusMsg;

	@Column(name="mail_status")
	private int mailStatus;
	/*	@Column(name="emailcol")
	private int emailcol;*/
	@Column(name = "to_emailaddress")
	 private String toEmailAddress;
	  
	 @Column(name = "cc_emailaddress")
	 private String ccEmailAddress;
	
	@Column(name="type")
	private int type;
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

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getStatusMsg() {
		return statusMsg;
	}

	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}


	/*	public int getEmailcol() {
		return emailcol;
	}

	public void setEmailcol(int emailcol) {
		this.emailcol = emailcol;
	}*/

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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
 	public String getToEmailAddress() {
		return toEmailAddress;
	}

	public void setToEmailAddress(String toEmailAddress) {
		this.toEmailAddress = toEmailAddress;
	}

	public String getCcEmailAddress() {
		return ccEmailAddress;
	}

	public void setCcEmailAddress(String ccEmailAddress) {
		this.ccEmailAddress = ccEmailAddress;
	}

	private static final long serialVersionUID = 1L;
	public static final int STATUS_PENDING = 0;//email is just inserted in email table
	public static final int STATUS_PROCESSING = -1;//email is being processed for sending
	public static final int STATUS_SENT_SUCCESS = 1;// email is being sent successfully
	public static final int STATUS_SENT_ERROR_WRONG_EMAIL = 2;// email sent error
	public static final int STATUS_SENT_ERROR_SERVER_ISSUE = 3;// email sent error
	public static final int STATUS_EMAIL_BLOCKED = 4;// email sent error for firewal issue
	public static final int STATUS_VERIFIED = 5;//email has been verified successfully


	public static final int EMAIL_TYPE_HOTEL_VOUCHER = 1;
	public static final int EMAIL_TYPE_FLIGHT_VOUCHER = 2;
	public static final int EMAIL_TYPE_USER_REGISTRATION = 3;
	public static final int EMAIL_TYPE_USER_FORGOT_PWD_REGISTRATION = 6;//storing user d
	public static final int EMAIL_TYPE_COMPANY_APPROVAL = 9;
	public static final int EMAIL_TYPE_COMPANY_REGISTRATION = 4;
	public static final int EMAIL_TYPE_COMPANY_FORGOT_PWD = 7;
	public static final int EMAIL_TYPE_FRONT_USER_REGISTRATION = 5;
	public static final int EMAIL_TYPE_FRONT_USER_FORGOT_PWD = 8;
	public static final int EMAIL_TYPE_COMPANY_CONFIG_APPROVAL = 10;
	public static final int EMAIL_TYPE_POSTPAID_BILL= 11;
	public static final int EMAIL_TYPE_BLOCKED_USER= 12;
	public static final int EMAIL_TYPE_WHITE_LABEL= 13;
	public static final int EMAIL_TYPE_USER_RESET_PASSWORD = 14;//storing user id
	public static final int EMAIL_TYPE_COMPANY_RESET_PASSWORD = 15;//storing company id
	public static final int EMAIL_TYPE_SUPER_INVOICE_TO_OTHERS = 16;//sending invoice from lintas to others
	public static final int EMAIL_TYPE_DIS_INVOICE_TO_OTHERS = 17;//sending invoice from Dis to others

	public static final int EMAIL_TYPE_HOTEL_INVOICE_SUPER_TO_OTHERS = 18;//sending Hotel invoice from lintas to others
	public static final int EMAIL_TYPE_HOTEL_INVOICE_DISTRIBUTOR_TO_OTHERS = 19;//sending hotel invoice from Dis to others

	public static final int EMAIL_TYPE_VERIFICATION_USER = 20;
	public static final int EMAIL_TYPE_VERIFICATION_COMPANY = 21;
	//public static final int EMAIL_TYPE_WALLET_NOTIFICATION = 22;//###################  EMAIL WALLET NOTIFICATION  
	//###################  EMAIL ACKNOWLEDGEMENTS TYPES......
	public static final int EMAIL_TYPE_CREDITNOTE_REQUEST_HOTEL = 23;//###################  CREDIT NOTE REQUEST HOTEL  
	public static final int EMAIL_TYPE_CREDITNOTE_CONFIRM_HOTEL = 24;//###################  CREDIT NOTE REQUEST HOTEL 

	public static final int EMAIL_TYPE_CREDITNOTE_REQUEST_FLIGHT = 25;//###################  CREDIT NOTE REQUEST FLIGHT  
	public static final int EMAIL_TYPE_CREDITNOTE_CONFIRM_FLIGHT = 26;//###################  CREDIT NOTE REQUEST FLIGHT  

	//###################  EMAIL ACKNOWLEDGEMENTS TYPES......
	public static final int EMAIL_TYPE_VERIFICATION_ACK = 27;//sending email verfication ack back to parent company..
	public static final int EMAIL_TYPE_USER_CREDENTIALS = 28;//sending user credential mail...
	public static final int EMAIL_TYPE_USER_ENQUERIES = 29;//sending user credential mail...	 

	public static final int EMAIL_TYPE_FAILS_HOTEL_BOOK = 30;//sending user credential mail...
	public static final int EMAIL_TYPE_FAILS_FLIGHT_BOOK = 31;//sending user credential mail...	 

	public static final int EMAIL_TYPE_COMPANY_UPDATION = 32;//sending company credential mail...
	public static final int EMAIL_TYPE_EMPLOYEE_UPDATION = 33;//sending employee credential mail...
	public static final int EMAIL_TYPE_FRONT_USER_UPDATION = 34;//sending front user credential mail...
	public static final int EMAIL_TYPE_COMMISSION_NOTIFICATION = 35;//sending commission change mail...

	public static final int EMAIL_TYPE_WALLET_CREDIT_PARENT_NOTIFICATION = 36;//###################  EMAIL WALLET NOTIFICATION  
	public static final int EMAIL_TYPE_WALLET_CREDIT_CHILD_NOTIFICATION = 22;//###################  EMAIL WALLET NOTIFICATION  

	public static final int EMAIL_TYPE_WALLET_DEBIT_PARENT_NOTIFICATION = 38;//###################  EMAIL WALLET NOTIFICATION  
	public static final int EMAIL_TYPE_WALLET_DEBIT_CHILD_NOTIFICATION = 39;//###################  EMAIL WALLET NOTIFICATION  

	public static final int EMAIL_TYPE_CUSTOMER_INVOICE_FLIGHT = 40;//flight customer invoice
	public static final int EMAIL_TYPE_CUSTOMER_INVOICE_HOTEL = 41;//flight customer invoice

	public static final int EMAIL_TYPE_ENQUIRY = 42;//user enquiry


	public static final int EMAIL_TYPE_HOTEL_OFFLINE_REQUEST = 46;// hotel offline booking request	
	public static final int EMAIL_TYPE_HOTEL_OFFLINE_REQUEST_QUOTATION = 47;// hotel offline booking request quotation
	public static final int EMAIL_TYPE_HOTEL_OFFLINE_VOUCHER = 48;// hotel offline booking voucher

	public static final int EMAIL_TYPE_FLIGHT_OFFLINE_REQUEST = 49;// flight offline booking request
	public static final int EMAIL_TYPE_FLIGHT_OFFLINE_REQUEST_QUOTATION = 50;// flight offline booking request quotation
	public static final int EMAIL_TYPE_FLIGHT_OFFLINE_VOUCHER = 51;// flight offline booking voucher		


	public static final int EMAIL_TYPE_BUG_TRACKER_HISTORY_CREATED =52;//STATUS CRAETED
	public static final int EMAIL_TYPE_BUG_TRACKER_HISTORY_ASSIGNED =53;//STATUS ASSIGNED
	public static final int EMAIL_TYPE_BUG_TRACKER_HISTORY_PENDING =54;//STATUS PENDING
	public static final int EMAIL_TYPE_BUG_TRACKER_HISTORY_WORK_IN_PROGRESS =55;//STATUS WORK_IN_PROGRESS
	public static final int EMAIL_TYPE_BUG_TRACKER_HISTORY_STILL_IN_PROGRESS =56;//STATUS STILL_IN_PROGRESS
	public static final int EMAIL_TYPE_BUG_TRACKER_HISTORY_REVIEW =57;//STATUS REVIEW
	public static final int EMAIL_TYPE_BUG_TRACKER_HISTORY_TEST_REVIEW =87;//STATUS TEST REVIEW
	public static final int EMAIL_TYPE_BUG_TRACKER_HISTORY_CLOSED =58;//STATUS CLOSED

	public static final int EMAIL_TYPE_HOTEL_QUOTATION_ALTERNATIVE_EMAIL_SEND =59;//STATUS CLOSED

	public static final int EMAIL_TYPE_FLIGHT_QUOTATION_ALTERNATIVE_EMAIL_SEND =60;//STATUS CLOSED

	public static final int EMAIL_TYPE_FLIGHT_OFFLINE_ONLINE_INVOICE_SEND=61;//STATUS CLOSED

	public static final int EMAIL_TYPE_SEND_NOTIFICATION=62; //SEND NOTIFICATION TO USER BY MAIL
	public static final int EMAIL_TYPE_HOTEL_OFFLINE_ONLINE_INVOICE_SEND=63;//STATUS CLOSED
	
	public static final int EMAIL_TYPE_CAR_INVOICE=64; //SEND NOTIFICATION TO USER BY MAIL
	public static final int EMAIL_TYPE_BUS_INVOICE=65;//STATUS CLOSED
	public static final int EMAIL_TYPE_VISA_INVOICE=66;//STATUS CLOSED
	public static final int EMAIL_TYPE_TRAIN_INVOICE=67;//STATUS CLOSED
	public static final int EMAIL_TYPE_INSURANCE_INVOICE=68;//STATUS CLOSED
	public static final int EMAIL_TYPE_MISC_INVOICE=69;//STATUS CLOSED
	public static final int EMAIL_TYPE_MEAL_INVOICE=70;//STATUS CLOSED
	public static final int EMAIL_TYPE_LABOR_INVOICE=71;//STATUS CLOSED
	 
	public static final int EMAIL_TYPE_CREDITNOTE_REQUEST_CAR =72; 
	public static final int EMAIL_TYPE_CREDITNOTE_CONFIRM_CAR = 73;
	public static final int EMAIL_TYPE_CREDITNOTE_REQUEST_BUS =74; 
	public static final int EMAIL_TYPE_CREDITNOTE_CONFIRM_BUS = 75;
	public static final int EMAIL_TYPE_CREDITNOTE_REQUEST_TRAIN =76; 
	public static final int EMAIL_TYPE_CREDITNOTE_CONFIRM_TRAIN = 77;
	
	public static final int EMAIL_TYPE_CREDITNOTE_REQUEST_VISA =78; 
	public static final int EMAIL_TYPE_CREDITNOTE_CONFIRM_VISA = 79;
	public static final int EMAIL_TYPE_CREDITNOTE_REQUEST_INSURANCE =80; 
	public static final int EMAIL_TYPE_CREDITNOTE_CONFIRM_INSURANCE = 81;
	public static final int EMAIL_TYPE_CREDITNOTE_CONFIRM_HOTEL_Customer = 82;//###################  CREDIT NOTE customer HOTEL 
	
	public static final int EMAIL_TYPE_WALLET_DEPOSIT_PARENT_NOTIFICATION = 83;//################### parent wallet deposit mail type
	public static final int EMAIL_TYPE_WALLET_DEPOSIT_CHILD_NOTIFICATION = 84; //################### child wallet deposit mail type
	
	public static final int EMAIL_TYPE_WALLET_WITHDRAW_PARENT_NOTIFICATION = 85;//################### parent wallet deposit mail type
	public static final int EMAIL_TYPE_WALLET_WITHDRAW_CHILD_NOTIFICATION = 86; //################### child wallet deposit mail type
	public static final int EMAIL_TYPE_BUS_VOUCHER = 88;
	public static final int EMAIL_TYPE_CREDITNOTE_REQUEST_MISCELLANEOUS =89; 
	public static final int EMAIL_TYPE_CREDITNOTE_CONFIRM_MISCELLANEOUS = 90;
	public static final int EMAIL_TYPE_BUG_TRACKER_COMMENT_CREATED =91;// Bug Comment Created
}