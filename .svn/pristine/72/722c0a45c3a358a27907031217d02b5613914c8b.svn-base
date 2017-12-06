package com.admin.common.config.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.lintas.admin.common.model.Timestampable;

@Entity
@Table(name = "rail_service_tax_config")
public class RailServiceTaxConfig extends Timestampable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Transient
	private BigDecimal visaManagementFee;
	@Transient
	private BigDecimal advertisementAndOtherManagementFee;
	@Column(name="applicable_fare")
	private String applicableFare;
	@Column(name="basic_tax", columnDefinition="decimal(20,3) default '0.000'")
	private BigDecimal basicTax;
	@Column(name="swatch_bharath_cess", columnDefinition="decimal(20,3) default '0.000'")
	private BigDecimal swatchBharathCess;
	@Column(name="krishi_kalyan_cess", columnDefinition="decimal(20,3) default '0.000'")
	private BigDecimal krishiKalyanCess;
	@Column(name="total_tax", columnDefinition="decimal(20,3) default '0.000'")
	private BigDecimal totalTax;
	@Column(name="convenience_fee", columnDefinition="decimal(20,3) default '0.000'")
	private BigDecimal convenienceFee;
	@Column(name="management_fee", columnDefinition="decimal(20,3) default '0.000'")
	private BigDecimal managementFee;

	@Column(name="management_fee_tatkal", columnDefinition="decimal(20,3) default '0.000'")
	private BigDecimal managementFeeTatkal;

	public BigDecimal getManagementFee() {
		return managementFee!=null?managementFee:new BigDecimal("0.00");
	}
	public void setManagementFee(BigDecimal managementFee) {
		this.managementFee = managementFee!=null?managementFee:new BigDecimal("0.00");;
	}
	public String getApplicableFare() {
		return applicableFare;
	}
	public void setApplicableFare(String applicableFare) {
		this.applicableFare = applicableFare;
	}
	public BigDecimal getBasicTax() {
		return basicTax!=null?basicTax:new BigDecimal("0.00");
	}
	public void setBasicTax(BigDecimal basicTax) {
		this.basicTax = basicTax!=null?basicTax:new BigDecimal("0.00");;
	}
	public BigDecimal getSwatchBharathCess() {
		return swatchBharathCess!=null?swatchBharathCess:new BigDecimal("0.00");
	}
	public void setSwatchBharathCess(BigDecimal swatchBharathCess) {
		this.swatchBharathCess = swatchBharathCess!=null?swatchBharathCess:new BigDecimal("0.00");
	}
	public BigDecimal getKrishiKalyanCess() {
		return krishiKalyanCess!=null?krishiKalyanCess:new BigDecimal("0.00");
	}
	public void setKrishiKalyanCess(BigDecimal krishiKalyanCess) {
		this.krishiKalyanCess = krishiKalyanCess!=null?krishiKalyanCess:new BigDecimal("0.00");;
	}
	public BigDecimal getTotalTax() {
		return totalTax!=null?totalTax:new BigDecimal("0.00");
	}
	public void setTotalTax(BigDecimal totalTax) {
		this.totalTax = totalTax!=null?totalTax:new BigDecimal("0.00");;
	}
	public BigDecimal getConvenienceFee() {
		return convenienceFee!=null?convenienceFee:new BigDecimal("0.00");
	}
	public void setConvenienceFee(BigDecimal convenienceFee) {
		this.convenienceFee = convenienceFee!=null?convenienceFee:new BigDecimal("0.00");;
	}
	public BigDecimal getVisaManagementFee() {
		return visaManagementFee;
	}
	public void setVisaManagementFee(BigDecimal visaManagementFee) {
		this.visaManagementFee = visaManagementFee;
	}
	public BigDecimal getAdvertisementAndOtherManagementFee() {
		return advertisementAndOtherManagementFee;
	}
	public void setAdvertisementAndOtherManagementFee(BigDecimal advertisementAndOtherManagementFee) {
		this.advertisementAndOtherManagementFee = advertisementAndOtherManagementFee;
	}
	public BigDecimal getManagementFeeTatkal() {
		return managementFeeTatkal;
	}
	public void setManagementFeeTatkal(BigDecimal managementFeeTatkal) {
		this.managementFeeTatkal = managementFeeTatkal;
	}

	
	
 
}

