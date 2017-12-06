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
@Table(name="labor_expense")
public class LaborExpense extends Timestampable {

	/**
	 *    Created by Vimal
	 */
	private static final long serialVersionUID = 1L;	


	@Transient
	private String expenseDateTemp;
	
	@Column(name="work_name")
	private String workName;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "expense_id", referencedColumnName = "id")
	private TripExepense tripExepense;
	public TripExepense getExpense() {
		return tripExepense;
	}

	public void setExpense(TripExepense tripExepense) {
		this.tripExepense = tripExepense;
	}
	

	@Column(name="department")
	private String department;
	
	@Column(name="location")
	private String location;	
	

	@Column(name="hours")
	private String hours;
	

	@Column(name="expense_currency")
	private String expenseCurrency;	
	
	@Column(name="total_amount")
	private BigDecimal totalAmount;
	
	
	@Column(name="expense_date")
	@Temporal(TemporalType.DATE)
	private Date expenseDate;
	
	@Column(name="expense_reason")
	private String expenseReason;
	
	@Column(name="reimbursable")
	private String Reimbursable;
	
	@Column(name="is_billable")
	private boolean isBillable;
	
	@Column(name="receipt_filename")
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

	/*public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
*/
	public String getWorkName() {
		return workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
	}

	public String getHours() {
		return hours;
	}

	public void setHours(String hours) {
		this.hours = hours;
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
	

}


