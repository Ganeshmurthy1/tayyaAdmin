package com.admin.common.expense.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.lintas.admin.common.model.Timestampable;
import com.lintas.admin.flight.model.FlightOrderRow;
import com.lintas.utility.DateConversion;


@Entity
@Table(name="flight_expense")
public class FlightExpense extends Timestampable{

	/**
	 *    Created by Vimal
	 */
	private static final long serialVersionUID = 1L;
	
	@Transient
	private String arrivalDate;
	
	@Transient
	private String departuralDate;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "expense_id", referencedColumnName = "id")
	private TripExepense tripExepense;
	
	
	@Column(name="order_id")
	private String OrderId;
	
	/*@Column(name="expense_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date expenseDate;*/
	@Column(name="flight_pnr_number")
	private String pnrNumber;
	
	@Column(name="flight_number")
	private String flightNumber;
	
	@Column(name="flight_carrier")
	private String flightCarrier;
	
	
	@Column(name="arrival_date")
	@Temporal(TemporalType.DATE)
	private Date arrivDate;
	
	@Column(name="departure_date")
	@Temporal(TemporalType.DATE)
	private Date departureDate;
	
	@Column(name="department")
	private String department;
	
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

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPnrNumber() {
		return pnrNumber;
	}

	public void setPnrNumber(String pnrNumber) {
		this.pnrNumber = pnrNumber;
	}

	public Date getArrivDate() {
		return arrivDate;
	}

	public void setArrivDate( String arrivalDate) {
		this.arrivDate =DateConversion.StringToDate(arrivalDate);
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(String departuralDate) {
		this.departureDate=DateConversion.StringToDate(departuralDate);
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getFlightCarrier() {
		return flightCarrier;
	}

	public void setFlightCarrier(String flightCarrier) {
		this.flightCarrier = flightCarrier;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getExpenseCurrency() {
		return expenseCurrency;
	}

	public void setExpenseCurrency(String expenseCurrency) {
		this.expenseCurrency = expenseCurrency;
	}

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

	public String getArrivalDate() {
		return arrivalDate;
	}

	public String getDeparturalDate() {
		return departuralDate;
	}

	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public void setDeparturalDate(String departuralDate) {
		this.departuralDate = departuralDate;
	}
	
	public TripExepense getExpense() {
		return tripExepense;
	}

	public void setExpense(TripExepense tripExepense) {
		this.tripExepense = tripExepense;
	}
	

}
