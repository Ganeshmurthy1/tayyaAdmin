package com.lintas.admin.model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
 
@Entity
@Table(name = "airlinelist")

public class Airlinelist implements Serializable {


	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="airlinecode")
	private String airlinecode;
	@Column(name="iatacode")
	private String iatacode;
	@Column(name="airline")
	private String airline; 
	@Column(name="airlinename")
	private String airlinename;
	public String getAirlinecode() {
		return airlinecode;
	}
	public void setAirlinecode(String airlinecode) {
		this.airlinecode = airlinecode;
	}
	public String getIatacode() {
		return iatacode;
	}
	public void setIatacode(String iatacode) {
		this.iatacode = iatacode;
	}
	public String getAirline() {
		return airline;
	}
	public void setAirline(String airline) {
		this.airline = airline;
	}
	public String getAirlinename() {
		return airlinename;
	}
	public void setAirlinename(String airlinename) {
		this.airlinename = airlinename;
	}



}