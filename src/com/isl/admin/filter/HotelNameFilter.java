package com.isl.admin.filter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HotelNameFilter extends Filter implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<String> hotelnameList;
	private String vendorName;
	private String countryName;
	private String city;
	

	public HotelNameFilter() {
		super();
		this.hotelnameList = new ArrayList<>();
		this.vendorName = null;
		this.countryName=null;
		this.city=null;
	}


	public List<String> getHotelnameList() {
		return hotelnameList;
	}


	public void setHotelnameList(List<String> hotelnameList) {
		this.hotelnameList = hotelnameList;
	}


	
	public String getVendorName() {
		return vendorName;
	}


	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}


	public String getCountryName() {
		return countryName;
	}


	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}
 


	}