package com.lintas.cache.flight.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="flight_search_cache_destination")
public class FlightSearchCacheDestination  implements Serializable
{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(name="origin")
	private String origin;
	@Column(name="destination")
	private String destination;
	@Column(name="oneway",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean oneway;
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public boolean isOneway() {
		return oneway;
	}
	public void setOneway(boolean oneway) {
		this.oneway = oneway;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
}