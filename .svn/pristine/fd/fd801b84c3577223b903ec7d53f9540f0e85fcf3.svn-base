package com.lintas.admin.flight.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.lintas.admin.common.model.OrderCustomer;
import com.lintas.admin.common.model.Timestampable;

@Entity
@Table(name = "flight_order_customer_ssr")
public class FlightOrderCustomerSSR extends Timestampable implements Serializable{ 

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_row_id", referencedColumnName = "id")
	private FlightOrderRow flightOrderRow;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id", referencedColumnName = "id")
	private com.lintas.admin.common.model.OrderCustomer flightCustomer;
	
	@Transient 
	@Column(name = "paxName")
	private String paxName;
	
	
	public String getPaxName() {
		return paxName;
	}

	public void setPaxName(String paxName) {
		this.paxName = paxName;
	}

	@Column(name = "mealtype")
	private String mealType;

	@Column(name = "mealprice")
	private String mealPrice;
	
	@Column(name = "baggagetype")
	private String baggageType;
	
	@Column(name = "baggageprice")
	private String baggagePrice;

	@Column(name = "seattype")
	private String seatType;
	
	
	@Column(name = "returnmealtype")
	private String returnmealType;

	@Column(name = "returnmealprice")
	private String returnmealPrice;
	
	@Column(name = "returnbaggagetype")
	private String returnbaggageType;
	
	@Column(name = "returnbaggageprice")
	private String returnbaggagePrice;

	@Column(name = "returnseattype")
	private String returnseatType;

	@Column(name = "mealname")
	private String mealname;
	
	@Column(name = "returnmealname")
	private String returnmealname;
	
	@Column(name = "baggageweight")
	private String baggageweight;

	@Column(name = "returnbaggageweight")
	private String returnbaggageweight;
	
	
	public FlightOrderRow getFlightOrderRow() {
		return flightOrderRow;
	}

	public void setFlightOrderRow(FlightOrderRow flightOrderRow) {
		this.flightOrderRow = flightOrderRow;
	}

	public OrderCustomer getFlightCustomer() {
		return flightCustomer;
	}

	public void setFlightCustomer(OrderCustomer flightCustomer) {
		this.flightCustomer = flightCustomer;
	}

	public String getMealType() {
		return mealType;
	}

	public void setMealType(String mealType) {
		this.mealType = mealType;
	}

	public String getMealPrice() {
		return mealPrice;
	}

	public void setMealPrice(String mealPrice) {
		this.mealPrice = mealPrice;
	}

	public String getBaggageType() {
		return baggageType;
	}

	public void setBaggageType(String baggageType) {
		this.baggageType = baggageType;
	}

	public String getBaggagePrice() {
		return baggagePrice;
	}

	public void setBaggagePrice(String baggagePrice) {
		this.baggagePrice = baggagePrice;
	}

	public String getSeatType() {
		return seatType;
	}

	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}

	public String getReturnmealType() {
		return returnmealType;
	}

	public void setReturnmealType(String returnmealType) {
		this.returnmealType = returnmealType;
	}

	public String getReturnmealPrice() {
		return returnmealPrice;
	}

	public void setReturnmealPrice(String returnmealPrice) {
		this.returnmealPrice = returnmealPrice;
	}

	public String getReturnbaggageType() {
		return returnbaggageType;
	}

	public void setReturnbaggageType(String returnbaggageType) {
		this.returnbaggageType = returnbaggageType;
	}

	public String getReturnbaggagePrice() {
		return returnbaggagePrice;
	}

	public void setReturnbaggagePrice(String returnbaggagePrice) {
		this.returnbaggagePrice = returnbaggagePrice;
	}

	public String getReturnseatType() {
		return returnseatType;
	}

	public void setReturnseatType(String returnseatType) {
		this.returnseatType = returnseatType;
	}

	public String getMealname() {
		return mealname;
	}

	public void setMealname(String mealname) {
		this.mealname = mealname;
	}

	public String getReturnmealname() {
		return returnmealname;
	}

	public void setReturnmealname(String returnmealname) {
		this.returnmealname = returnmealname;
	}

	public String getBaggageweight() {
		return baggageweight;
	}

	public void setBaggageweight(String baggageweight) {
		this.baggageweight = baggageweight;
	}

	public String getReturnbaggageweight() {
		return returnbaggageweight;
	}

	public void setReturnbaggageweight(String returnbaggageweight) {
		this.returnbaggageweight = returnbaggageweight;
	}

	
	
}
