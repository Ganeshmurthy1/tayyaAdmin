package com.lintas.admin.flight.model;



import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="flight_fare_alert_connecting_flight")
public class FlightFareAlertConnectingFlight implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int id;
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
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fare_alert_id", referencedColumnName = "id")
	private FlightFareAlertDetail flightFareAlertDetail;
	
	public int getId() {
		return id;
	}
	public FlightFareAlertDetail getFlightFareAlertDetail() {
		return flightFareAlertDetail;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setFlightFareAlertDetail(FlightFareAlertDetail flightFareAlertDetail) {
		this.flightFareAlertDetail = flightFareAlertDetail;
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
	public String getFlightNo() {
		return flightNo;
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
	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}
}
