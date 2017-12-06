package com.lintas.admin.hotel.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lintas.admin.common.model.Timestampable;

@Entity
@Table(name = "hotel_search_response_cache_entity")
public class HotelSearchResponseCacheEntity extends Timestampable implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column
	private String cityName;
	@Column
	private int citycode;
	@Column
	private String country;
    @Column(name="search_response", columnDefinition = "LONGBLOB")
	private byte[] hotel_search_response;

	public byte[] getHotel_search_response() {
		return hotel_search_response;
	}

	public void setHotel_search_response(byte[] hotel_search_response) {
		this.hotel_search_response = hotel_search_response;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public int getCitycode() {
		return citycode;
	}

	public void setCitycode(int citycode) {
		this.citycode = citycode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}