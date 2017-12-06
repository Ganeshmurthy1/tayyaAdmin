package com.lintas.cache.flight.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lintas.admin.common.model.Timestampable;

@Entity
@Table(name="flight_search_cache")
public class FlightSearchCache extends Timestampable implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Column(name="is_domestic",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean domestic;
	@Column(name="currency")
	private String currency;
	@Column(name="origin")
	private String origin;
	@Column(name="destination")
	private String destination;
	@Column(name="dep_date")
	private String depDate;
	@Column(name="trip_type")
	private String tripType;
	@Column(name="arvl_date")
	private String arvlDate;
	@Column(name="supplier")
	private String supplier;
	@Column(name="adult")
	private int adult;
	@Column(name="kid")
	private int kid;
	@Column(name="infant")
	private int infant;
	@Column(name = "search_data" , columnDefinition = "LONGBLOB")
	private byte[] searchData;
	
	public boolean isDomestic() {
		return domestic;
	}
	public String getCurrency() {
		return currency;
	}
	public String getOrigin() {
		return origin;
	}
	public String getDestination() {
		return destination;
	}
	public String getDepDate() {
		return depDate;
	}
	public String getTripType() {
		return tripType;
	}
	public String getArvlDate() {
		return arvlDate;
	}
	public int getAdult() {
		return adult;
	}
	public int getKid() {
		return kid;
	}
	public int getInfant() {
		return infant;
	}
	public byte[] getSearchData() {
		return searchData;
	}
	public void setDomestic(boolean domestic) {
		this.domestic = domestic;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public void setDepDate(String depDate) {
		this.depDate = depDate;
	}
	public void setTripType(String tripType) {
		this.tripType = tripType;
	}
	public void setArvlDate(String arvlDate) {
		this.arvlDate = arvlDate;
	}
	public void setAdult(int adult) {
		this.adult = adult;
	}
	public void setKid(int kid) {
		this.kid = kid;
	}
	public void setInfant(int infant) {
		this.infant = infant;
	}
	public void setSearchData(byte[] searchData) {
		this.searchData = searchData;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

}