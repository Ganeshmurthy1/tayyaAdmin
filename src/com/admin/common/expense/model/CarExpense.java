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
import com.lintas.utility.DateConversion;
import com.opensymphony.xwork2.inject.Scope.Strategy;

@Entity
@Table(name = "car_expense")
public class CarExpense extends Timestampable {

	/**
	 * Created by Vimal
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "expense_id", referencedColumnName = "id")
	private TripExepense tripExepense;

	@Transient
	private String travelDateTemp;

	private String confirmationNumber;

	private String passengerName;

	@Column(name = "company_name")
	private String carCompanyName;

	@Column(name = "location")
	private String location;

	@Column(name = "pickup")
	private String pickUp;

	@Column(name = "dropoff")
	private String dropOff;

	@Column(name = "travel_date")
	@Temporal(TemporalType.DATE)
	private Date travelDate;

	@Column(name = "department")
	private String department;

	@Column(name = "expense_currency")
	private String expenseCurrency;

	private BigDecimal supplierPrice;
	private BigDecimal basePrice;
	private BigDecimal otherTaxes;
	private BigDecimal tollOrParkingCharges;
	private BigDecimal driverAllowanceDay;
	private BigDecimal driverAllowanceNight;
	private BigDecimal managementFee;
	private BigDecimal convenienceFee;
	private String remarks;
	private BigDecimal serviceTax; // – (Service Tax Base, Swachh Bharat Cess,
	private String extraKM;
	private String extraHours;

	@Column(name = "total_amount")
	private BigDecimal totalAmount;

	@Column(name = "expense_reason")
	private String expenseReason;

	@Column(name = "reimbursable")
	private String Reimbursable;

	@Column(name = "is_billable")
	private boolean isBillable;

	@Column(name = "receipt_filename")
	private String receiptFilename;

	/*
	 * public String getOrderId() { return OrderId; }
	 * 
	 * public void setOrderId(String orderId) { OrderId = orderId; }
	 * 
	 * public Date getExpenseDate() { return expenseDate; }
	 * 
	 * public void setExpenseDate(Date expenseDate) { this.expenseDate =
	 * expenseDate; }
	 */

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public Date getTravelDate() {
		return travelDate;
	}

	public void setTravelDate(String travelDateTemp) {
		this.travelDate = DateConversion.StringToDate(travelDateTemp);
	}

	/*
	 * public Date getPickUpDate() { return pickUpDate; }
	 * 
	 * public void setPickUpDate(Date pickUpDate) { this.pickUpDate =
	 * pickUpDate; }
	 * 
	 * public Date getDropOffDate() { return dropOffDate; }
	 * 
	 * public void setDropOffDate(Date dropOffDate) { this.dropOffDate =
	 * dropOffDate; }
	 */
	public String getCarCompanyName() {
		return carCompanyName;
	}

	public void setCarCompanyName(String carCompanyName) {
		this.carCompanyName = carCompanyName;
	}

	public String getDropOff() {
		return dropOff;
	}

	public void setDropOff(String dropOff) {
		this.dropOff = dropOff;
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

	/*
	 * public String getCompanyName() { return companyName; }
	 * 
	 * public void setCompanyName(String companyName) { this.companyName =
	 * companyName; }
	 */

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPickUp() {
		return pickUp;
	}

	public void setPickUp(String pickUp) {
		this.pickUp = pickUp;
	}

	/*
	 * public String getDropOut() { return dropOut; }
	 * 
	 * public void setDropOut(String dropOut) { this.dropOut = dropOut; }
	 */
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

	public String getTravelDateTemp() {
		return travelDateTemp;
	}

	public void setTravelDateTemp(String travelDateTemp) {
		this.travelDateTemp = travelDateTemp;
	}

	public void setTravelDate(Date travelDate) {
		this.travelDate = travelDate;
	}

	public TripExepense getExpense() {
		return tripExepense;
	}

	public void setExpense(TripExepense tripExepense) {
		this.tripExepense = tripExepense;
	}

	public String getConfirmationNumber() {
		return confirmationNumber;
	}

	public void setConfirmationNumber(String confirmationNumber) {
		this.confirmationNumber = confirmationNumber;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	

	public BigDecimal getServiceTax() {
		return serviceTax;
	}

	public void setServiceTax(BigDecimal serviceTax) {
		this.serviceTax = serviceTax;
	}

	public BigDecimal getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(BigDecimal basePrice) {
		this.basePrice = basePrice;
	}

	public String getExtraKM() {
		return extraKM;
	}

	public void setExtraKM(String extraKM) {
		this.extraKM = extraKM;
	}

	public String getExtraHours() {
		return extraHours;
	}

	public void setExtraHours(String extraHours) {
		this.extraHours = extraHours;
	}

	public BigDecimal getOtherTaxes() {
		return otherTaxes;
	}

	public void setOtherTaxes(BigDecimal otherTaxes) {
		this.otherTaxes = otherTaxes;
	}

	

	public BigDecimal getDriverAllowanceDay() {
		return driverAllowanceDay;
	}

	public void setDriverAllowanceDay(BigDecimal driverAllowanceDay) {
		this.driverAllowanceDay = driverAllowanceDay;
	}

	public BigDecimal getDriverAllowanceNight() {
		return driverAllowanceNight;
	}

	public void setDriverAllowanceNight(BigDecimal driverAllowanceNight) {
		this.driverAllowanceNight = driverAllowanceNight;
	}

	public BigDecimal getManagementFee() {
		return managementFee;
	}

	public void setManagementFee(BigDecimal managementFee) {
		this.managementFee = managementFee;
	}

	public BigDecimal getConvenienceFee() {
		return convenienceFee;
	}

	public void setConvenienceFee(BigDecimal convenienceFee) {
		this.convenienceFee = convenienceFee;
	}

	public BigDecimal getSupplierPrice() {
		return supplierPrice;
	}

	public void setSupplierPrice(BigDecimal supplierPrice) {
		this.supplierPrice = supplierPrice;
	}

	public BigDecimal getTollOrParkingCharges() {
		return tollOrParkingCharges;
	}

	public void setTollOrParkingCharges(BigDecimal tollOrParkingCharges) {
		this.tollOrParkingCharges = tollOrParkingCharges;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
