package com.common.dsr;

import java.math.BigDecimal;

public class HotelCityWiseTravelReportData {
	
	private String guestName;
	private String hotelName;
	private String city;
	private String status;
	private BigDecimal totalInvoiceAmount;
	private BigDecimal basePrice;
	private String checkInDate;
	private String checkOutDate;
	private int noOfNights;
	public String getGuestName() {
		return guestName;
	}
	public String getHotelName() {
		return hotelName;
	}
	public String getCity() {
		return city;
	}
	public String getStatus() {
		return status;
	}
	public BigDecimal getTotalInvoiceAmount() {
		return totalInvoiceAmount;
	}
	public BigDecimal getBasePrice() {
		return basePrice;
	}
	public String getCheckInDate() {
		return checkInDate;
	}
	public String getCheckOutDate() {
		return checkOutDate;
	}
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	public void setHotelName(String name) {
		this.hotelName = name;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setTotalInvoiceAmount(BigDecimal totalInvoiceAmount) {
		this.totalInvoiceAmount = totalInvoiceAmount;
	}
	public void setBasePrice(BigDecimal basePrice) {
		this.basePrice = basePrice;
	}
	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}
	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	public int getNoOfNights() {
		return noOfNights;
	}
	public void setNoOfNights(int noOfNights) {
		this.noOfNights = noOfNights;
	}

}
