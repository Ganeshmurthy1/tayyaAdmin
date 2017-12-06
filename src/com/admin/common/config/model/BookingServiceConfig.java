package com.admin.common.config.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lintas.admin.common.model.Timestampable;

@Entity
@Table(name = "booking_service_config")
public class BookingServiceConfig extends Timestampable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="is_flight", columnDefinition = "BOOLEAN DEFAULT false")
	private boolean isFlight;
	@Column(name="is_hotel", columnDefinition = "BOOLEAN DEFAULT false")
	private boolean isHotel;
	 
	public boolean isHotel() {
		return isHotel;
	}
	public void setHotel(boolean isHotel) {
		this.isHotel = isHotel;
	}
	public boolean isFlight() {
		return isFlight;
	}
	public void setFlight(boolean isFlight) {
		this.isFlight = isFlight;
	}
 
}