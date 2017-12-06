/**
 * 
 */
package com.admin.api.analytics.pojo;

import java.io.Serializable;

/**
 * @author MANISH
 *
 */
public class FlightPojo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String pnr;
	private String source;
	private String agencyName;
	private String departureDate;
	private String name;
	private String status;
	private String hotelCheckInDate;
	private String  orderId;
	private String bookingDate;
	private String city;
	private String fromLocation;
	
	public String getFromLocation() {
		return fromLocation;
	}
	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	 
	public String getHotelCheckInDate() {
		return hotelCheckInDate;
	}
	public void setHotelCheckInDate(String hotelCheckInDate) {
		this.hotelCheckInDate = hotelCheckInDate;
	}
	public String getPnr() {
		return pnr;
	}
	public void setPnr(String pnr) {
		this.pnr = pnr;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getAgencyName() {
		return agencyName;
	}
	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}
	public String getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
