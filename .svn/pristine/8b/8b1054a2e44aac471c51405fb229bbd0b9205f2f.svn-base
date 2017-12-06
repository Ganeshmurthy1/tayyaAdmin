package com.isl.admin.filter;

import java.util.ArrayList;
import java.util.List;

public class CommonConfigFilter extends Filter{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String  mode;
	private String  status;
	private String  cardHolderName;
	 private String  bankName;
	 private String paymentType;
	 private String mailId;
	 private String id;
	 
	public String getCardHolderName() {
		return cardHolderName;
	}
	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	private List<String> commonConfigStatusQueue;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
 
	
	public CommonConfigFilter() {
		super();
		this.commonConfigStatusQueue = new ArrayList<>();
		 this.commonConfigStatusQueue.add("live");
		this.commonConfigStatusQueue.add("test");
		 
		
	
	}
	public List<String> getCommonConfigStatusQueue() {
		return commonConfigStatusQueue;
	}
	public void setCommonConfigStatusQueue(List<String> commonConfigStatusQueue) {
		this.commonConfigStatusQueue = commonConfigStatusQueue;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
