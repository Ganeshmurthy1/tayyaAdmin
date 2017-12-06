package com.lintas.admin.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="company_booking_system_type")
public class CompanyBookingSystemType implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue
	@Column(name="booking_id")	
	private Long bookingId;
	@Column(name="ibe")	
	private boolean isIBE;
	@Column(name="api")	
	private boolean isAPI;
	/**
	 * @return the isIBE
	 */
	public boolean isIBE() {
		return isIBE;
	}
	/**
	 * @param isIBE the isIBE to set
	 */
	public void setIBE(boolean isIBE) {
		this.isIBE = isIBE;
	}
	/**
	 * @return the isAPI
	 */
	public boolean isAPI() {
		return isAPI;
	}
	/**
	 * @param isAPI the isAPI to set
	 */
	public void setAPI(boolean isAPI) {
		this.isAPI = isAPI;
	}
	/**
	 * @return the bookingId
	 */
	public Long getBookingId() {
		return bookingId;
	}
	/**
	 * @param bookingId the bookingId to set
	 */
	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

 }
