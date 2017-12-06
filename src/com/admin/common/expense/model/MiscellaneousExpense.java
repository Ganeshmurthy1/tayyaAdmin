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
@Table(name = "miscellaneous_expense")
public class MiscellaneousExpense extends Timestampable {

	/**
	 * Created by Vimal
	 */
	private static final long serialVersionUID = 1L;

	private String confirmationNumber;
	private String passengerName;
	private String remarks;
	private BigDecimal supplierPrice;
	private BigDecimal basePrice;
	private BigDecimal otherTaxes;
	private BigDecimal managementFee;
	private BigDecimal convenienceFee;
	private BigDecimal serviceTax; // – (Service Tax Base, Swachh Bharat Cess,
									// Krishi Kalyan Cess)
	@Transient
	private String expenseDateTemp;

	@Column(name = "department")
	private String department;

	@Column(name = "location")
	private String location;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "expense_id", referencedColumnName = "id")
	private TripExepense tripExepense;

	@Column(name = "expense_date")
	@Temporal(TemporalType.DATE)
	private Date expenseDate;

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

	public Date getExpenseDate() {
		return expenseDate;
	}

	public void setExpenseDate(String expenseDateTemp) {
		this.expenseDate = DateConversion.StringToDate(expenseDateTemp);
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	public String getExpenseDateTemp() {
		return expenseDateTemp;
	}

	public void setExpenseDateTemp(String expenseDateTemp) {
		this.expenseDateTemp = expenseDateTemp;
	}

	public void setExpenseDate(Date expenseDate) {
		this.expenseDate = expenseDate;
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public BigDecimal getSupplierPrice() {
		return supplierPrice;
	}

	public void setSupplierPrice(BigDecimal supplierPrice) {
		this.supplierPrice = supplierPrice;
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

	public BigDecimal getServiceTax() {
		return serviceTax;
	}

	public void setServiceTax(BigDecimal serviceTax) {
		this.serviceTax = serviceTax;
	}

}
