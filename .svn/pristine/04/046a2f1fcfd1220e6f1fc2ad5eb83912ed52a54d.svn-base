package com.admin.ageing.report.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import com.lintas.admin.flight.model.FlightOrderRowGstTax;
import com.lintas.admin.flight.model.FlightOrderRowServiceTax;

public class OrderRowDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal finalPrice;
	private Timestamp createdAt;
	private BigDecimal gstOnFlights;
	private String  companyId;
	private FlightOrderRowServiceTax flightOrderRowServiceTax;	
	private FlightOrderRowGstTax flightOrderRowGstTax;	
	public BigDecimal getFinalPrice() {
		return finalPrice;
	}
	public BigDecimal getGstOnFlights() {
		return gstOnFlights;
	}
	public FlightOrderRowServiceTax getFlightOrderRowServiceTax() {
		return flightOrderRowServiceTax;
	}
	public FlightOrderRowGstTax getFlightOrderRowGstTax() {
		return flightOrderRowGstTax;
	}
	public void setFinalPrice(BigDecimal finalPrice) {
		this.finalPrice = finalPrice;
	}
	public void setGstOnFlights(BigDecimal gstOnFlights) {
		this.gstOnFlights = gstOnFlights;
	}
	public void setFlightOrderRowServiceTax(FlightOrderRowServiceTax flightOrderRowServiceTax) {
		this.flightOrderRowServiceTax = flightOrderRowServiceTax;
	}
	public void setFlightOrderRowGstTax(FlightOrderRowGstTax flightOrderRowGstTax) {
		this.flightOrderRowGstTax = flightOrderRowGstTax;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	
}
