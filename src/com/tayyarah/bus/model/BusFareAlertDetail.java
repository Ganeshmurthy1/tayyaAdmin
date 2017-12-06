package com.tayyarah.bus.model;


import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bus_fare_alert_detail")
public class BusFareAlertDetail implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int id;
	@Column(name="companyid")
	private Integer companyId;
	@Column(name="configid")
	private Integer configId;
	@Column(name="createdbyuserid")
	private String  createdbyuserid;
	@Column(name="totalfare")
	private BigDecimal totalFare;
	@Column(name="origin")
	private String  origin;
	@Column(name="destination")
	private String  destination;
	@Column(name="operator_name")
	private String  operatorName;
	@Column(name="departure_time")
	private String  departureTime;
	@Column(name="travel_date")
	private String travelDate;
	@Column(name="booking_date")
	private String bookingDate;
	@Column(name="boarding_point")
	private String  boardingPoint;
	@Column(name="bus_type")
	private String  busType;
	@Column(name="reasons",columnDefinition = "LONGTEXT")
	private String reasons;	
	@Column(name="orderid")
	private String orderid;
	
	public int getId() {
		return id;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public Integer getConfigId() {
		return configId;
	}
	public String getCreatedbyuserid() {
		return createdbyuserid;
	}
	public BigDecimal getTotalFare() {
		return totalFare;
	}
	public String getOrigin() {
		return origin;
	}
	public String getDestination() {
		return destination;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public String getDepartureTime() {
		return departureTime;
	}
	public String getBoardingPoint() {
		return boardingPoint;
	}
	public String getBusType() {
		return busType;
	}
	public String getReasons() {
		return reasons;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public void setConfigId(Integer configId) {
		this.configId = configId;
	}
	public void setCreatedbyuserid(String createdbyuserid) {
		this.createdbyuserid = createdbyuserid;
	}
	public void setTotalFare(BigDecimal totalFare) {
		this.totalFare = totalFare;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
	public void setBoardingPoint(String boardingPoint) {
		this.boardingPoint = boardingPoint;
	}
	public void setBusType(String busType) {
		this.busType = busType;
	}
	public void setReasons(String reasons) {
		this.reasons = reasons;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getTravelDate() {
		return travelDate;
	}
	public String getBookingDate() {
		return bookingDate;
	}
	public void setTravelDate(String travelDate) {
		this.travelDate = travelDate;
	}
	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}
	

}
