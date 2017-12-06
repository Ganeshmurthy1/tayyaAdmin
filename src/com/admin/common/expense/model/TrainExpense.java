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
@Table(name="train_expense")
public class TrainExpense extends Timestampable {

	/**
	 *    Created by Vimal
	 */
	private static final long serialVersionUID = 1L;
	
	private BigDecimal basePrice;
	
	private BigDecimal otherTaxes;
	
	private BigDecimal managementFee;
	
	private BigDecimal convenienceFee;
	
	private String passengerName;

	private String Remarks;
	
	private BigDecimal supplierPrice;
	
	private BigDecimal serviceTax; 
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "expense_id", referencedColumnName = "id")
	private TripExepense tripExepense;
	
	@Transient
	private String travelDateTemp;
	
	@Column(name="pnr_number")
	private String pnrNumber;
	
	@Column(name="train_number")
	private String trainNumber; 
	
	@Column(name="from_location")
	private String Fromlocation;	
	
	@Column(name="to_location")
	private String Tolocation;	
	
	
	@Column(name="travel_date")
	@Temporal(TemporalType.DATE)
	private Date travelDate;
	
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


	
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public String getFromlocation() {
		return Fromlocation;
	}

	public void setFromlocation(String fromlocation) {
		Fromlocation = fromlocation;
	}

	public String getTolocation() {
		return Tolocation;
	}

	public void setTolocation(String tolocation) {
		Tolocation = tolocation;
	}

	public String getPnrNumber() {
		return pnrNumber;
	}

	public void setPnrNumber(String pnrNumber) {
		this.pnrNumber = pnrNumber;
	}

	public Date getTravelDate() {
		return travelDate;
	}

	public void setTravelDate(String travelDateTemp) {
		this.travelDate = DateConversion.StringToDate(travelDateTemp);
	
	}

	/*public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
*/
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

	public String getTrainNumber() {
		return trainNumber;
	}

	public void setTrainNumber(String trainNumber) {
		this.trainNumber = trainNumber;
	}
	
	public TripExepense getExpense() {
		return tripExepense;
	}

	public void setExpense(TripExepense tripExepense) {
		this.tripExepense = tripExepense;
	}
	
/*
	public String getDepartTime() {
		return departTime;
	}

	public void setDepartTime(String departTime) {
		this.departTime = departTime;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getTravelDate() {
		return travelDate;
	}

	public void setTravelDate(Date travelDate) {
		this.travelDate = travelDate;
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

	public String getTravelDateTemp() {
		return travelDateTemp;
	}

	public void setTravelDateTemp(String travelDateTemp) {
		this.travelDateTemp = travelDateTemp;
	}

	public void setTravelDate(Date travelDate) {
		this.travelDate = travelDate;
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

