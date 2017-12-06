package com.lintas.admin.flight.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="flight_temp_pricing_keys")
public class FlightAirPriceDetailsTemp implements Serializable

{
	private static final long serialVersionUID = 1L;
	@Id	
	@Column(name = "id")
	@GeneratedValue
	private int id;
	@Column(name = "price_key")
	private String price_key;
	@Column(name = "date_time")
	private Date datetime;
	@Column(name = "flightpriceresponse", columnDefinition = "LONGBLOB")
	private byte[] FlightPriceResponse;
	@Column(name = "tboflightpriceresponse", columnDefinition = "LONGBLOB")
	private byte[] TBOFlightPriceResponse;
	@Column(name = "tboflightpriceresponsespecial", columnDefinition = "LONGBLOB")
	private byte[] TBOFlightPriceResponseSpecial;
	@Column(name = "lowfare_flightindex1")
	private String lowFareFlightIndex1;
	@Column(name = "lowfare_flightindex2")
	private String lowFareFlightIndex2;
	@Column(name = "reasons",columnDefinition = "LONGTEXT")
	private String reasonToSelect;
	@Column(name = "lowfare_flightindex_return1")
	private String lowFareFlightIndexReturn1;
	@Column(name = "lowfare_flightindex_return2")
	private String lowFareFlightIndexReturn2;
	@Column(name = "reasons_return",columnDefinition = "LONGTEXT")
	private String reasonToSelectReturn;


	
	
	public String getLowFareFlightIndex1() {
		return lowFareFlightIndex1;
	}
	public String getLowFareFlightIndex2() {
		return lowFareFlightIndex2;
	}
	public String getReasonToSelect() {
		return reasonToSelect;
	}
	public String getLowFareFlightIndexReturn1() {
		return lowFareFlightIndexReturn1;
	}
	public String getLowFareFlightIndexReturn2() {
		return lowFareFlightIndexReturn2;
	}
	public String getReasonToSelectReturn() {
		return reasonToSelectReturn;
	}
	public void setLowFareFlightIndex1(String lowFareFlightIndex1) {
		this.lowFareFlightIndex1 = lowFareFlightIndex1;
	}
	public void setLowFareFlightIndex2(String lowFareFlightIndex2) {
		this.lowFareFlightIndex2 = lowFareFlightIndex2;
	}
	public void setReasonToSelect(String reasonToSelect) {
		this.reasonToSelect = reasonToSelect;
	}
	public void setLowFareFlightIndexReturn1(String lowFareFlightIndexReturn1) {
		this.lowFareFlightIndexReturn1 = lowFareFlightIndexReturn1;
	}
	public void setLowFareFlightIndexReturn2(String lowFareFlightIndexReturn2) {
		this.lowFareFlightIndexReturn2 = lowFareFlightIndexReturn2;
	}
	public void setReasonToSelectReturn(String reasonToSelectReturn) {
		this.reasonToSelectReturn = reasonToSelectReturn;
	}
	
	public String getPrice_key() {
		return price_key;
	}
	public void setPrice_key(String price_key) {
		this.price_key = price_key;
	}
	public byte[] getFlightPriceResponse() {
		return FlightPriceResponse;
	}
	public void setFlightPriceResponse(byte[] flightPriceResponse) {
		FlightPriceResponse = flightPriceResponse;
	}
	public byte[] getTBOFlightPriceResponse() {
		return TBOFlightPriceResponse;
	}
	public void setTBOFlightPriceResponse(byte[] tBOFlightPriceResponse) {
		TBOFlightPriceResponse = tBOFlightPriceResponse;
	}
	
	
	public byte[] getTBOFlightPriceResponseSpecial() {
		return TBOFlightPriceResponseSpecial;
	}
	public void setTBOFlightPriceResponseSpecial(byte[] tBOFlightPriceResponseSpecial) {
		TBOFlightPriceResponseSpecial = tBOFlightPriceResponseSpecial;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}


}