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

@Entity
@Table(name = "bus_expense")
public class BusExpense extends Timestampable {

	/**
	 * Created by Vimal
	 */
	private static final long serialVersionUID = 1L;

	@Transient
	private String travelDateTemp;

	@Column(name = "bus_type")
	private String busType;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "expense_id", referencedColumnName = "id")
	private TripExepense tripExepense;

	private String confirmationNumber;

	private BigDecimal basePrice;

	private BigDecimal otherTaxes;

	private BigDecimal managementFee;

	private BigDecimal convenienceFee;

	private String passengerName;

	private String Remarks;

	private BigDecimal supplierPrice;

	private BigDecimal serviceTax;

	@Column(name = "location")
	private String location;

	@Column(name = "pickup")
	private String pickUp;

	@Column(name = "dropoff")
	private String dropOff;

	@Column(name = "Travel_date")
	@Temporal(TemporalType.DATE)
	private Date travelDate;

	@Column(name = "department")
	private String department;

	@Column(name = "expense_currency")
	private String expenseCurrency;

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

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public Date getTravelDate() {
		return travelDate;
	}

	public void setTravelDate(String travelDateTemp) {
		this.travelDate = DateConversion.StringToDate(travelDateTemp);
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPickUp() {
		return pickUp;
	}

	public String getBusType() {
		return busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

	public String getDropOff() {
		return dropOff;
	}

	public void setDropOff(String dropOff) {
		this.dropOff = dropOff;
	}

	public void setPickUp(String pickUp) {
		this.pickUp = pickUp;
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

	public BigDecimal getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(BigDecimal basePrice) {
		this.basePrice = basePrice;
	}

	public BigDecimal getOtherTaxes() {
		return otherTaxes;
	}

	public void setOtherTaxes(BigDecimal otherTaxes) {
		this.otherTaxes = otherTaxes;
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

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public String getRemarks() {
		return Remarks;
	}

	public void setRemarks(String remarks) {
		Remarks = remarks;
	}

	public BigDecimal getSupplierPrice() {
		return supplierPrice;
	}

	public void setSupplierPrice(BigDecimal supplierPrice) {
		this.supplierPrice = supplierPrice;
	}

	public BigDecimal getServiceTax() {
		return serviceTax;
	}

	public void setServiceTax(BigDecimal serviceTax) {
		this.serviceTax = serviceTax;
	}

}
