package com.admin.dashboard.analysis.json.vo;

import java.math.BigDecimal;

public class ServicesCountDataVO {
	/**
	 * @author Basha
	 * 28 Aug 2017
	 */
	private String  serviceType;
	private  Long  count;
	private  Long  cancelledCount;
	private double percentage;
	private BigDecimal totalBookingAmount;
	private BigDecimal totalRefundedAmount;
	private BigDecimal totalLossAmount;
	 
	public double getPercentage() {
		return percentage;
	}
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	 
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public ServicesCountDataVO() {
		
	}
	public ServicesCountDataVO(String serviceType, Long count, Long cancelledCount, double percentage,
			BigDecimal totalBookingAmount, BigDecimal totalRefundedAmount, BigDecimal totalLossAmount) {
		super();
		this.serviceType = serviceType;
		this.count = count;
		this.cancelledCount = cancelledCount;
		this.percentage = percentage;
		this.totalBookingAmount = totalBookingAmount;
		this.totalRefundedAmount = totalRefundedAmount;
		this.totalLossAmount = totalLossAmount;
	}
	public Long getCancelledCount() {
		return cancelledCount;
	}
	public void setCancelledCount(Long cancelledCount) {
		this.cancelledCount = cancelledCount;
	}
	public BigDecimal getTotalBookingAmount() {
		return totalBookingAmount;
	}
	public void setTotalBookingAmount(BigDecimal totalBookingAmount) {
		this.totalBookingAmount = totalBookingAmount;
	}
	public BigDecimal getTotalRefundedAmount() {
		return totalRefundedAmount;
	}
	public void setTotalRefundedAmount(BigDecimal totalRefundedAmount) {
		this.totalRefundedAmount = totalRefundedAmount;
	}
	public BigDecimal getTotalLossAmount() {
		return totalLossAmount;
	}
	public void setTotalLossAmount(BigDecimal totalLossAmount) {
		this.totalLossAmount = totalLossAmount;
	}
	
	 
}
