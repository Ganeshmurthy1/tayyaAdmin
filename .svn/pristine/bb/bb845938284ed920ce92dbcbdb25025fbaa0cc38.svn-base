package com.lintas.admin.flight.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="flight_temp_booking_details_keys")
public class FlightBookingDetailsTemp implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id	
	@Column(name = "id")
	@GeneratedValue
	private int id;

	@Column(name = "booking_details", columnDefinition = "LONGBLOB")
	private byte[] booking_details;

	@Column(name = "order_id")
	private String order_id;

	@Column(name = "date_time")
	private Date datetime;



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte[] getBooking_details() {
		return booking_details;
	}

	public void setBooking_details(byte[] booking_details) {
		this.booking_details = booking_details;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}


}
