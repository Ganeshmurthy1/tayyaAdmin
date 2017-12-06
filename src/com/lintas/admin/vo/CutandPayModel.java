package com.lintas.admin.vo;

import java.math.BigDecimal;

public class CutandPayModel {
	private String userId;
	private BigDecimal payableAmount;
	private boolean bookingStatus;
	private String bookingRemarks;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public BigDecimal getPayableAmount() {
		return payableAmount;
	}
	public void setPayableAmount(BigDecimal payableAmount) {
		this.payableAmount = payableAmount;
	}
	public boolean isBookingStatus() {
		return bookingStatus;
	}
	public void setBookingStatus(boolean bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
	public String getBookingRemarks() {
		return bookingRemarks;
	}
	public void setBookingRemarks(String bookingRemarks) {
		this.bookingRemarks = bookingRemarks;
	}
}
