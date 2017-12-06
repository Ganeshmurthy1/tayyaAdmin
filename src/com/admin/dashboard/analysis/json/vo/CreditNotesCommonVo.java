package com.admin.dashboard.analysis.json.vo;

import java.math.BigDecimal;

public class CreditNotesCommonVo {
	/**
	 * @author Shaik Basha
	 * date: 10-10-2017
	 */
	private int id;
	private BigDecimal refundedAmount;
	private BigDecimal totalBookingAmount;
	private String companyId;
	private String userId;
	private int rowId;
	private boolean isCreditnoteIssued;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public BigDecimal getRefundedAmount() {
		return refundedAmount;
	}
	public void setRefundedAmount(BigDecimal refundedAmount) {
		this.refundedAmount = refundedAmount;
	}
	public BigDecimal getTotalBookingAmount() {
		return totalBookingAmount;
	}
	public void setTotalBookingAmount(BigDecimal totalBookingAmount) {
		this.totalBookingAmount = totalBookingAmount;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getRowId() {
		return rowId;
	}
	public void setRowId(int rowId) {
		this.rowId = rowId;
	}
	public boolean isCreditnoteIssued() {
		return isCreditnoteIssued;
	}
	public void setCreditnoteIssued(boolean isCreditnoteIssued) {
		this.isCreditnoteIssued = isCreditnoteIssued;
	}

}
