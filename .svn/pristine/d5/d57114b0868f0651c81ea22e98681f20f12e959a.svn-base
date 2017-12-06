package com.isl.admin.commission.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class AirlineCommision implements Serializable{
 
	public AirlineCommision(boolean isIataApplicable, boolean isPlbApplicable) {
		super();
		this.isIataApplicable = isIataApplicable;
		this.isPlbApplicable = isPlbApplicable;
		this.iataCommission = new BigDecimal(0);
		this.plbCommission = new BigDecimal(0);
		this.airlineCommissionBlockRow = new AirlineCommissionBlock();
		this.airlineCommissionSheetRow = new AirlineCommissionSheet();
	}
	public AirlineCommision() {
		super();		
		this.iataCommission = new BigDecimal(0);
		this.plbCommission = new BigDecimal(0);
		this.airlineCommissionBlockRow = new AirlineCommissionBlock();
		this.airlineCommissionSheetRow = new AirlineCommissionSheet();
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	private final String rateType="Commission";
	private final String commissionType="Percentage";
	private boolean isIataApplicable;
	private boolean isPlbApplicable;
	private BigDecimal iataCommission;
	private BigDecimal plbCommission;
	
	
	private BigDecimal iataCommissionTotal;
	private BigDecimal plbCommissionTotal;
	
	private BigDecimal commissionTotal;
	
	
	
		
	public BigDecimal getCommissionTotal() {
		return commissionTotal;
	}
	public void setCommissionTotal(BigDecimal commissionTotal) {
		this.commissionTotal = commissionTotal;
	}
	public BigDecimal getIataCommissionTotal() {
		return iataCommissionTotal;
	}
	public void setIataCommissionTotal(BigDecimal iataCommissionTotal) {
		this.iataCommissionTotal = iataCommissionTotal;
	}
	public BigDecimal getPlbCommissionTotal() {
		return plbCommissionTotal;
	}
	public void setPlbCommissionTotal(BigDecimal plbCommissionTotal) {
		this.plbCommissionTotal = plbCommissionTotal;
	}


	
	
	
	private AirlineCommissionSheet airlineCommissionSheetRow;
	private AirlineCommissionBlock airlineCommissionBlockRow;
	
	
	
	public AirlineCommissionSheet getAirlineCommissionSheetRow() {
		return airlineCommissionSheetRow;
	}
	public void setAirlineCommissionSheetRow(
			AirlineCommissionSheet airlineCommissionSheetRow) {
		this.airlineCommissionSheetRow = airlineCommissionSheetRow;
	}
	public AirlineCommissionBlock getAirlineCommissionBlockRow() {
		return airlineCommissionBlockRow;
	}
	public void setAirlineCommissionBlockRow(
			AirlineCommissionBlock airlineCommissionBlockRow) {
		this.airlineCommissionBlockRow = airlineCommissionBlockRow;
	}
	public String getCommissionType() {
		return commissionType;
	}
	public String getRateType() {
		return rateType;
	}
	public BigDecimal getIataCommission() {
		return iataCommission;
	}
	public void setIataCommission(BigDecimal iataCommission) {
		this.iataCommission = iataCommission;
	}
	public BigDecimal getPlbCommission() {
		return plbCommission;
	}
	public void setPlbCommission(BigDecimal plbCommission) {
		this.plbCommission = plbCommission;
	}
	public boolean isIataApplicable() {
		return isIataApplicable;
	}
	public boolean isPlbApplicable() {
		return isPlbApplicable;
	}

}
