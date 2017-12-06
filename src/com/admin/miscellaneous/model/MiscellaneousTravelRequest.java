/**
 * 
 */
package com.admin.miscellaneous.model;


import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lintas.admin.common.model.Timestampable;

/**
 * @author MANISH
 *
 */
@Entity
@Table(name="miscellaneous_travel_request")
public class MiscellaneousTravelRequest extends Timestampable implements Serializable{
	private static final long serialVersionUID = 1L;
	@Column(name = "company_id" ,columnDefinition="INT(11) default 0")
	private int companyId;
	
	@Column(name = "user_id",columnDefinition="INT(11) default 0")
	private int userId;
	
	@Column(name = "confirmation_number")
	private String confirmationNumber;
	
	@Column(name="travel_request_number")
	private String travelRequestNumber;
	
	@Column(name="entity")
	private String entity;
	
	@Column(name="title")
	private String title;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name = "supplier_Name")
	private String supplierName;
	
	@Column(name="booking_date")
	private String  bookingDate;
	
	@Column(name = "details1")
	private String details1;
	
	@Column(name = "details2")
	private String details2;
	
	@Column(name = "total_amount", columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal totalAmount;

	@Column(name = "status_id")
	 private int statusId;
	
	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public int getCompanyId() {
		return companyId;
	}

	public String getConfirmationNumber() {
		return confirmationNumber;
	}

	public String getTravelRequestNumber() {
		return travelRequestNumber;
	}

	public String getEntity() {
		return entity;
	}

	public String getTitle() {
		return title;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public String getBookingDate() {
		return bookingDate;
	}

	public String getDetails1() {
		return details1;
	}

	public String getDetails2() {
		return details2;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public void setConfirmationNumber(String confirmationNumber) {
		this.confirmationNumber = confirmationNumber;
	}

	public void setTravelRequestNumber(String travelRequestNumber) {
		this.travelRequestNumber = travelRequestNumber;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}

	public void setDetails1(String details1) {
		this.details1 = details1;
	}

	public void setDetails2(String details2) {
		this.details2 = details2;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}
