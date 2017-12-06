package com.admin.hotel.fin.sheet.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="payment_options")
public class PaymentOptions implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	@Column(name="is_prepaid",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean prePaid;
	
	@Column(name="is_postpaid",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean postPaid;
	
	@Column(name="is_payat_hotel" ,columnDefinition = "BOOLEAN DEFAULT false")
	private boolean payAtHotel;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isPrePaid() {
		return prePaid;
	}
	public void setPrePaid(boolean prePaid) {
		this.prePaid = prePaid;
	}
	public boolean isPostPaid() {
		return postPaid;
	}
	public void setPostPaid(boolean postPaid) {
		this.postPaid = postPaid;
	}
	public boolean isPayAtHotel() {
		return payAtHotel;
	}
	public void setPayAtHotel(boolean payAtHotel) {
		this.payAtHotel = payAtHotel;
	}
}
