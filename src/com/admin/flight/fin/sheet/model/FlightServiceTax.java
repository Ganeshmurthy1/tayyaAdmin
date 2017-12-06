package com.admin.flight.fin.sheet.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class FlightServiceTax implements Serializable  {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal baseServicetax;
	private BigDecimal SBC;
	private BigDecimal KKC;
	private BigDecimal totalServiceTax;
	private BigDecimal managementFee;
	private BigDecimal convenienceFee;

	public BigDecimal getManagementFee() {
		return managementFee;
	}
	public void setManagementFee(BigDecimal managementFee) {
		this.managementFee = managementFee;
	}
	public BigDecimal getBaseServicetax() {
		return baseServicetax;
	}
	public BigDecimal getSBC() {
		return SBC;
	}
	public BigDecimal getKKC() {
		return KKC;
	}
	public BigDecimal getTotalServiceTax() {
		return totalServiceTax;
	}
	public void setBaseServicetax(BigDecimal baseServicetax) {
		this.baseServicetax = baseServicetax;
	}
	public void setSBC(BigDecimal sBC) {
		SBC = sBC;
	}
	public void setKKC(BigDecimal kKC) {
		KKC = kKC;
	}
	public void setTotalServiceTax(BigDecimal totalServiceTax) {
		this.totalServiceTax = totalServiceTax;
	}
	public BigDecimal getConvenienceFee() {
		return convenienceFee;
	}
	public void setConvenienceFee(BigDecimal convenienceFee) {
		this.convenienceFee = convenienceFee;
	}

}
