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
@Table(name = "flight_order_trip_details")
public class FlightOrderTripDetail extends Timestampable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_row_id", referencedColumnName = "id")
	private FlightOrderRow flightOrderRow;
	
	@Transient
	private String convertDate;
	@Transient
	private String arrTime;
	@Transient
	private String arrDate;
	@Transient
	private String depTime;
	@Transient
	private String depDate;
	@Transient
	private String depDateTime;
	@Transient
	private String arrDateTime;
	
	
	@Column(name = "flight_number")
	private String flightNumber;

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
	
	
	@Column(name = "arrival_timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date arrivalTimestamp;
	@Column(name = "arrival_time")
	@Temporal(TemporalType.TIME)
	private Date arrivalTime;
	@Column(name = "arrival_date")
	@Temporal(TemporalType.DATE)
	private Date arrivalDate;
	

	@Column(name = "operated_by_name")
	private String operatedByName;

	@Column(name = "operated_by_code", length = 3)
	protected String operatedByCode;

	@Column(name = "class_of_service", length = 20)
	protected String classOfService;

	@Column(name = "destination_terminal", length = 30)
	protected String destinationTerminal;

	@Column(name = "origin_terminal", length = 30)
	protected String originTerminal;

	@Column(name = "trip_type")
	private String tripType;

	@Column(name = "trips")
	private int trips;

	@Column(name = "flight_duration")
	private String flightDuration;

	@Column(name = "pnr_segment_identifier")
	private Integer pnrSegmentIdentifier;

	@Column(name = "craft")
	private String craft;
	
	@Column(name = "fare_basiscode")
	private String fareBasisCode;
	
	@Column(name = "fare_class")
	private String fareClass;

	public String getFareBasisCode() {
		return fareBasisCode;
	}

	public String getFareClass() {
		return fareClass;
	}

	public void setFareBasisCode(String fareBasisCode) {
		this.fareBasisCode = fareBasisCode;
	}

	public void setFareClass(String fareClass) {
		this.fareClass = fareClass;
	}

	public String getCraft() {
		return craft;
	}

	public void setCraft(String craft) {
		this.craft = craft;
	}
	
	public FlightOrderRow getFlightOrderRow() {
		return flightOrderRow;
	}

	public void setFlightOrderRow(FlightOrderRow flightOrderRow) {
		this.flightOrderRow = flightOrderRow;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
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

	public String getTripType() {
		return tripType;
	}

	public void setTripType(String tripType) {
		this.tripType = tripType;
	}

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

	public Date getDepartureDate() {
		return departureDate;
	}

	public String getDepDate() {
		return depDate;
	}

	public void setDepDate(String depDate) {
		this.depDate = depDate;
	}

	public String getArrDate() {
		return arrDate;
	}

	public void setArrDate(String arrDate) {
		this.arrDate = arrDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
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

	public String getClassOfService() {
		return classOfService;
	}

	public void setClassOfService(String classOfService) {
		this.classOfService = classOfService;
	}

	public String getDestinationTerminal() {
		return destinationTerminal;
	}

	public void setDestinationTerminal(String destinationTerminal) {
		this.destinationTerminal = destinationTerminal;
	}

	public String getOriginTerminal() {
		return originTerminal;
	}

	public void setOriginTerminal(String originTerminal) {
		this.originTerminal = originTerminal;
	}

	public int getTrips() {
		return trips;
	}

	public void setTrips(int trips) {
		this.trips = trips;
	}


	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public Integer getPnrSegmentIdentifier() {
		return pnrSegmentIdentifier;
	}

	public void setPnrSegmentIdentifier(Integer pnrSegmentIdentifier) {
		this.pnrSegmentIdentifier = pnrSegmentIdentifier;
	}

	public Date getDepartureTimestamp() {
		return departureTimestamp;
	}

	public void setDepartureTimestamp(Date departureTimestamp) {
		this.departureTimestamp = departureTimestamp;
	}

	public Date getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
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

	public String getDepTime() {
		return depTime;
	}

	public void setDepTime(String depTime) {
		this.depTime = depTime;
	}

	public String getArrTime() {
		return arrTime;
	}

	public void setArrTime(String arrTime) {
		this.arrTime = arrTime;
	}

	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}


	public String getFlightDuration() {
		return flightDuration;
	}

	public void setFlightDuration(String flightDuration) {
		this.flightDuration = flightDuration;
	}

	public String getConvertDate() {
		return convertDate;
	}

	public void setConvertDate(String convertDate) {
		this.convertDate = convertDate;
	}
	public String getDepDateTime() {
		return depDateTime;
	}

	public void setDepDateTime(String depDateTime) {
		this.depDateTime = depDateTime;
	}

	public String getArrDateTime() {
		return arrDateTime;
	}

	public void setArrDateTime(String arrDateTime) {
		this.arrDateTime = arrDateTime;
	}

	

}
