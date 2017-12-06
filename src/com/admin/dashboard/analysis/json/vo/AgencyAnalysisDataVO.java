package com.admin.dashboard.analysis.json.vo;

import java.math.BigDecimal;

public class AgencyAnalysisDataVO {
	private String name;
	private  BigDecimal  petcentage;
	private BigDecimal totalAmount;
	public String getName() {
		return name;
	}
	 
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getPetcentage() {
		return petcentage;
	}

	public void setPetcentage(BigDecimal petcentage) {
		this.petcentage = petcentage;
	}
	
}
