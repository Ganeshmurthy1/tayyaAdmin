package com.isl.admin.filter;

import java.io.Serializable;
import java.math.BigDecimal;

public class AmountFilterOption implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public AmountFilterOption(BigDecimal amountMin, BigDecimal amountMax) {
		super();
		this.amountMin = amountMin;
		this.amountMax = amountMax;
		this.amountMinDefault = new BigDecimal(0);
		this.amountMaxDefault = new BigDecimal(500000);
	}
	public AmountFilterOption() {
		super();
		this.amountMin = new BigDecimal(0);
		this.amountMax = new BigDecimal(200000);
		this.amountMinDefault = new BigDecimal(0);
		this.amountMaxDefault = new BigDecimal(500000);
	}

	public BigDecimal getAmountMinDefault() {
		return amountMinDefault;
	}
	public BigDecimal getAmountMaxDefault() {
		return amountMaxDefault;
	}
	@Override
	public String toString() {
		return "AmountFilterOption [amountMin=" + amountMin + ", amountMax=" + amountMax + "]";
	}
	public BigDecimal getAmountMin() {
		return amountMin;
	}
	public void setAmountMin(BigDecimal amountMin) {
		this.amountMin = amountMin;
	}
	public BigDecimal getAmountMax() {
		return amountMax;
	}
	public void setAmountMax(BigDecimal amountMax) {
		this.amountMax = amountMax;
	}
	private BigDecimal amountMin;
	private BigDecimal amountMax;
	private final BigDecimal amountMinDefault;
	private final BigDecimal amountMaxDefault;
	
}
