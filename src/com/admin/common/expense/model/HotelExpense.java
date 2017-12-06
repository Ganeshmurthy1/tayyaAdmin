package com.admin.common.expense.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.lintas.admin.common.model.Timestampable;
import com.lintas.utility.DateConversion;
@Entity
@Table(name="hotel_expense")
public class HotelExpense extends Timestampable {

	/**
	 *    Created by Vimal
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Transient
	private String checkInDateTemp;
	
	@Transient
	private String checkOutDateTemp;
	 
	
	@Column(name="order_id")
	private String OrderId;
	
	@Column(name="hotel_confirm_number")
	private String HotelConfirmNumber;
	
	@Column(name="hotel_name")
	private String hotelName;
	
	@Column(name="location")
	private String location;
	
	/*@Column(name="expense_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date expenseDate;*/
	
	
	@Column(name="room_type")
	private String roomType;
	

	@Column(name="department")
	private String department;
	
	@Column(name="checkin_date")
	@Temporal(TemporalType.DATE)
	private Date checkInDate;
	
	
	@Column(name="checkout_date")
	@Temporal(TemporalType.DATE)
	private Date checkOutDate;
	 
	@Column(name="expense_currency")
	private String expenseCurrency;	
	
	@Column(name="total_amount")
	private BigDecimal totalAmount;
	
	
	@Column(name="expense_reason")
	private String expenseReason;
	
	@Column(name="reimbursable")
	private String Reimbursable;
	
	@Column(name="is_billable")
	private boolean isBillable;
	
	@Column(name="receipt_filename")
	private String receiptFilename;

	public String getOrderId() {
		return OrderId;
	}

	public void setOrderId(String orderId) {
		OrderId = orderId;
	}

	/*public Date getExpenseDate() {
		return expenseDate;
	}

	public void setExpenseDate(Date expenseDate) {
		this.expenseDate = expenseDate;
	}*/

	public String getHotelConfirmNumber() {
		return HotelConfirmNumber;
	}

	public void setHotelConfirmNumber(String hotelConfirmNumber) {
		HotelConfirmNumber = hotelConfirmNumber;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	

	public Date getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(String checkInDateTemp) {
		this.checkInDate=DateConversion.StringToDate(checkInDateTemp);
	}

	public Date getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(String checkOutDateTemp) {
		this.checkOutDate =DateConversion.StringToDate(checkOutDateTemp);
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getExpenseCurrency() {
		return expenseCurrency;
	}

	public void setExpenseCurrency(String expenseCurrency) {
		this.expenseCurrency = expenseCurrency;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	/*public String getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(String checkIn) {
		this.checkIn = checkIn;
	}

	public String getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(String checkOut) {
		this.checkOut = checkOut;
	}*/

	public String getExpenseReason() {
		return expenseReason;
	}

	public void setExpenseReason(String expenseReason) {
		this.expenseReason = expenseReason;
	}

	public String getReimbursable() {
		return Reimbursable;
	}

	public void setReimbursable(String reimbursable) {
		Reimbursable = reimbursable;
	}

	public boolean isBillable() {
		return isBillable;
	}

	public void setBillable(boolean isBillable) {
		this.isBillable = isBillable;
	}

	public String getReceiptFilename() {
		return receiptFilename;
	}

	public void setReceiptFilename(String receiptFilename) {
		this.receiptFilename = receiptFilename;
	}

	public String getCheckInDateTemp() {
		return checkInDateTemp;
	}

	public void setCheckInDateTemp(String checkInDateTemp) {
		this.checkInDateTemp = checkInDateTemp;
	}

	public String getCheckOutDateTemp() {
		return checkOutDateTemp;
	}

	public void setCheckOutDateTemp(String checkOutDateTemp) {
		this.checkOutDateTemp = checkOutDateTemp;
	}
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "expense_id", referencedColumnName = "id")
	private TripExepense tripExepense;
	public TripExepense getExpense() {
		return tripExepense;
	}

	public void setExpense(TripExepense tripExepense) {
		this.tripExepense = tripExepense;
	}
	
}
