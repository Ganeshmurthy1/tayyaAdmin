package com.lintas.admin.flight.model;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.lintas.admin.common.model.Timestampable;
 
@Entity
@Table(name = "flight_travel_request_connecting_flight_trip_details")
public class FlightTravelRequestConnectingFlightTripDetail extends Timestampable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "origin_name")
	private String originName;

	@Column(name = "origin_code", length = 3)
	private String originCode;

	@Column(name = "destination_name")
	private String destinationName;

	@Column(name = "destination_code", length = 3)
	private String destinationCode;

	@Column(name = "departure_timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date departureTimestamp;
	
	@Column(name = "departure_date")
	@Temporal(TemporalType.DATE)
	private Date departureDate;
	
	@Column(name = "departure_time")
	@Temporal(TemporalType.TIME)
	private Date departureTime;
	
	@Transient
	private String depTime;
	
	@Transient
	private String depDate;

	@Column(name = "arrival_timestamp")
	@Temporal(TemporalType.TIMESTAMP)	
	private Date arrivalTimestamp;
	
	@Column(name = "arrival_time")
	@Temporal(TemporalType.TIME)
	private Date arrivalTime;
	
	@Column(name = "arrival_date")
	@Temporal(TemporalType.DATE)
	private Date arrivalDate;
	
	@Transient
	private String arrTime;
	
	@Transient
	private String arrDate;

	@Column(name = "operated_by_name")
	private String operatedByName;

	@Column(name = "operated_by_code", length = 3)
	protected String operatedByCode;

	// economy , business
	@Column(name = "class_of_service", length = 20)
	protected String classOfService;

	@Column(name = "trips")
	private int trips;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "flight_travel_trip_id", referencedColumnName = "id")
	private FlightTravelRequestTripDetail FlightTravelRequestTripDetail;

	public String getOriginName() {
		return originName;
	}

	public void setOriginName(String originName) {
		this.originName = originName;
	}

	public String getOriginCode() {
		return originCode;
	}

	public void setOriginCode(String originCode) {
		this.originCode = originCode;
	}

	public String getDestinationName() {
		return destinationName;
	}

	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}

	public String getDestinationCode() {
		return destinationCode;
	}

	public void setDestinationCode(String destinationCode) {
		this.destinationCode = destinationCode;
	}

	public Date getDepartureTimestamp() {
		return departureTimestamp;
	}

	public void setDepartureTimestamp(Date departureTimestamp) {
		this.departureTimestamp = departureTimestamp;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public Date getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}

	public String getDepTime() {
		return depTime;
	}

	public void setDepTime(String depTime) {
		this.depTime = depTime;
	}

	public String getDepDate() {
		return depDate;
	}

	public void setDepDate(String depDate) {
		this.depDate = depDate;
	}

	public Date getArrivalTimestamp() {
		return arrivalTimestamp;
	}

	public void setArrivalTimestamp(Date arrivalTimestamp) {
		this.arrivalTimestamp = arrivalTimestamp;
	}

	public Date getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public String getArrTime() {
		return arrTime;
	}

	public void setArrTime(String arrTime) {
		this.arrTime = arrTime;
	}

	public String getArrDate() {
		return arrDate;
	}

	public void setArrDate(String arrDate) {
		this.arrDate = arrDate;
	}

	public String getOperatedByName() {
		return operatedByName;
	}

	public void setOperatedByName(String operatedByName) {
		this.operatedByName = operatedByName;
	}

	public String getOperatedByCode() {
		return operatedByCode;
	}

	public void setOperatedByCode(String operatedByCode) {
		this.operatedByCode = operatedByCode;
	}

	public String getClassOfService() {
		return classOfService;
	}

	public void setClassOfService(String classOfService) {
		this.classOfService = classOfService;
	}

	public int getTrips() {
		return trips;
	}

	public void setTrips(int trips) {
		this.trips = trips;
	}

	public FlightTravelRequestTripDetail getFlightTravelRequestTripDetail() {
		return FlightTravelRequestTripDetail;
	}

	public void setFlightTravelRequestTripDetail(FlightTravelRequestTripDetail flightTravelRequestTripDetail) {
		FlightTravelRequestTripDetail = flightTravelRequestTripDetail;
	}
}

