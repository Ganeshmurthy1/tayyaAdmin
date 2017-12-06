package com.lintas.admin.flight.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
@Entity
@Table(name="flight_fare_alert_detail")
public class FlightFareAlertDetail implements Serializable {

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
	@Column(name="date_of_travel")
	private String travelDate;
	@Column(name="booking_date")
	private String bookingDate;
	@Column(name="origincode")
	private String originCode;
	@Column(name="destinationcode")
	private String destinationcode;
	@Column(name="departuretime")
	private String departureTime;
	@Column(name="arrivaltime")
	private String arrivalTime;
	@Column(name="airline")
	private String airlineName;
	@Column(name="fareclass")
	private String fareClass;
	@Column(name="flightno")
	private String flightNo;	
	@Column(name="reasons",columnDefinition = "LONGTEXT")
	private String reasons;	
	@Column(name="orderid")
	private String orderid;
	@Column(name="isconnflight_available")
	private Boolean isConnFlightAvailable;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "flightFareAlertDetail",targetEntity = FlightFareAlertConnectingFlight.class)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<FlightFareAlertConnectingFlight> flightFareAlertConnectingFlightList;
	
	
	
	public Boolean getIsConnFlightAvailable() {
		return isConnFlightAvailable;
	}
	public void setIsConnFlightAvailable(Boolean isConnFlightAvailable) {
		this.isConnFlightAvailable = isConnFlightAvailable;
	}
	public List<FlightFareAlertConnectingFlight> getFlightFareAlertConnectingFlightList() {
		return flightFareAlertConnectingFlightList;
	}
	public void setFlightFareAlertConnectingFlightList(
			List<FlightFareAlertConnectingFlight> flightFareAlertConnectingFlightList) {
		this.flightFareAlertConnectingFlightList = flightFareAlertConnectingFlightList;
	}
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
	
	public String getOriginCode() {
		return originCode;
	}
	public String getDestinationcode() {
		return destinationcode;
	}
	public String getDepartureTime() {
		return departureTime;
	}
	public String getArrivalTime() {
		return arrivalTime;
	}
	public String getAirlineName() {
		return airlineName;
	}
	public String getFareClass() {
		return fareClass;
	}
	public String getOrderid() {
		return orderid;
	}
	public String getFlightNo() {
		return flightNo;
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
	
	public void setOriginCode(String originCode) {
		this.originCode = originCode;
	}
	public void setDestinationcode(String destinationcode) {
		this.destinationcode = destinationcode;
	}
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}
	public void setFareClass(String fareClass) {
		this.fareClass = fareClass;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}
	public String getReasons() {
		return reasons;
	}
	public void setReasons(String reasons) {
		this.reasons = reasons;
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
