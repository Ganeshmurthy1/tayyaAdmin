package com.admin.hotel.fin.sheet.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="hotel_flight_booking_status")
public class HotelFlightBookingStatus implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue
	private Long id;
	private int created;
	private int qSentToMail;
	private int approved;
	private int shortListed;
	private int booked;
	private int cancelled;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getCreated() {
		return created;
	}
	public void setCreated(int created) {
		this.created = created;
	}
	 
	public int getApproved() {
		return approved;
	}
	public void setApproved(int approved) {
		this.approved = approved;
	}
	public int getShortListed() {
		return shortListed;
	}
	public void setShortListed(int shortListed) {
		this.shortListed = shortListed;
	}
	public int getBooked() {
		return booked;
	}
	public void setBooked(int booked) {
		this.booked = booked;
	}
	public int getCancelled() {
		return cancelled;
	}
	public void setCancelled(int cancelled) {
		this.cancelled = cancelled;
	}
	public int getqSentToMail() {
		return qSentToMail;
	}
	public void setqSentToMail(int qSentToMail) {
		this.qSentToMail = qSentToMail;
	}
 
}
